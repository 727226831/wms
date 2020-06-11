package com.lkrh.storescontrol.view;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.bean.ProductionOrderBean;
import com.lkrh.storescontrol.databinding.ActivityProductionOrderBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductionOrderActivity extends BaseActivity {
    LoginBean.Menu menuBean;
    ActivityProductionOrderBinding binding;
    String ordertype="标准";
    String orderclass="01";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ProductionOrderActivity.this,R.layout.activity_production_order);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        binding.bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etDepcode.getText().toString().isEmpty()){
                    Toast.makeText(ProductionOrderActivity.this,"请填写机台号",Toast.LENGTH_LONG).show();
                    return;
                }
                if(binding.etInvcode.getText().toString().isEmpty()){
                    Toast.makeText(ProductionOrderActivity.this,"请填写存货编码",Toast.LENGTH_LONG).show();
                    return;
                }
                if(binding.etQuantity.getText().toString().isEmpty()){
                    Toast.makeText(ProductionOrderActivity.this,"请填写数量",Toast.LENGTH_LONG).show();
                    return;
                }

                updateData("提交");
            }
        });
        binding.rgOrdertype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                 Button button=findViewById(i);
                 ordertype=button.getText().toString();
            }
        });
        binding.rgOrderclass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Button button=findViewById(i);
                orderclass=button.getText().toString();
            }
        });


    }

    private void updateData(String text) {

        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("button",text);
            jsonObject.put("condition","");
            ProductionOrderBean bean=new ProductionOrderBean();
            bean.setDepcode(binding.etDepcode.getText().toString());
            bean.setInvcode(binding.etInvcode.getText().toString());
            bean.setMemo(binding.etMemo.getText().toString());
            bean.setQuantity(binding.etQuantity.getText().toString());
            bean.setOrderclass(orderclass);
            bean.setOrdertype(ordertype);
            jsonObject.put("formdata",new Gson().toJson(bean));



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
                    Toast.makeText(ProductionOrderActivity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
                    if(confirmBean.getResultcode().equals("200")){
                        finish();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProductionOrderActivity.this,call.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
