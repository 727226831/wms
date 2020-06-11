package com.lkrh.storescontrol.view.sale;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.lkrh.storescontrol.bean.DispatchBean;
import com.lkrh.storescontrol.view.BaseActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 销售出库
 */
public class DispatchActivity extends BaseActivity {
    RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;
    DispatchBean dispatchBean=new DispatchBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=getLayoutInflater().inflate(R.layout.activity_production_list,null,false);
        setContentView(v);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        recyclerView=findViewById(R.id.rv_list);

        getData();
    }
    private void getData() {

        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getDispatchBywhcode");
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
                        JSONObject object=new JSONObject(response.body().string());

                        if(object.getString("Resultcode").equals("200")) {
                            dispatchBean = new Gson().fromJson(response.body().string(), DispatchBean.class);
                            functionAdapter = new FunctionAdapter(dispatchBean.getData());
                            recyclerView.setLayoutManager(new LinearLayoutManager(DispatchActivity.this));
                            recyclerView.addItemDecoration(new DividerItemDecoration(DispatchActivity.this, DividerItemDecoration.VERTICAL));
                            recyclerView.setAdapter(functionAdapter);
                        }else {
                            Toast.makeText(DispatchActivity.this,object.getString("ResultMessage"),Toast.LENGTH_SHORT).show();
                        }

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
            View v=getLayoutInflater().inflate(R.layout.item_production_list,viewGroup,false);
            return new FunctionAdapter.VH(v);


        }

        private List<DispatchBean.Data> mDatas;
        public FunctionAdapter(List<DispatchBean.Data> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh,final int i) {
            vh.textViewcwhname.setText(mDatas.get(i).getCwhname());
            vh.textViewdate.setText(mDatas.get(i).getDdate());
            vh.textViewtag.setText(i+1+"");
            vh.textViewcTRCode.setText(mDatas.get(i).getCcode());
            vh.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DispatchActivity.this,DispatchdetailslistActivity.class);
                    intent.putExtra("id",mDatas.get(i).getID());
                    intent.putExtra("cwhcode",mDatas.get(i).getCwhcode());
                    intent.putExtra("ccode",mDatas.get(i).getCcode());
                    intent.putExtra("menuname",getIntent().getStringExtra("menuname"));
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
            TextView textViewcTRCode,textViewdate,textViewcwhname,textViewtag;
            LinearLayout linearLayout;
            public VH(@NonNull View itemView) {
                super(itemView);
                linearLayout=itemView.findViewById(R.id.l_input);
                textViewcTRCode=itemView.findViewById(R.id.tv_cTRCode);
                textViewdate=itemView.findViewById(R.id.tv_date);
                textViewcwhname=itemView.findViewById(R.id.tv_cWhName);
                textViewtag=itemView.findViewById(R.id.tv_number);


            }
        }
    }


}
