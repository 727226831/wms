package com.lkrh.storescontrol.view;

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

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.TROutBywhcodeBean;
import com.lkrh.storescontrol.databinding.ItemProductionListBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *2调拨入库
 * 3材料出库
 * 5调拨出库
 */
public class ProductionListActivity extends BaseActivity {
    RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;
    TROutBywhcodeBean trOutBywhcodeBean=new TROutBywhcodeBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=getLayoutInflater().inflate(R.layout.activity_production_list,null,false);
        setContentView(v);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        recyclerView=findViewById(R.id.rv_list);


    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {

        JSONObject jsonObject=new JSONObject();
        try {
            if(getIntent().getStringExtra("menuname").equals("调拨入库")){
                jsonObject.put("methodname","getTRInBywhcode");
            }else   if(getIntent().getStringExtra("menuname").equals("调拨出库")){
                jsonObject.put("methodname","getTROutBywhcode");
            }else if(getIntent().getStringExtra("menuname").equals("材料出库")){
                jsonObject.put("methodname","getMaterialOutBywhcode");
            }else if(getIntent().getStringExtra("menuname").equals("销售出库")){
                jsonObject.put("methodname","getDispatchBywhcode");
            }
            jsonObject.put("usercode",usercode);
            jsonObject.put("cwhcode","");
            jsonObject.put("acccode",acccode);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {

                        trOutBywhcodeBean=new Gson().fromJson(response.body().string(),TROutBywhcodeBean.class);
                        functionAdapter=new FunctionAdapter(trOutBywhcodeBean.getData());
                        recyclerView.setLayoutManager(new LinearLayoutManager(ProductionListActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(ProductionListActivity.this,DividerItemDecoration.VERTICAL));
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
  public   class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.VH>{

        @NonNull
        @Override
        public FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemProductionListBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.item_production_list,viewGroup,false);

            return new FunctionAdapter.VH(binding.getRoot());
        }

        private List<TROutBywhcodeBean.Data> mDatas;
        public FunctionAdapter(List<TROutBywhcodeBean.Data> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh,final int i) {
            ItemProductionListBinding binding=DataBindingUtil.getBinding(vh.itemView);
            TROutBywhcodeBean.Data data=mDatas.get(i);
            binding.setData(data);
            binding.executePendingBindings();
            binding.tvNumber.setText(i+1+"");
            binding.tvDate.setText(mDatas.get(i).getdDate());
            binding.lInput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ProductionListActivity.this,DetailListActivity.class);
                     intent.putExtra("id",mDatas.get(i).getID());
                     intent.putExtra("ccode",mDatas.get(i).getCCode());
                     intent.putExtra("menuname",getIntent().getStringExtra("menuname"));
                     intent.putExtra("ddate",mDatas.get(i).getdDate());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{

            public VH(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


}
