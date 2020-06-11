package com.lkrh.storescontrol.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.lkrh.storescontrol.bean.ArrivalHeadBean;
import com.lkrh.storescontrol.bean.ConfirmBean;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;

import com.lkrh.storescontrol.bean.WarehouseBean;
import com.lkrh.storescontrol.databinding.ActivityPutListBinding;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutListActivity extends BaseActivity {
    RecyclerView recyclerView;
    private  FunctionAdapter functionAdapter;
    Button buttonsubmit;
    private ImageView imageViewreturn;
    TextView textViewtitle,textViewtotal;
    ArrayList<ArrivalHeadBean> arrivalHeadBeans=new ArrayList<>();
    SharedPreferences sharedPreferences;
    LoginBean.Menu menuBean;

    ActivityPutListBinding binding;

    Boolean isParse=false;
    int tag=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_list);
        binding= DataBindingUtil.setContentView(PutListActivity.this,R.layout.activity_put_list);
        menuBean=getIntent().getParcelableExtra("menubean");
        recyclerView=findViewById(R.id.rv_list);
        textViewtitle=findViewById(R.id.tv_title);
        buttonsubmit=findViewById(R.id.b_submit);
        textViewtotal=findViewById(R.id.tv_total);
        textViewtitle.setText(menuBean.getMenushowname()+"列表");

        imageViewreturn=findViewById(R.id.iv_return);
        imageViewreturn.setVisibility(View.VISIBLE);
        imageViewreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String data="";


        data= sharedPreferences.getString(menuBean.getMenucode()+"List","");
        if (!data.equals("")){

            try {
                Gson gson = new Gson();
                JsonArray arry = new JsonParser().parse(data).getAsJsonArray();
                for (JsonElement jsonElement : arry) {
                    arrivalHeadBeans.add(gson.fromJson(jsonElement, ArrivalHeadBean.class));
                }
                Log.i("arrivalHeadBeans",arrivalHeadBeans.size()+"");
                textViewtotal.setText("总计:"+arrivalHeadBeans.size()+"条");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(getIntent().getStringExtra("menuname").equals("销售出库") && company.equals("浦东瀚氏")
                ||getIntent().getStringExtra("menuname").equals("发货出库") && company.equals("浦东瀚氏")){
            binding.lPlate.setVisibility(View.VISIBLE);
        } else if(getIntent().getStringExtra("menuname").equals("货位调整") && company.equals("林肯SKF")) {
            binding.rlUpdate.setVisibility(View.VISIBLE);
        }

        binding.etUpdatecwhcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    getData(binding.etUpdatecwhcode.getText().toString());
                }
                return false;
            }
        });
        binding.ivPlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScan();
                tag=0;
            }
        });
        binding.ivUpdatecwhcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScan();
                tag=1;
            }
        });



        functionAdapter=new PutListActivity.FunctionAdapter(arrivalHeadBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(functionAdapter);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rlUpdate.getVisibility()==View.VISIBLE){
                    if(binding.etUpdatecwhcode.getText().toString().isEmpty()){
                        Toast.makeText(PutListActivity.this,"请扫描调入仓位",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if(binding.lPlate.getVisibility()==View.VISIBLE){
                    if(binding.etPlate.getText().toString().isEmpty()){
                        Toast.makeText(PutListActivity.this,"请扫描车码",Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                if(company.equals("新傲科技")){
                    if(!getIntent().getStringExtra("menuname").equals("采购入库")){
                        SharedPreferences detailPreferences = getSharedPreferences(menuBean.getMenucode() + "Detail", 0);
                        List<ConfirmlistBean> detailsList = Untils.getDetails(detailPreferences);
                        for (int i = 0; i <detailsList.size() ; i++) {
                            int undone=Integer.parseInt(detailsList.get(i).getField8value());
                            if(undone!=0){
                                Toast.makeText(PutListActivity.this,"有未扫码条目，请完成后再提交",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }
                }

                    putData();


            }
        });

    }

    private void openScan() {

        new IntentIntegrator(PutListActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }
    private void getData(String code) {
        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("layout","2");
            jsonObject.put("barcode",code);
            jsonObject.put("ccode","");
            jsonObject.put("irowno","");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("request-->",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    JSONObject object=new JSONObject(response.body().string());
                    WarehouseBean   warehouseBean=new Gson().fromJson(object.toString(), WarehouseBean.class);
                    binding.etUpdatecwhcode.setText(warehouseBean.getFormdata().getCposition()+"\\"+warehouseBean.getFormdata().getCwhName());
                    arrivalHeadBeans.get(0).setInposition(warehouseBean.getFormdata().getCposition());
                    arrivalHeadBeans.get(0).setInwhcode(warehouseBean.getFormdata().getCwhcode());
                    isParse=true;


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
               switch (tag){
                   case 0:
                       binding.etPlate.setText(result.getContents());
                       break;
                   case 1:
                       getData(result.getContents());
                       break;
               }


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void putData() {
        dialog.show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String strings=simpleDateFormat.format(date);
        if(getTime().isEmpty()) {
            setTime(strings);
        }

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","updateDocVouch");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            if(company.equals("新傲科技")){
                jsonObject.put("layout",passworld);
            }else {
                jsonObject.put("layout","1");
            }

            jsonObject.put("button",binding.etPlate.getText().toString());
            jsonObject.put("condition",getTime());
            jsonObject.put("formdata",new Gson().toJson(arrivalHeadBeans));



        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("requestbody-->",obj);
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

                if(bean.getResultcode().equals("200")){
                    arrivalHeadBeans.clear();
                    functionAdapter.notifyDataSetChanged();
                    SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                    setTime("");

                    sharedPreferences.edit().putString(menuBean.getMenucode()+"List","").commit();
                    sharedPreferences.edit().putString(menuBean.getMenucode()+"Scan","").commit();

                }

                Toast.makeText(PutListActivity.this,bean.getResultMessage(),5*1000).show();
                textViewtotal.setText("");
                finish();

            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(PutListActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            } });
    }

    private void setTime(String strings) {
        sharedPreferences.edit().putString(menuBean.getMenucode()+"Time", strings).commit();
    }
    private String getTime() {
        String stringscandata="";
        stringscandata=sharedPreferences.getString(menuBean.getMenucode()+"Time","");
        return stringscandata;

    }

    class FunctionAdapter extends RecyclerView.Adapter<PutListActivity.FunctionAdapter.VH>{

        @NonNull
        @Override
        public PutListActivity.FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v=getLayoutInflater().inflate(R.layout.item_input,viewGroup,false);
            return new PutListActivity.FunctionAdapter.VH(v);


        }

        private List<ArrivalHeadBean> mDatas;
        public FunctionAdapter(List<ArrivalHeadBean> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull  PutListActivity.FunctionAdapter.VH vh,final int i) {
            vh.textViewnumber.setText(i+1+"");
            vh.textViewccode.setText(arrivalHeadBeans.get(i).getCpocode());
            vh.textViewline.setText(arrivalHeadBeans.get(i).getIrowno());
            vh.textViewcposcode.setText("货位："+arrivalHeadBeans.get(i).getCposition());
            vh.textViewmaterial.setText("料号："+arrivalHeadBeans.get(i).getcInvCode());
            vh.textViewbatch.setText("批号："+arrivalHeadBeans.get(i).getCbatch());
            vh.textViewcount.setText("数量："+arrivalHeadBeans.get(i).getIquantity());

            if(arrivalHeadBeans.get(i).getFile()!=null){
                vh.imageViewpic.setTag(i+"");
                Picasso.get().load(new File(arrivalHeadBeans.get(i).getFile())).into(vh.imageViewpic);
            }
            vh.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(PutListActivity.this);
                    builder.setTitle("提示").setMessage("删除此条数据").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                            //delete sharedPreferences->scan item
                            String stringscandata="";
                            Log.i("arrivalHeadBeans",new Gson().toJson(arrivalHeadBeans));
                            stringscandata=sharedPreferences.getString(menuBean.getMenucode()+"Scan","");
                            List<String> listcode = Untils.stingsToList(stringscandata);

                            listcode.remove(i);



                            if(company.equals("新傲科技")) {
                                SharedPreferences detailPreferences=getSharedPreferences(menuBean.getMenucode() + "Detail" ,0);
                                List<ConfirmlistBean> detailsList = Untils.getDetails(detailPreferences);
                                for (int j = 0; j <detailsList.size() ; j++) {

                                    if(arrivalHeadBeans.get(i).getIrowno().equals(detailsList.get(j).getField10value())){

                                        Untils.updateIquantity(detailsList.get(j),-Integer.parseInt(arrivalHeadBeans.get(i).getIquantity()));

                                    }
                                }

                                detailPreferences.edit().putString("data",new Gson().toJson(detailsList)).commit();
                            }
                            arrivalHeadBeans.remove(i);

                            functionAdapter.notifyDataSetChanged();
                            textViewtotal.setText("总计:"+arrivalHeadBeans.size()+"条");
                            //delete sharedPreferences->putlist item
                            String strings= new Gson().toJson(arrivalHeadBeans);

                            sharedPreferences.edit().putString(menuBean.getMenucode()+"List",strings).commit();
                            sharedPreferences.edit().putString(menuBean.getMenucode()+"Scan",listcode.toString()).commit();



                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            });


        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
            TextView textViewnumber,textViewccode,textViewline,textViewcposcode,textViewmaterial,textViewbatch,textViewcount;
            LinearLayout linearLayout;
            ImageView imageViewpic;
            public VH(@NonNull View itemView) {
                super(itemView);
                linearLayout=itemView.findViewById(R.id.l_input);
                textViewnumber=itemView.findViewById(R.id.tv_number);
                textViewccode=itemView.findViewById(R.id.tv_ccode);
                textViewline=itemView.findViewById(R.id.tv_line);
                textViewcposcode=itemView.findViewById(R.id.tv_cposcode);
                textViewmaterial=itemView.findViewById(R.id.tv_material);
                textViewbatch=itemView.findViewById(R.id.tv_batch);
                textViewcount=itemView.findViewById(R.id.tv_count);
                imageViewpic=itemView.findViewById(R.id.iv_pic);


            }
        }
    }


}
