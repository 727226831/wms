package com.lkrh.storescontrol.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.DetailsBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailListActivity extends BaseActivity {
    RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;
    Button buttonSubmit,buttonscan;
    TextView textViewTotal;
    DetailsBean detailsBean=new DetailsBean();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
       Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        recyclerView=findViewById(R.id.rv_list);
        buttonSubmit=findViewById(R.id.b_submit);
        textViewTotal=findViewById(R.id.tv_total);
        buttonscan=findViewById(R.id.b_scan);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkData();
            }
        });
        buttonscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DetailListActivity.this,ProductionwarehousingActivity.class);
                intent.putExtra("menuname",getIntent().getStringExtra("menuname"));
                intent.putExtra("detailsBean",detailsBean);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("checklist","").commit();
        sharedPreferences.edit().putString("checkscan","").commit();
        getData();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!sharedPreferences.getString("detailsBean","").equals("")){
            detailsBean= new Gson().fromJson(sharedPreferences.getString("detailsBean",""),DetailsBean.class);
            functionAdapter=new FunctionAdapter(detailsBean.getData());
            recyclerView.setLayoutManager(new LinearLayoutManager(DetailListActivity.this));
            recyclerView.addItemDecoration(new DividerItemDecoration(DetailListActivity.this,DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(functionAdapter);
        }

    }

    private void checkData() {
        for (int i = 0; i <detailsBean.getData().size() ; i++) {
              if(!detailsBean.getData().get(i).getIncomplete().equals("0")){
                  Toast.makeText(DetailListActivity.this,"有未扫码条目，请完成后再提交",Toast.LENGTH_LONG).show();
                  return;
              }

        }

        JSONObject jsonObject=new JSONObject();
        try {
            if(getIntent().getStringExtra("menuname").equals("调拨入库")){
                jsonObject.put("methodname","CheckTRInByccode");
            }else   if(getIntent().getStringExtra("menuname").equals("调拨出库")){
                jsonObject.put("methodname","CheckTROutByccode");
            } if(getIntent().getStringExtra("menuname").equals("材料出库")){
                jsonObject.put("methodname","CheckMaterialOutByccode");
            }
            jsonObject.put("usercode",usercode);
            jsonObject.put("id",getIntent().getStringExtra("id"));
            jsonObject.put("acccode",acccode);
            jsonObject.put("ccode",getIntent().getStringExtra("ccode"));
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
                        Toast.makeText(DetailListActivity.this,response.body().string(),Toast.LENGTH_LONG).show();

                        sharedPreferences.edit().putString("checklist","").commit();
                        sharedPreferences.edit().putString("checkscan","").commit();
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }

    private void getData() {

        JSONObject jsonObject=new JSONObject();
        try {
            if(getIntent().getStringExtra("menuname").equals("调拨入库")){
                jsonObject.put("methodname","getTRInDetailsByccode");
            }else   if(getIntent().getStringExtra("menuname").equals("调拨出库")){
                jsonObject.put("methodname","getTROutDetailsByccode");
            } if(getIntent().getStringExtra("menuname").equals("材料出库")){
                jsonObject.put("methodname","getMaterialOutDetailsByccode");
            }
              jsonObject.put("usercode",usercode);
              jsonObject.put("id",getIntent().getStringExtra("id"));
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
                        detailsBean=new Gson().fromJson(response.body().string(),DetailsBean.class);

                        for (int i = 0; i <detailsBean.getData().size() ; i++) {
                            if(detailsBean.getData().get(i).getCompleted()==null){
                                detailsBean.getData().get(i).setCompleted("0");
                                detailsBean.getData().get(i).setIncomplete(detailsBean.getData().get(i).getIQuantity());
                            }
                        }
                        sharedPreferences.edit().putString("detailsBean",new Gson().toJson(detailsBean)).commit();

                        functionAdapter=new FunctionAdapter(detailsBean.getData());
                        recyclerView.setLayoutManager(new LinearLayoutManager(DetailListActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(DetailListActivity.this,DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(functionAdapter);

                        textViewTotal.setText("总计："+detailsBean.getData().size()+"条");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
    class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.VH>{

        @NonNull
        @Override
        public FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v=getLayoutInflater().inflate(R.layout.item_detail_list,viewGroup,false);
            return new FunctionAdapter.VH(v);
        }

        private List<DetailsBean.Data> mDatas;
        public FunctionAdapter(List<DetailsBean.Data> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull FunctionAdapter.VH vh, final int i) {

            vh.textViewDetails.setText(mDatas.get(i).getCInvCode());
            vh.textViewcposition.setText(mDatas.get(i).getCposition());
            vh.textViewtag.setText(i+1+"");
            vh.textViewcBatch.setText("批号："+mDatas.get(i).getCBatch());
            vh.textViewcInvName.setText("名称："+mDatas.get(i).getCInvName()+"/"+mDatas.get(i).getCInvStd());
            vh.textViewiQuantity.setText("数量："+mDatas.get(i).getIQuantity());
            vh.textViewcompleted.setText("已扫码："+mDatas.get(i).getCompleted());
            vh.textViewincomplete.setText("未扫码："+ mDatas.get(i).getIncomplete());

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
           TextView textViewcposition,textViewDetails,textViewiQuantity,textViewtag,
                   textViewcBatch,textViewcInvName,textViewcompleted,textViewincomplete,
                   textViewCInvStd;
            LinearLayout linearLayout;
            public VH(@NonNull View itemView) {
                super(itemView);
                linearLayout=itemView.findViewById(R.id.l_input);
               textViewcposition=itemView.findViewById(R.id.tv_cposition);
               textViewDetails=itemView.findViewById(R.id.tv_details);
               textViewiQuantity=itemView.findViewById(R.id.tv_iQuantity);
               textViewtag=itemView.findViewById(R.id.tv_tag);
               textViewcBatch=itemView.findViewById(R.id.tv_cBatch);
               textViewcInvName=itemView.findViewById(R.id.tv_cInvName);
               textViewcompleted=itemView.findViewById(R.id.tv_completed);
               textViewincomplete=itemView.findViewById(R.id.tv_incomplete);
               textViewCInvStd=itemView.findViewById(R.id.tv_CInvStd);
            }
        }
    }
}
