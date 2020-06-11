package com.lkrh.storescontrol.view.test;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityTestBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.view.BaseActivity;
import com.lkrh.storescontrol.view.ScanActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends BaseActivity {

    ActivityTestBinding binding;
    ConfirmBean confirmBean;
    LoginBean.Menu menuBean;
    EditText editTextiquantity;
    String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_test);
        menuBean=getIntent().getParcelableExtra("menubean");
        editTextiquantity=findViewById(R.id.et_iquantity);
        Log.i(" menuBean",new Gson().toJson(menuBean));
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
        binding.bChoose.setOnClickListener(onClickListener);
        //初检 合格 待定
        //挑选  合格 不合格
        //复检 确定
        if(menuBean.getMenushowname().equals("到货初检")){
            if(company.equals("浦东瀚氏")){
                binding.rgCheckhs.setVisibility(View.VISIBLE);
                binding.rlMrn.setVisibility(View.GONE);
                binding.rlCwhcode.setVisibility(View.GONE);
                getData("");
            }else {
                binding.rgChecklincon.setVisibility(View.VISIBLE);

            }

        }else if(menuBean.getMenushowname().equals("到货挑选")){

            binding.rlMrn.setVisibility(View.GONE);
            binding.tvIquantity.setText("合格数量:");
            binding.rgPick.setVisibility(View.VISIBLE);
        }else if(menuBean.getMenushowname().equals("到货复检")){
           binding.rgRecheck.setVisibility(View.VISIBLE);


        }

        binding.rgRecheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("i",checkedId+"");
                switch (checkedId){

                    case R.id.rb_recheck1:
                        result="退货";
                        break;
                    case R.id.rb_recheck2:
                        result="挑选";
                        break;
                    case R.id.rb_recheck3:
                        result="特采";
                        break;
                    case R.id.rb_recheck4:
                        result="复选";
                        break;
                }
            }
        });
        binding.rgCheckhs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.i("i",i+"");
                switch (i){
                    case R.id.rb_chechhs1:
                        result="合格";
                        break;
                    case R.id.rb_chechhs2:
                        result="特采";
                        break;
                    case R.id.rb_chechhs3:
                        result="拒收";
                        break;
                }
            }
        });
        binding.rgChecklincon.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_checklincon1:
                        result="合格";
                        break;
                    case R.id.rb_checklincon2:
                        result="待定";
                        break;
                }
            }
        });
        binding.rgPick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.i("i",i+"");
                switch (i){
                    case R.id.rb_pick1:
                        result="合格";
                        break;
                    case R.id.rb_pick2:
                        result="不合格";
                        break;
                }
            }
        });




    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case  R.id.b_choose:
                   Log.i("result",result);
                   if(result.isEmpty()){
                       Toast.makeText(TestActivity.this,"请选择结果",Toast.LENGTH_SHORT).show();
                       return;
                   }
                   if(Double.parseDouble(binding.etIquantity.getText().toString())>Double.parseDouble(confirmBean.getFormdata().getIquantity())){
                       Toast.makeText(TestActivity.this,"数量不能大于送检数量",Toast.LENGTH_SHORT).show();
                       return;
                   }
                   updateData(result);
                   break;
           }

        }
    };

    private void updateData(String  type) {
        if(confirmBean==null){
            return;
        }
        confirmBean.getFormdata().setIquantity(editTextiquantity.getText().toString());
         JSONObject jsonObject=new JSONObject();
        try {
            if(menuBean.getMenushowname().equals("到货挑选")) {
                jsonObject.put("methodname","updateBarcodeValues");
            }else {
                jsonObject.put("methodname","updateDocVouch");
            }
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","2");

            jsonObject.put("button",type);
            jsonObject.put("condition",getIntent().getStringExtra("condition"));
            confirmBean.getFormdata().setDescribe(binding.etDescribe.getText().toString());
            confirmBean.getFormdata().setMrn(binding.etMrn.getText().toString());
            jsonObject.put("formdata",new Gson().toJson(confirmBean.getFormdata()));



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
                    Toast.makeText(TestActivity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
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

    private void getData(String code) {
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","2");
            jsonObject.put("barcode",code);
            jsonObject.put("ccode",getIntent().getStringExtra("ccode"));
            jsonObject.put("irowno",getIntent().getStringExtra("irowno"));


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
                    Toast.makeText(TestActivity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
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

        new IntentIntegrator(TestActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }
}
