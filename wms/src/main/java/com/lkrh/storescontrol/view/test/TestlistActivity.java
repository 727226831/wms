package com.lkrh.storescontrol.view.test;

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
import com.lkrh.storescontrol.databinding.ItemDhcjBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.view.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestlistActivity extends BaseActivity {

    RecyclerView recyclerView;
    LoginBean.Menu menuBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=getLayoutInflater().inflate(R.layout.activity_testlist,null,false);
        setContentView(v);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
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
            jsonObject.put("condition",getIntent().getStringExtra("condition"));
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
                        List<ConfirmlistBean> list=new ArrayList<>();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            list.add(new Gson().fromJson(jsonElement, ConfirmlistBean.class));
                        }
                        Confirm1Adapter adapter = new Confirm1Adapter(list, TestlistActivity.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TestlistActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(TestlistActivity.this, DividerItemDecoration.VERTICAL));
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

    class Confirm1Adapter extends RecyclerView.Adapter<Confirm1Adapter.VH> {
        ItemDhcjBinding binding;
        @NonNull
        @Override
        public Confirm1Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_dhcj,viewGroup,false);
            return new Confirm1Adapter.VH(binding.getRoot());
        }
        private List<ConfirmlistBean> mDatas;
        private Context context;
        public Confirm1Adapter(List<ConfirmlistBean> data, Context context) {
            this.mDatas = data;
            this.context=context;
        }



        @Override
        public void onBindViewHolder(@NonNull VH vh, final int i) {
             binding=DataBindingUtil.getBinding(vh.itemView);


            binding.tvTag.setText(i+1+"");
            ConfirmlistBean data=mDatas.get(i);

            binding.setData(data);
            binding.executePendingBindings();
            binding.rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, TestActivity.class);
                    intent.putExtra("menubean",getIntent().getParcelableExtra("menubean"));
                    intent.putExtra("ccode",mDatas.get(i).getField9value());
                    intent.putExtra("irowno",mDatas.get(i).getField10value());
                    intent.putExtra("condition",new Gson().toJson(mDatas.get(i)));
                    context.startActivity(intent);
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
