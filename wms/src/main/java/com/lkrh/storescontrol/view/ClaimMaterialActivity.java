package com.lkrh.storescontrol.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.lkrh.storescontrol.databinding.ActivityClaimMaterialBinding;
import com.lkrh.storescontrol.databinding.ItemInspectionBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClaimMaterialActivity extends BaseActivity{
    ActivityClaimMaterialBinding binding;
    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;
    List<ConfirmlistBean> listSubmit=new ArrayList<>();
    Confirm2Adapter adapter;
    double total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(ClaimMaterialActivity.this,R.layout.activity_claim_material);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        menuBean=getIntent().getParcelableExtra("menubean");
        binding.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMenuFieldAndValue();
            }
        });
        binding.bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etCwhcode.getText().toString().isEmpty()){
                    Toast.makeText(ClaimMaterialActivity.this,"仓库不能为空",Toast.LENGTH_LONG).show();
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
            jsonObject.put("button","提交");
            jsonObject.put("condition",binding.etCwhcode.getText().toString());
            jsonObject.put("formdata",new Gson().toJson(listSubmit));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
         Log.i("requst-->",obj);
        Call<ResponseBody> data = Request.getRequestbody(obj);
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
                    listSubmit.clear();

                }

                Toast.makeText(ClaimMaterialActivity.this,bean.getResultMessage(),Toast.LENGTH_LONG).show();



            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ClaimMaterialActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            } });
    }
    private void getMenuFieldAndValue() {
        dialog.show();
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getMenuFieldAndValue");
            jsonObject.put("usercode",usercode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            JSONObject object=new JSONObject();
            object.put("ccode",binding.etCcode.getText().toString());
            object.put("cinvcode",binding.etCinvcode.getText().toString());
            object.put("invcode",binding.etInvcode.getText().toString());
            jsonObject.put("condition",object.toString());
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
                    dialog.dismiss();
                    if(response.code()==200) {
                        list=new ArrayList<>();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            list.add(new Gson().fromJson(jsonElement, ConfirmlistBean.class));
                        }
                        adapter = new Confirm2Adapter(list, ClaimMaterialActivity.this);
                        binding.rvList.setLayoutManager(new LinearLayoutManager(ClaimMaterialActivity.this));
                        binding.rvList.addItemDecoration(new DividerItemDecoration(ClaimMaterialActivity.this, DividerItemDecoration.VERTICAL));
                        binding.rvList.setAdapter(adapter);


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
    public class Confirm2Adapter extends RecyclerView.Adapter<Confirm2Adapter.VH>  {

        @NonNull
        @Override
        public Confirm2Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            ItemInspectionBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_inspection,viewGroup,false);
            return new Confirm2Adapter.VH(binding.getRoot());
        }
        private List<ConfirmlistBean> mDatas;
        private Context context;
        public Confirm2Adapter(List<ConfirmlistBean> data, Context context) {
            this.mDatas = data;
            this.context=context;
        }



        @Override
        public void onBindViewHolder(@NonNull Confirm2Adapter.VH vh, final int position) {

            final ItemInspectionBinding itemPurchaseBinding=DataBindingUtil.getBinding(vh.itemView);
            itemPurchaseBinding.tvTag.setText( position+1+"");
            ConfirmlistBean data=mDatas.get( position);
            itemPurchaseBinding.setData(data);
            itemPurchaseBinding.executePendingBindings();
            if(mDatas.get( position).isSelect()){

                itemPurchaseBinding.ivSelect.setImageDrawable(getResources().getDrawable(R.mipmap.ic_selected));
            }else {
                itemPurchaseBinding.ivSelect.setImageDrawable(getResources().getDrawable(R.mipmap.ic_select));
            }
            itemPurchaseBinding.ivSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.get( position).isSelect()){
                        list.get( position).setSelect(false);
                        total=total-Double.parseDouble((list.get(position).getField5value()));
                        binding.tvTotal.setText("总数："+total);
                        listSubmit.remove(position);
                    }else {
                        list.get( position).setSelect(true);
                        listSubmit.add(mDatas.get(position));
                        total=total+Double.parseDouble(list.get(position).getField5value());
                        binding.tvTotal.setText("总数："+total);
                    }

                    mDatas=list;
                    notifyDataSetChanged();
                }
            });

            itemPurchaseBinding.rlLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et = new EditText(ClaimMaterialActivity.this);
                    et.setInputType(InputType.TYPE_CLASS_NUMBER);
                    new AlertDialog.Builder(ClaimMaterialActivity.this).setTitle("请输入修改数量")
                            .setIcon(android.R.drawable.sym_def_app_icon)
                            .setView(et)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    Log.i("position",position+"");
                                    if(list.get(position).isSelect()){
                                        total=total-Double.parseDouble(list.get(position).getField5value())+Integer.parseInt(et.getText().toString());
                                    }
                                    binding.tvTotal.setText("总数："+total);
                                    list.get(position).setField5text(et.getText().toString());
                                    list.get(position).setField5value(et.getText().toString());

                                    mDatas=list;
                                    notifyDataSetChanged();



                                }
                            }).setNegativeButton("取消",null).show();

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
