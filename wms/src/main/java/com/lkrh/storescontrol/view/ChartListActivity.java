package com.lkrh.storescontrol.view;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ItemCkkbBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartListActivity extends BaseActivity {
    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;
    RecyclerView recyclerView;
    Confirm2Adapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_list);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        menuBean=getIntent().getParcelableExtra("menubean");
        recyclerView=findViewById(R.id.rv_list);
        getData();
    }
    private void getData() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getMenuFieldAndValue");
            jsonObject.put("usercode",usercode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("condition","");
            jsonObject.put("barcode","");
            jsonObject.put("formdata","");
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
                        list=new ArrayList<>();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            list.add(new Gson().fromJson(jsonElement, ConfirmlistBean.class));
                        }
                        adapter = new Confirm2Adapter(list, ChartListActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ChartListActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(ChartListActivity.this, DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);




                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
    public class Confirm2Adapter extends RecyclerView.Adapter<Confirm2Adapter.VH>  {
        ItemCkkbBinding binding;
        @NonNull
        @Override
        public Confirm2Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_ckkb,viewGroup,false);
            return new Confirm2Adapter.VH(binding.getRoot());
        }
        private List<ConfirmlistBean> mDatas;
        private Context context;
        public Confirm2Adapter(List<ConfirmlistBean> data, Context context) {
            this.mDatas = data;
            this.context=context;
        }



        @Override
        public void onBindViewHolder(@NonNull Confirm2Adapter.VH vh, final int i) {
            binding=DataBindingUtil.getBinding(vh.itemView);
            binding.tvTag.setText(i+1+"");
            final ConfirmlistBean data=mDatas.get(i);
            binding.setData(data);
            binding.executePendingBindings();
            binding.rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                     /**知道要跳转应用的包命与目标Activity*/
                    if(menuBean.getMenushowname().equals("库存看板")) {
                        ComponentName componentName = new ComponentName("com.example.wkftv", "com.example.wkftv.HsActivity");
                        intent.setComponent(componentName);
                        intent.putExtra("title", "库存看板");
                        intent.putExtra("condition",mDatas.get(i).getField1value());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }else {
                        intent = new Intent(ChartListActivity.this, ProductionwarehousingActivity.class);
                        intent.putExtra("menuname",menuBean.getMenushowname());
                        intent.putExtra("menubean",menuBean);
                        intent.putExtra("category",mDatas.get(i).getField1value());
                    }

                    startActivity(intent);

                }
            });




        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public class VH extends RecyclerView.ViewHolder {
            public VH(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
