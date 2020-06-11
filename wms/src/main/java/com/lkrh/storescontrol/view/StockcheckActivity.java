package com.lkrh.storescontrol.view;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.StockcheckBean;
import com.lkrh.storescontrol.databinding.ActivityStockcheckBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StockcheckActivity extends BaseActivity {
    ActivityStockcheckBinding binding;
    FunctionAdapter functionAdapter;
    StockcheckBean stockcheckBean;
    LoginBean.Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_stockcheck);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        binding.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentStockInfo();
            }
        });
        menu=getIntent().getParcelableExtra("menubean");
        binding.ivPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                binding.etPosition.setText(result.getContents());


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void openScan() {

        new IntentIntegrator(StockcheckActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    private void getCurrentStockInfo() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","getMenuFieldAndValue");
            jsonObject.put("acccode",acccode);
            jsonObject.put("usercode",usercode);
            jsonObject.put("menucode",menu.getMenucode());
            jsonObject.put("layout",2);
            jsonObject.put("warehouse",binding.etWarehouse.getText().toString());
            jsonObject.put("position",binding.etPosition.getText().toString());
            jsonObject.put("inventory",binding.etInventory.getText().toString());
            jsonObject.put("batch",binding.etBatch.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("getCurrentStockInfo",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        stockcheckBean=new Gson().fromJson(response.body().string(),StockcheckBean.class);
                        functionAdapter=new FunctionAdapter(stockcheckBean.getData());
                        binding.rvList.setLayoutManager(new LinearLayoutManager(StockcheckActivity.this));
                        binding.rvList.addItemDecoration(new DividerItemDecoration(StockcheckActivity.this,DividerItemDecoration.VERTICAL));
                        binding.rvList.setAdapter(functionAdapter);
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
            View v=getLayoutInflater().inflate(R.layout.item_stockcheck,viewGroup,false);
            return new FunctionAdapter.VH(v);


        }

        private List<StockcheckBean.Data> mDatas;
        public FunctionAdapter(List<StockcheckBean.Data> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh,final int i) {
            vh.textViewiQuantity.setText("数量："+mDatas.get(i).getIQuantity());
            vh.textViewcBatch.setText("批号："+mDatas.get(i).getCBatch());
            vh.textViewcInvStd.setText("描述："+mDatas.get(i).getCInvName()+"/"+mDatas.get(i).getCInvStd());
            vh.textViewcInvCode.setText("料号："+mDatas.get(i).getCInvCode());
            vh.textViewcPosName.setText("货位："+mDatas.get(i).getCPosName());
            vh.textViewcWhName.setText("仓库："+mDatas.get(i).getCWhName());
            vh.textViewtag.setText(i+1+"");
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
            TextView  textViewcWhName,textViewcPosName,textViewcInvCode,
                    textViewcInvStd,textViewcBatch,textViewiQuantity,textViewtag;
            public VH(@NonNull View itemView) {
                super(itemView);
                textViewcWhName=itemView.findViewById(R.id.tv_cWhName);
                textViewcPosName=itemView.findViewById(R.id.tv_cPosName);
                textViewcInvCode=itemView.findViewById(R.id.tv_cInvCode);
                textViewcInvStd=itemView.findViewById(R.id.tv_cInvStd);
                textViewcBatch=itemView.findViewById(R.id.tv_cBatch);
                textViewiQuantity=itemView.findViewById(R.id.tv_iQuantity);
                textViewtag=itemView.findViewById(R.id.tv_tag);



            }
        }
    }


}
