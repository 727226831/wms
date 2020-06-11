package com.lkrh.storescontrol.view.packinginspetion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.bean.PackingInspetionBean;
import com.lkrh.storescontrol.databinding.ActivityPackingInspetionBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.url.iUrl;
import com.lkrh.storescontrol.view.BaseActivity;
import com.lkrh.storescontrol.view.ScanActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PackingInspetionActivity extends BaseActivity {
    ActivityPackingInspetionBinding binding;
    int type;
    LoginBean.Menu menuBean;

    int count=0;
    List<String> listCode=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(PackingInspetionActivity.this,R.layout.activity_packing_inspetion);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);

       binding.etBoxcode.setOnKeyListener(onKeyListener);
       binding.etCode.setOnKeyListener(onKeyListener);
       binding.etHscode.setOnKeyListener(onKeyListener);

       binding.ivBoxcode.setOnClickListener(onClickListener);
       binding.ivCode.setOnClickListener(onClickListener);
       binding.ivHscode.setOnClickListener(onClickListener);
       binding.bEnd.setOnClickListener(onClickListener);
       binding.bSearch.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           switch (view.getId()){
               case R.id.iv_boxcode:
                   type=0;
                   openScan();
                   break;
               case R.id.iv_code:
                   type=1;
                   openScan();
                   break;
               case R.id.iv_hscode:
                   type=2;
                   openScan();

                   break;
               case R.id.b_end:
                   if(binding.etBoxcode.getText().toString().isEmpty()){
                       Toast.makeText(PackingInspetionActivity.this, "客户箱码不能为空", Toast.LENGTH_LONG).show();
                       return;
                   }


                   updateDocVouch();
                   break;
               case R.id.b_search:
                   Intent intent=new Intent(PackingInspetionActivity.this,PackingInspetionListActivity.class);
                   intent.putExtra("menuname",getIntent().getStringExtra("menuname"));


                   intent.putExtra("listCode",listCode.toString());
                   startActivity(intent);
                   break;

           }
        }
    };
    private void openScan() {

        new IntentIntegrator(PackingInspetionActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String code=result.getContents();
                Log.i("scan",code);

                switch (type){
                    case 0://客户箱码
                        binding.etBoxcode.setText(code);
                        break;
                    case 1://条码
                       binding.etCode.setText(code);
                        break;
                    case 2://hs箱码
                        binding.etHscode.setText(code);
                        break;
                }

                getBarcodeValues(code,type);


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
    PackingInspetionBean bean;
    View.OnKeyListener onKeyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (v.getId()) {
                    case R.id.et_boxcode:
                         getBarcodeValues(binding.etBoxcode.getText().toString(),0);
                        break;
                    case R.id.et_code:

                        getBarcodeValues(binding.etCode.getText().toString(),1);
                        break;
                    case R.id.et_hscode:
                        getBarcodeValues(binding.etCode.getText().toString(),2);
                        break;

                }
            }

            return false;
        }
    };

    /**
     *
     * @param code
     * @param type 0客户箱码 1条码 2 瀚氏箱码
     */
    private void getBarcodeValues(String code, final int type) {
        if(type==1 && listCode.contains(binding.etCode.getText().toString())){
            Toast.makeText(PackingInspetionActivity.this,"此单件已装箱！", Toast.LENGTH_LONG).show();
            binding.etCode.setText("");
            return;
        }
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            if(type==2){
                jsonObject.put("layout","2");
            }else {
                jsonObject.put("layout","1");
            }

            jsonObject.put("barcode",code);

            jsonObject.put("ccode",binding.tvCusinvcode.getText().toString());


            jsonObject.put("irowno","");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("request-->",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    bean=new Gson().fromJson(response.body().string(),PackingInspetionBean.class);
                      switch (type){
                          case 0:
                             binding.tvCusinvcode.setText(bean.getFormdata().getCusinvcode());
                             binding.tvPackqty.setText(bean.getFormdata().getPackqty());
                             packqty=Integer.parseInt(bean.getFormdata().getPackqty());
                              break;
                          case 1:
                             if(bean.getResultcode().equals("200")){
                                 if(count<packqty) {
                                     count++;
                                     listCode.add(binding.etCode.getText().toString());
                                     binding.tvCount.setText(count+"");
                                     binding.etCode.setText("");
                                     binding.etCode.requestFocus();
                                 }else {
                                     Toast.makeText(PackingInspetionActivity.this,"已满足装箱数！", Toast.LENGTH_LONG).show();
                                 }

                             }else {
                                 Toast.makeText(PackingInspetionActivity.this, bean.getResultMessage(), Toast.LENGTH_LONG).show();
                             }
                              break;
                          case 2:
                              if(!bean.getResultcode().equals("200")){
                                  binding.etHscode.setText("");
                                  Toast.makeText(PackingInspetionActivity.this,bean.getResultMessage(), Toast.LENGTH_LONG).show();
                              }

                              break;
                      }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    int packqty;
    private void updateDocVouch() {
        if(listCode.size()!=packqty){
            Toast.makeText(PackingInspetionActivity.this, "已扫描数必须等于装箱数", Toast.LENGTH_LONG).show();
            return;
        }

        dialog.show();

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("button","结束");
            JSONObject jsonObjectCondition=new JSONObject();
            jsonObjectCondition.put("cusqrcode",binding.etBoxcode.getText().toString());
            jsonObjectCondition.put("hsqrcode",binding.etHscode.getText().toString());
            jsonObject.put("condition",new Gson().toJson(jsonObjectCondition));

            JSONArray jsonArray=new JSONArray();

            for (int i = 0; i <listCode.size() ; i++) {
                JSONObject object=new JSONObject();
                object.put("barcode",listCode.get(i));
                jsonArray.put(object);
            }
            jsonObject.put("formdata",jsonArray.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String obj=jsonObject.toString();
        Log.i("json object",obj);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Request.URL).build();
        final RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);

        iUrl login = retrofit.create(iUrl.class);
        retrofit2.Call<ResponseBody> data = login.getMessage(body);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                try {
                    bean=new Gson().fromJson(response.body().string(),PackingInspetionBean.class);

                   binding.etBoxcode.setText("");
                   binding.etHscode.setText("");
                   binding.tvCusinvcode.setText("");
                   binding.tvPackqty.setText("");
                   binding.etCode.setText("");
                   binding.tvCount.setText("");
                   listCode.clear();
                   binding.etBoxcode.requestFocus();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(PackingInspetionActivity.this, bean.getResultMessage(), Toast.LENGTH_LONG).show();


            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(PackingInspetionActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            } });
    }
}
