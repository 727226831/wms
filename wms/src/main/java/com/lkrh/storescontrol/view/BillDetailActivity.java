package com.lkrh.storescontrol.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.bean.WarehouseBean;
import com.lkrh.storescontrol.databinding.ActivityBillDetailBinding;

import com.lkrh.storescontrol.databinding.ItemDetailsBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillDetailActivity extends BaseActivity {

    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;
    Confirm2Adapter adapter;

    SharedPreferences sharedPreferences;
    SharedPreferences sp;
    ActivityBillDetailBinding binding;
    WarehouseBean warehouseBean;
    String data;
    String qrCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(BillDetailActivity.this,R.layout.activity_bill_detail);


        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);


        if(        !menuBean.getMenushowname().equals("委外发料")
                && !menuBean.getMenushowname().equals("补料发料")
                && !menuBean.getMenushowname().equals("采购入库")
                && !menuBean.getMenushowname().equals("销售出库")
                && !menuBean.getMenushowname().equals("备货调拨")
        ){
            if(!company.equals("新傲科技")){
                binding.lBottom.setVisibility(View.VISIBLE);
                binding.bSubmit.setVisibility(View.VISIBLE);
            }

            if(company.equals("浦东瀚氏") && menuBean.getMenushowname().equals("材料出库")){
                binding.bDelete.setVisibility(View.VISIBLE);
            }
           binding.bSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //判断是否有未扫描条码
                    if(menuBean.getMenushowname().equals("销售发货")){
                        for (int i = 0; i <list.size() ; i++) {
                            int undone=Integer.parseInt(list.get(i).getField8value());
                            if(undone!=0){
                                Toast.makeText(BillDetailActivity.this,"有未扫码条目，请完成后再提交",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }

                        updateData( binding.bSubmit.getText().toString());

                    }else {
                        updateData( binding.bSubmit.getText().toString());
                    }

                }
            });
        }
        if(menuBean.getMenushowname().equals("其他入库确认")
                ||menuBean.getMenushowname().equals("生产入库确认")

        ){
          binding.rlCwhcode.setVisibility(View.VISIBLE);
          binding.ivHelp.setVisibility(View.VISIBLE);
        }


        getData();
        binding.etCwhcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()){
                    binding.ivClear.setVisibility(View.GONE);
                }else {
                    binding.ivClear.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.etCwhcode.setOnKeyListener(onKeyListener);
        binding.ivCwhcode.setOnClickListener(onClickListener);
        binding.ivClear.setOnClickListener(onClickListener);
        binding.ivHelp.setOnClickListener(onClickListener);

        sp=getSharedPreferences("sp",0);
        if(menuBean.getMenushowname().equals("销售发货")) {
            sp.edit().putString(menuBean.getMenucode() + "List", "").commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String code=result.getContents();
                Log.i("scan",code);
                if (code.contains("$")) {
                    Toast.makeText(BillDetailActivity.this, "类型错误", Toast.LENGTH_LONG).show();
                } else {
                    binding.etCwhcode.setText(code);
                    getBarcodeValues(code, 0);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             switch (view.getId()) {

                 case R.id.iv_cwhcode:

                     openScan();
                     break;
                 case R.id.iv_clear:
                     binding.etCwhcode.setText("");
                     break;
                 case R.id.iv_help:

                     Intent intent=new Intent(BillDetailActivity.this,HelpActivity.class);
                     intent.putExtra("cwhcode",binding.etCwhcode.getText().toString());
                     startActivity(intent);
                     break;

             }
         }
     };
     View.OnKeyListener onKeyListener=new View.OnKeyListener() {
         @Override
         public boolean onKey(View v, int keyCode, KeyEvent event) {
             if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                 switch (v.getId()) {
                     case R.id.et_cwhcode:
                         if (binding.etCwhcode.getText().toString().contains("仓库")) {
                             Toast.makeText(BillDetailActivity.this, "如需" +
                                     "重新查询，请清空该项所有字符", Toast.LENGTH_LONG).show();
                             break;
                         }
                         getBarcodeValues(binding.etCwhcode.getText().toString(), 0);
                         break;
                 }
             }
             return false;
         }
     };
    private void openScan() {

        new IntentIntegrator(BillDetailActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    private void getBarcodeValues(String code, final int type) {

        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            jsonObject.put("barcode",code);
            jsonObject.put("ccode","");
            jsonObject.put("irowno","");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String obj=jsonObject.toString();
        Log.i("request-->",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject object=new JSONObject(response.body().string());
                    switch (type){
                        case 0:

                            warehouseBean=new Gson().fromJson(object.toString(), WarehouseBean.class);
                            if(warehouseBean.getResultcode().equals("200")) {
                                qrCode=binding.etCwhcode.getText().toString();
                                binding.etCwhcode.setText(warehouseBean.getFormdata().getCposition() + "\\" + warehouseBean.getFormdata().getCwhName());
                            }else {
                                Toast.makeText(BillDetailActivity.this,warehouseBean.getResultMessage(),Toast.LENGTH_LONG).show();
                            }
                            break;

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences=getSharedPreferences(menuBean.getMenucode()+"Detail",0);
       data= sharedPreferences.getString("data","");
      if(!data.isEmpty()){
          list=new ArrayList<>();
          JsonArray arry = new JsonParser().parse(data).getAsJsonArray();
          for (JsonElement jsonElement : arry) {
              list.add(new Gson().fromJson(jsonElement, ConfirmlistBean.class));
          }

          try {
              if(company.equals("瑞格菲克斯")) {
                  Collections.sort(list, new Comparator<ConfirmlistBean>() {
                      @Override
                      public int compare(ConfirmlistBean t1, ConfirmlistBean t2) {
                          if (Integer.parseInt(t1.getField8value()) < Integer.parseInt(t2.getField8value())) {
                              return 1;
                          } else if (Integer.parseInt(t1.getField8value()) == Integer.parseInt(t2.getField8value())) {
                              return t1.getField1value().compareTo(t2.getField1value());
                          } else {
                              return -1;
                          }

                      }

                  });
              }
          }catch (Exception e){
              e.toString();
          }

          adapter = new  Confirm2Adapter(list, BillDetailActivity.this);
          binding.rvList.setLayoutManager(new LinearLayoutManager(BillDetailActivity.this));
          binding.rvList.addItemDecoration(new DividerItemDecoration(BillDetailActivity.this, DividerItemDecoration.VERTICAL));
          binding.rvList.setAdapter(adapter);

      }

    }
    private void updateData(String text) {
        dialog.show();
        JSONObject jsonObject=new JSONObject();
        try {

             jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","2");
            jsonObject.put("button",text);
            ConfirmlistBean condition=getIntent().getParcelableExtra("condition");
            jsonObject.put("condition",new Gson().toJson(condition));
            if(menuBean.getMenushowname().equals("其他入库确认")
                    ||menuBean.getMenushowname().equals("生产入库确认")

            ){
                jsonObject.put("formdata",qrCode);

            }else {
                String formadata=sp.getString(menuBean.getMenucode()+"List","");
                jsonObject.put("formdata",formadata);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    try {
                        ConfirmBean confirmBean = new Gson().fromJson(response.body().string(), ConfirmBean.class);
                        Toast.makeText(BillDetailActivity.this, confirmBean.getResultMessage(), Toast.LENGTH_SHORT).show();
                        if (confirmBean.getResultcode().equals("200")) {

                            sp.edit().putString(menuBean.getMenucode() + "List", "").commit();
                            sp.edit().putString(menuBean.getMenucode()+"Scan","").commit();
                            finish();
                        }




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            dialog.dismiss();
            }
        });
    }

    private void getData() {
        dialog.show();
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getMenuFieldAndValue");
            jsonObject.put("usercode",usercode);
            jsonObject.put("menucode",menuBean.getMenucode());
            if(menuBean.getMenushowname().equals("备货调拨")){
                jsonObject.put("layout","1");

            }else {
                jsonObject.put("layout","2");

            }
            if(getIntent().getParcelableExtra("condition")==null){

                if(getIntent().getStringExtra("condition").isEmpty()){
                    jsonObject.put("condition","");
                }else {
                    jsonObject.put("condition",getIntent().getStringExtra("condition"));
                }

            }else {
                ConfirmlistBean data=getIntent().getParcelableExtra("condition");
                jsonObject.put("condition",new Gson().toJson(data));
            }
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
                   dialog.dismiss();
                try {
                    if(response.code()==200) {
                        list=new ArrayList<>();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            list.add(new Gson().fromJson(jsonElement, ConfirmlistBean.class));
                        }
                        if(list.isEmpty()){
                            Toast.makeText(BillDetailActivity.this, "无数据", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(list.get(0).getField8value().isEmpty()||list.get(0).getField2value().isEmpty()) {
                        }else {
                            if(company.equals("瑞格菲克斯")) {
                                Collections.sort(list, new Comparator<ConfirmlistBean>() {
                                    @Override
                                    public int compare(ConfirmlistBean t1, ConfirmlistBean t2) {
                                        if (Double.parseDouble(t1.getField8value()) < Double.parseDouble(t2.getField8value())) {
                                            return 1;
                                        } else if (Double.parseDouble(t1.getField8value()) == Double.parseDouble(t2.getField8value())) {
                                            return t1.getField1value().compareTo(t2.getField1value());
                                        } else {
                                            return -1;
                                        }


                                    }
                                });
                            }
                        }
                        adapter = new  Confirm2Adapter(list, BillDetailActivity.this);
                        binding.rvList.setLayoutManager(new LinearLayoutManager(BillDetailActivity.this));
                        binding.rvList.addItemDecoration(new DividerItemDecoration(BillDetailActivity.this, DividerItemDecoration.VERTICAL));
                        binding.rvList.setAdapter(adapter);

                        sharedPreferences.edit().putString("data",new Gson().toJson(list)).commit();
                        if(company.equals("新傲科技")) {
                            SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString(menuBean.getMenucode()+"List","").commit();
                            sharedPreferences.edit().putString(menuBean.getMenucode()+"Scan","").commit();
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
    public class Confirm2Adapter extends RecyclerView.Adapter<Confirm2Adapter.VH>  {
        ItemDetailsBinding binding;
        @NonNull
        @Override
        public Confirm2Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_details,viewGroup,false);
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

                    if(menuBean.getMenushowname().equals("其他出库确认")
                       ||menuBean.getMenushowname().equals("其他入库确认")
                            ||menuBean.getMenushowname().equals("生产入库确认")
                    ){
                    }else {
                        Intent intent = new Intent(BillDetailActivity.this, ProductionwarehousingActivity.class);
                        intent.putExtra("menubean", menuBean);
                        intent.putExtra("position",i);
                        intent.putExtra("cvenabbname",getIntent().getStringExtra("cvenabbname"));
                        startActivity(intent);
                    }


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
