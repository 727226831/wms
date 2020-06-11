package com.lkrh.storescontrol.view.confirm;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityConfirmBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.view.BaseActivity;
import com.lkrh.storescontrol.view.ScanActivity;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmActivity extends BaseActivity {
     ActivityConfirmBinding binding;
    ConfirmBean confirmBean;
    LoginBean.Menu menuBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_confirm);
        menuBean=getIntent().getParcelableExtra("menubean");
        Log.i("menubean",new Gson().toJson(menuBean));
        Untils.initTitle(menuBean.getMenushowname(),this);
        binding.ivCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScan();
            }
        });
        binding.etCode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.i("onkey","is run");
                    getData(binding.etCode.getText().toString());
                }

                return false;
            }
        });
        binding.bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmBean==null ||confirmBean.getFormdata()==null){
                    return;
                }
                confirmBean.getFormdata().setIquantity(binding.etIquantity.getText().toString());
                updateData();
            }
        });
        if(menuBean.getMenushowname().equals("破坏抽检")){
            binding.lDescribe.setVisibility(View.VISIBLE);
            layout="1";
        }else {

            layout="1";
        }


    }

    private void updateData() {
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout",layout);
            confirmBean.getFormdata().setDescribe(binding.etDescribe.getText().toString());
            jsonObject.put("formdata",new Gson().toJson(confirmBean.getFormdata()));
            jsonObject.put("button",binding.bSubmit.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ConfirmBean confirmBean=new Gson().fromJson(response.body().string(),ConfirmBean.class);
                    Toast.makeText(ConfirmActivity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
                    if(confirmBean.getResultcode().equals("200")){
                        binding.setBean(null);

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
    private String ccode="";
    private String irowno="";
    private String layout="";
    private void getData(String code) {
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());

            jsonObject.put("layout",layout);
            jsonObject.put("barcode",code);
            if(getIntent().getStringExtra("ccode")!=null){
                ccode=getIntent().getStringExtra("ccode");
                irowno=getIntent().getStringExtra("irowno");
            }

            jsonObject.put("ccode",ccode);
            jsonObject.put("irowno",irowno);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    confirmBean=new Gson().fromJson(response.body().string(),ConfirmBean.class);
                    Toast.makeText(ConfirmActivity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
                    Log.i("data-->",new Gson().toJson(confirmBean));
                    binding.setBean(confirmBean.getFormdata());
                    binding.etCode.setText("");
                    binding.etCode.requestFocus();
                    binding.etCode.setFocusable(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.getContents() != null) {
                String code = result.getContents();
                binding.etCode.setText(code);
                getData(code);
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void openScan() {

        new IntentIntegrator(ConfirmActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }
}
