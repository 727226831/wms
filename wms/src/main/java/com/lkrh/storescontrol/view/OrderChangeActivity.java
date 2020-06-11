package com.lkrh.storescontrol.view;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.url.iUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderChangeActivity extends BaseActivity {
    Button buttonSubmit;
    EditText editTextOrder;
    EditText editTextcount;
    LoginBean.Menu menuBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_change);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        menuBean=getIntent().getParcelableExtra("menubean");
        buttonSubmit=findViewById(R.id.b_submit);
        editTextcount=findViewById(R.id.et_count);
        editTextOrder=findViewById(R.id.et_order);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextcount.getText().toString().contains(".")){
                    Toast.makeText(OrderChangeActivity.this,"数量只能为整数",Toast.LENGTH_LONG).show();
                    return;
                }
                updateDocVouch();
            }
        });
    }
    private void updateDocVouch() {
        dialog.show();


        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("button",buttonSubmit.getText().toString());
            jsonObject.put("condition",editTextOrder.getText().toString());
            jsonObject.put("formdata",editTextcount.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String obj=jsonObject.toString();
        Log.i("json object",obj);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Request.URL).build();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),obj);

        iUrl login = retrofit.create(iUrl.class);
        retrofit2.Call<ResponseBody> data = login.getMessage(body);
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
                if(bean.getResultcode().equals("200")){
                    editTextcount.setText("");
                    editTextOrder.setText("");
                }

                Toast.makeText(OrderChangeActivity.this,bean.getResultMessage(),Toast.LENGTH_LONG).show();

                finish();

            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(OrderChangeActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            } });
    }
}
