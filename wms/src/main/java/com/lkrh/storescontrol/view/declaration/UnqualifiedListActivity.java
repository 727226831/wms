package com.lkrh.storescontrol.view.declaration;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.UnqualifiedBean;
import com.lkrh.storescontrol.databinding.ItemUnqualifiedBinding;
import com.lkrh.storescontrol.view.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnqualifiedListActivity extends BaseActivity {
    String ccode="PGD190227001";
    List<UnqualifiedBean> unqualifiedBeanList=new ArrayList<>();
    RecyclerView recyclerView;
    FunctionAdapter functionAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_list);
        Untils.initTitle("完工报单",this);
        recyclerView=findViewById(R.id.rv_list);
        getMeteriallist();
    }
    private void getMeteriallist() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetBHGInfo");
            jsonObject.put("acccode",acccode);
            jsonObject.put("ccode",ccode);
            jsonObject.put("cmocode",getIntent().getStringExtra("cmocode"));
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
                        Gson gson = new Gson();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                           unqualifiedBeanList.add(gson.fromJson(jsonElement, UnqualifiedBean.class));
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(UnqualifiedListActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(UnqualifiedListActivity.this,DividerItemDecoration.VERTICAL));
                        functionAdapter=new FunctionAdapter(unqualifiedBeanList);
                       recyclerView.setAdapter(functionAdapter);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
 public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.RecyclerViewHolder>{

        @NonNull
        @Override
        public FunctionAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemUnqualifiedBinding itemUnqualifiedBinding= DataBindingUtil.
                    inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.item_unqualified,viewGroup,false);

            return new FunctionAdapter.RecyclerViewHolder(itemUnqualifiedBinding.getRoot());
        }

        private List<UnqualifiedBean> mDatas;
        public FunctionAdapter(List<UnqualifiedBean> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull FunctionAdapter.RecyclerViewHolder vh, final int i) {
                ItemUnqualifiedBinding itemUnqualifiedBinding=DataBindingUtil.getBinding(vh.itemView);
                UnqualifiedBean unqualifiedBean=mDatas.get(i);
                itemUnqualifiedBinding.setUnqualifiedBean(unqualifiedBean);
                itemUnqualifiedBinding.executePendingBindings();


        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  RecyclerViewHolder extends RecyclerView.ViewHolder{

            public RecyclerViewHolder(View view) {
                super(view);
            }



        }
    }
}
