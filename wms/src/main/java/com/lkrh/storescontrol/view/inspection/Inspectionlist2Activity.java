package com.lkrh.storescontrol.view.inspection;

import android.content.DialogInterface;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ItemDhsjBinding;
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

public class Inspectionlist2Activity extends BaseActivity {
    RecyclerView recyclerView;
    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;
    Button buttontag;
    Confirm2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=getLayoutInflater().inflate(R.layout.activity_inspectionlist2,null,false);
        setContentView(v);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
        recyclerView=findViewById(R.id.rv_list);
        buttontag=findViewById(R.id.b_tag);
        buttontag.setOnClickListener(onClickListener);
        Button buttonurgent=findViewById(R.id.b_urgent);
        Button buttonnormal=findViewById(R.id.b_normal);
        buttonnormal.setOnClickListener(onClickListener);
        buttonurgent.setOnClickListener(onClickListener);



    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
       switch (v.getId()){
           case R.id.b_tag:
               AlertDialog.Builder builder=new AlertDialog.Builder(Inspectionlist2Activity.this);
               builder.setTitle("请选择送检区!").setSingleChoiceItems(new String[]{"待检区一","待检区二","待检区三","待检区四"}, 0, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       for (int i = 0; i <list.size() ; i++) {
                           if(list.get(i).isSelect()){
                               list.get(i).setField8value("待检区"+(which+1));
                               list.get(i).setField8text("待检区"+(which+1));
                               list.get(i).setSelect(false);
                           }
                       }
                       adapter = new Confirm2Adapter(list);
                       recyclerView.setAdapter(adapter);
                       dialog.dismiss();
                   }
               }).show();


               break;
           case R.id.b_urgent:
               updateData("急料送检");
               break;

           case R.id.b_normal:
               updateData("普通送检");
               break;
       }
        }
    };

    private void updateData(String button) {
        dialog.show();
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","2");
            jsonObject.put("button",button);
            jsonObject.put("condition","");
            jsonObject.put("formdata",new Gson().toJson(list));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                   dialog.dismiss();
                try {
                    if(response.code()==200) {
                        ConfirmBean confirmBean=new Gson().fromJson(response.body().string(),ConfirmBean.class);
                        Toast.makeText(Inspectionlist2Activity.this,confirmBean.getResultMessage(),Toast.LENGTH_SHORT).show();
                        if(confirmBean.getResultcode().equals("200")){
                            finish();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                  dialog.dismiss();
            } });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onstart","is run");
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
                         adapter = new Confirm2Adapter(list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Inspectionlist2Activity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(Inspectionlist2Activity.this, DividerItemDecoration.VERTICAL));
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
        ItemDhsjBinding binding;
        @NonNull
        @Override
        public Confirm2Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_dhsj,viewGroup,false);
            return new VH(binding.getRoot());
        }
        private List<ConfirmlistBean> mDatas;

        public Confirm2Adapter(List<ConfirmlistBean> data) {
            this.mDatas = data;

        }



        @Override
        public void onBindViewHolder(@NonNull Confirm2Adapter.VH vh, final int i) {
            binding=DataBindingUtil.getBinding(vh.itemView);
            binding.tvTag.setText(i+1+"");
            ConfirmlistBean data=mDatas.get(i);
            binding.setData(data);
            binding.executePendingBindings();
            if(mDatas.get(i).isSelect()){

                binding.ivSelect.setImageDrawable(getResources().getDrawable(R.mipmap.ic_selected));
            }else {
                binding.ivSelect.setImageDrawable(getResources().getDrawable(R.mipmap.ic_select));
            }

            binding.rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(list.get(i).isSelect()){
                        list.get(i).setSelect(false);
                    }else {
                        list.get(i).setSelect(true);
                    }

                     mDatas=list;
                     notifyDataSetChanged();
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
