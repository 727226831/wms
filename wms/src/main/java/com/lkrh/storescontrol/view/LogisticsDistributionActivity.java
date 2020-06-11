package com.lkrh.storescontrol.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityLogisticsDistributionBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogisticsDistributionActivity extends BaseActivity {
    Uri photoUri;
    int imageresultcode=100;
    File file;
    private  String imageid="";
    ActivityLogisticsDistributionBinding binding;
    LoginBean.Menu menuBean;
    SharedPreferences sharedPreferences;
    String plate="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding= DataBindingUtil.setContentView(LogisticsDistributionActivity.this,R.layout.activity_logistics_distribution);
        sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
      binding.ibUpload.setOnClickListener(onClickListener);
      binding.ivPlate.setOnClickListener(onClickListener);
      binding.bDepart.setOnClickListener(onClickListener);
      binding.bArrive.setOnClickListener(onClickListener);


      binding.etPlate.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void afterTextChanged(Editable editable) {
              if(editable.toString().isEmpty()){
                  binding.ivClear.setVisibility(View.GONE);
              }else {
                  binding.ivClear.setVisibility(View.VISIBLE);
              }
          }
      });
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_plate:
                    openScan();
                    break;
                case R.id.ib_upload:
                    takePhone(LogisticsDistributionActivity.this);
                    break;
                case R.id.b_depart:
                    putData("出发");
                    break;
                case R.id.b_arrive:
                    putData("到达");
                    break;
            }
        }
    };

    private void setTime(String strings) {
        sharedPreferences.edit().putString(menuBean.getMenucode()+"Time", strings).commit();
    }
    private String getTime() {
        String stringscandata="";
        stringscandata=sharedPreferences.getString(menuBean.getMenucode()+"Time","");
        return stringscandata;

    }
    private void putData(String button) {
        dialog.show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String strings=simpleDateFormat.format(date);
        if(getTime().isEmpty()) {
            setTime(strings);
        }

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("button",button);
            jsonObject.put("condition",getTime());
            JSONObject object=new JSONObject();
            object.put("imageid",imageid);
            object.put("plate",binding.etPlate.getText().toString());
            jsonObject.put("formdata",object.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("requestbody-->",obj);
        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                ConfirmBean bean= null;
                try {
                    bean = new Gson().fromJson(response.body().string(), ConfirmBean.class);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Log.i("bean-->",new Gson().toJson(bean));
                if(bean==null){
                    Toast.makeText(LogisticsDistributionActivity.this,"无返回值",Toast.LENGTH_LONG).show();
                    return;
                }
                if(bean.getResultcode().equals("200")){

                    SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                    setTime("");

                    sharedPreferences.edit().putString(menuBean.getMenucode()+"List","").commit();
                    sharedPreferences.edit().putString(menuBean.getMenucode()+"Scan","").commit();
                }

                Toast.makeText(LogisticsDistributionActivity.this,bean.getResultMessage(),Toast.LENGTH_LONG).show();

                finish();

            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LogisticsDistributionActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            } });
    }

    private void openScan() {

        new IntentIntegrator(LogisticsDistributionActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    public void takePhone(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA,}
                        , 300);
            } else if (ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        400);
            } else {
                //  takePhoto();
                autoObtainCameraPermission();

            }

        } else {
            // takePhoto();
            autoObtainCameraPermission();
        }
    }
    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // ToastUtils.showShort(this, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},  0x03);
        } else {//有权限直接调用系统相机拍照
            if (hasSdcard()) {
                String path = Environment.getExternalStorageDirectory() +
                        File.separator + Environment.DIRECTORY_DCIM + File.separator;
                String fileName = Untils.getPhotoFileName() + ".jpg";
                file = new File(path);
                if (!file.exists()) {
                    file.mkdir();
                }

                photoUri = Uri.fromFile(new File(path + fileName));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    //通过FileProvider创建一个content类型的Uri
                    file=new File(path + fileName);
                photoUri= FileProvider.getUriForFile(LogisticsDistributionActivity.this
                        ,"com.storescontrol.fileprovider",file);
                Intent intentCamera = new Intent();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                }
                intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                //将拍照结果保存至photo_file的Uri中，不保留在相册中
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intentCamera, imageresultcode);

            }
        }
    }
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == imageresultcode) {
            if (photoUri != null) {
                       uploadBatchPicture(file.getAbsolutePath());
            }
        }
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
              plate=result.getContents();


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void uploadBatchPicture(String path) {

        dialog.show();
        Log.i("path",path);
        file=new File(path);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("picturePath", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .header("Content-Type", "application/x-www-form-urlencoded ")
                .url(Request.URL+"/Handler.ashx")
                .post(requestBody)
                .build();
        Log.i("url",request.url().toString());
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                dialog.dismiss();
                Log.i("onFailure",e.toString());

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response)  {
                dialog.dismiss();
                if(response.code()==200) {
                    JSONObject jsonObjectresponse= null;
                    try {
                        jsonObjectresponse = new JSONObject(response.body().string());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    imageid=jsonObjectresponse.optString("id");
                    Picasso.get().load(file).into(binding.ibUpload);




                }


            }
        });



    }
}
