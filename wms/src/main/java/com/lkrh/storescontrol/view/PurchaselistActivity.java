package com.lkrh.storescontrol.view;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.adapter.PurchaseAdapter;
import com.lkrh.storescontrol.bean.PurchaselistBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaselistActivity extends BaseActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchaselist);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        recyclerView=findViewById(R.id.rv_list);
        getData();
    }
    private void getData() {

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","GetPuArrInfo");
            jsonObject.put("acccode",acccode);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        PurchaselistBean purchaselistBean=new Gson().fromJson(response.body().string(),PurchaselistBean.class);
                        if(purchaselistBean.getData()==null){
                            Toast.makeText(PurchaselistActivity.this, "无待入库信息", Toast.LENGTH_LONG).show();
                            return;
                        }
                        PurchaseAdapter purchaseAdapter=new PurchaseAdapter(purchaselistBean.getData(),PurchaselistActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PurchaselistActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(PurchaselistActivity.this,DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(purchaseAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }

}
