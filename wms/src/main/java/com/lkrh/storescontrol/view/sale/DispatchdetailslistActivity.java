package com.lkrh.storescontrol.view.sale;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.CreatesaleoutBean;
import com.lkrh.storescontrol.bean.DispatchdetailsBean;
import com.lkrh.storescontrol.view.BaseActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispatchdetailslistActivity extends BaseActivity {
    RecyclerView recyclerView;
    private FunctionAdapter functionAdapter;
    Button buttonSubmit;
    TextView textViewTotal;

    DispatchdetailsBean dispatchdetailsBean=new DispatchdetailsBean();
    List<CreatesaleoutBean> createsaleoutBeanArrayList=new ArrayList<>();
    ArrayList<String> arrayListselect=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchdetailslist);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        recyclerView=findViewById(R.id.rv_list);
        buttonSubmit=findViewById(R.id.b_submit);
        textViewTotal=findViewById(R.id.tv_total);
        getDispatchDetailsByccode();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createSaleOut();
            }
        });
    }

    private void createSaleOut() {
           setData();
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","CreateSaleOut");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            JSONArray jsonArray=new JSONArray(new Gson().toJson(createsaleoutBeanArrayList));
            jsonObject.put("datatetails",jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj = jsonObject.toString();
        Log.i("json object", obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        Toast.makeText(DispatchdetailslistActivity.this,response.body().string(),Toast.LENGTH_LONG).show();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }

    private void setData() {


        for (int i = 0; i <arrayListselect.size() ; i++) {
             CreatesaleoutBean createsaleoutBean=new CreatesaleoutBean();
             createsaleoutBean.setId(getIntent().getStringExtra("id"));
             createsaleoutBean.setCcode(getIntent().getStringExtra("ccode"));
             createsaleoutBean.setCwhcode(getIntent().getStringExtra("cwhcode"));
             if(dispatchdetailsBean.getData().get(Integer.parseInt(arrayListselect.get(i))).getBWhPos()==1){
                 createsaleoutBean.setCposcode("");
             }
            createsaleoutBean.setRowno(dispatchdetailsBean.getData().get(Integer.parseInt(arrayListselect.get(i))).getIrowno());
            createsaleoutBean.setCinvcode(dispatchdetailsBean.getData().get(Integer.parseInt(arrayListselect.get(i))).getCinvcode());
            createsaleoutBean.setCbatch(dispatchdetailsBean.getData().get(Integer.parseInt(arrayListselect.get(i))).getCbatch());
            createsaleoutBean.setIquantity(dispatchdetailsBean.getData().get(Integer.parseInt(arrayListselect.get(i))).getIquantity());
            createsaleoutBeanArrayList.add(createsaleoutBean);

        }

    }

    private void getDispatchDetailsByccode() {

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getDispatchDetailsByccode");
            jsonObject.put("id",getIntent().getStringExtra("id"));
            jsonObject.put("acccode",acccode);
            jsonObject.put("cwhcode",getIntent().getStringExtra("cwhcode"));
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
                       dispatchdetailsBean=new Gson().fromJson(response.body().string(),DispatchdetailsBean.class);
                        functionAdapter=new FunctionAdapter(dispatchdetailsBean.getData());
                        recyclerView.setLayoutManager(new LinearLayoutManager(DispatchdetailslistActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(DispatchdetailslistActivity.this,DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(functionAdapter);
                       // textViewTotal.setText("总计："+dispatchdetailsBean.getData().size()+"条");
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
            View v=getLayoutInflater().inflate(R.layout.item_dispatchdetails,viewGroup,false);
            return new FunctionAdapter.VH(v);
        }

        private List<DispatchdetailsBean.Data> mDatas;
        public FunctionAdapter(List<DispatchdetailsBean.Data> data) {
            this.mDatas = data;

        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh,final int i) {
            vh.textViewnumber.setText(i+1+"");
            vh.textViewcinvname.setText("品名："+mDatas.get(i).getCinvname());
            vh.textViewirowno.setText("行号："+mDatas.get(i).getIrowno());
            vh.textViewbWhPos.setText("库号："+mDatas.get(i).getBWhPos());
            vh.textViewcinvcode.setText("料号："+mDatas.get(i).getCinvcode());
            vh.textViewcbatch.setText("批号："+mDatas.get(i).getCbatch());
            vh.textViewiquantity.setText("数量："+mDatas.get(i).getIquantity());
            vh.textViewcinvstd.setText("规格型号："+mDatas.get(i).getCinvstd());
            vh.checkBoxselect.setTag(""+i);

            vh.checkBoxselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                      if(isChecked){
                         arrayListselect.add(buttonView.getTag().toString());

                      }else {
                          arrayListselect.remove(buttonView.getTag().toString());

                      }
                    Log.i("select--->",arrayListselect.toString());
                    textViewTotal.setText("总计："+arrayListselect.size()+"条");
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
            
            TextView textViewcinvname,textViewirowno,textViewbWhPos,textViewcinvcode,textViewcbatch,
                    textViewiquantity,textViewcinvstd,textViewnumber;
            RelativeLayout relativeLayout;
            CheckBox checkBoxselect;
            VH(@NonNull View itemView) {
                super(itemView);
                checkBoxselect=itemView.findViewById(R.id.cb_select);
                relativeLayout=itemView.findViewById(R.id.l_input);
                textViewnumber=itemView.findViewById(R.id.tv_number);
                textViewcinvname=itemView.findViewById(R.id.tv_cinvname);
                textViewirowno=itemView.findViewById(R.id.tv_irowno);
                textViewbWhPos=itemView.findViewById(R.id.tv_bWhPos);
                textViewcinvcode=itemView.findViewById(R.id.tv_cinvcode);
                textViewcbatch=itemView.findViewById(R.id.tv_cBatch);
                textViewiquantity=itemView.findViewById(R.id.tv_iquantity);
                textViewcinvstd=itemView.findViewById(R.id.tv_cinvstd);

            }
        }
    }
}
