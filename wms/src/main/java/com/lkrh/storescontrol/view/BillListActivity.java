package com.lkrh.storescontrol.view;

import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityBillListBinding;
import com.lkrh.storescontrol.databinding.ItemCodelistBinding;
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

public class BillListActivity extends BaseActivity {
    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;
    List<ConfirmlistBean> searchList;
    Confirm2Adapter adapter;
    RecyclerView recyclerView;
    ActivityBillListBinding binding;
    int tag=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        binding= DataBindingUtil.setContentView(BillListActivity.this,R.layout.activity_bill_list);

        recyclerView=findViewById(R.id.rv_list);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
        if(company.equals("林肯SKF") && menuBean.getMenushowname().equals("材料出库")) {
             binding.lCcode.setVisibility(View.VISIBLE);
        }
        if(company.equals("浦东瀚氏") && menuBean.getMenushowname().equals("生产入库确认")
                ||menuBean.getMenushowname().equals("其他入库确认")) {
            binding.lTray.setVisibility(View.VISIBLE);
        }
        binding.etCcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                    getData();
                }
                return false;
            }
        });
        if(company.equals("新傲科技")){
            binding.lSearch.setVisibility(View.VISIBLE);
        }
        binding.ivCcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag=0;
                openScan();
            }
        });
        binding.ivTray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag=1;
                openScan();
            }
        });
        binding.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag=2;
                openScan();
            }
        });

        binding.etTray.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                search(editable.toString());
            }
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                tag=2;
                search(editable.toString());
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void openScan() {

        new IntentIntegrator(BillListActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
              final   String code=result.getContents();
                switch (tag){
                    case 0:
                        binding.etCcode.setText(code);
                        getData();
                        break;
                    case 1:
                        binding.etTray.setText(code);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                search(code);
                            }
                        }).start();
                        break;
                    case 2:
                        binding.etSearch.setText(code);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                search(code);
                            }
                        }).start();
                        break;
                }


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


    private void search(String key) {
        Log.i("key-->",key);
        searchList=new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            String string=new Gson().toJson(list.get(i));
            if(string.contains(key)){
              searchList.add(list.get(i));
            }


        }
        adapter = new Confirm2Adapter(searchList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }


    private void getData() {
        dialog.show();
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getMenuFieldAndValue");
            jsonObject.put("usercode",usercode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","1");
            ConfirmlistBean condition=getIntent().getParcelableExtra("condition");
            jsonObject.put("condition",new Gson().toJson(condition));
            jsonObject.put("barcode","");
            jsonObject.put("formdata","");
            jsonObject.put("acccode",acccode);
            jsonObject.put("ccode",binding.etCcode.getText().toString());


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

                        adapter = new Confirm2Adapter(list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(BillListActivity.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(BillListActivity.this, DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(adapter);



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
        ItemCodelistBinding binding;
        @NonNull
        @Override
        public Confirm2Adapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_codelist,viewGroup,false);

            return new Confirm2Adapter.VH(binding.getRoot());
        }
        private List<ConfirmlistBean> mDatas;

        public Confirm2Adapter(List<ConfirmlistBean> data) {
            this.mDatas = data;

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

                    Intent intent=null;
                    if(company.equals("新傲科技") && menuBean.getMenushowname().equals("扫码检查")){
                        intent=new Intent(BillListActivity.this, ScanCheckActivity.class);
                        intent.putExtra("ConfirmlistBean",mDatas.get(i));
                    }else {
                        intent=new Intent(BillListActivity.this, BillDetailActivity.class);

                    }
                    intent.putExtra("condition",data);
                    intent.putExtra("menubean",menuBean);
                    intent.putExtra("menuname", menuBean.getMenushowname());
                    intent.putExtra("cvenabbname",mDatas.get(i).getField3value());
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
