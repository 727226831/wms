package com.lkrh.storescontrol.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

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
import com.lkrh.storescontrol.bean.ProductBean;
import com.lkrh.storescontrol.bean.WarehouseBean;
import com.lkrh.storescontrol.databinding.ActivityProductionwarehousingBinding;
import com.lkrh.storescontrol.untils.iToast;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 生产/采购 入库
 */
public class ProductionwarehousingActivity extends BaseActivity {

    ActivityProductionwarehousingBinding binding;
    ArrivalHeadBean arrivalHeadBean;
    ConfirmlistBean confirmlistBean;
    int tag=-1;//0仓库 1料号

    private  String cwhcode,inposition, inwhcode;
    private  String  cposition;
    File file;
    private  String stringScan; //扫描到的二维码
    SharedPreferences sharedPreferences;
    SharedPreferences detailPreferences;
    WarehouseBean warehouseBean;
    LoginBean.Menu menuBean;
    Boolean isVerification=false;
    List<ConfirmlistBean> detailsList;
    int defalultIquantity=25;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_productionwarehousing);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
        sharedPreferences=getSharedPreferences("sp",MODE_PRIVATE);
      
        detailPreferences=getSharedPreferences(menuBean.getMenucode() + "Detail" ,0);
        detailsList=Untils.getDetails(detailPreferences);
        if(getIntent().getIntExtra("position",-1)!=-1){
            confirmlistBean=detailsList.get(getIntent().getIntExtra("position",-1));
        }

        if(menuBean.getMenucode().equals("SCRK")
           ||menuBean.getMenushowname().equals("其他入库")
                ||menuBean.getMenucode().equals("HZCLCK")
        ){
            binding.lCvenabbname.setVisibility(View.GONE);
            useEditIquantity();
            if(company.equals("浦东瀚氏")) {
                binding.lTray.setVisibility(View.VISIBLE);
                binding.ivHelp.setVisibility(View.VISIBLE);
            }else if(company.equals("新傲科技")){
                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);
                binding.tvCinvcode.setVisibility(View.GONE);
                useEditIquantity();
                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");
                arrivalHeadBean.setCcode(confirmlistBean.getField9value());
                arrivalHeadBean.setCbatch(confirmlistBean.getField5value());
                arrivalHeadBean.setcInvName(confirmlistBean.getField3value());
                arrivalHeadBean.setCvenabbname(getIntent().getStringExtra("cvenabbname"));
                binding.setBean(arrivalHeadBean);
            }
        }else if(menuBean.getMenushowname().equals("其他出库")){
            binding.lCvenabbname.setVisibility(View.GONE);
              useEditIquantity();
            if(company.equals("新傲科技")){

                    useEditIquantity();

                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);
                binding.tvCinvcode.setVisibility(View.GONE);
                binding.rlCwhcode.setVisibility(View.GONE);

                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");

                arrivalHeadBean.setCcode(confirmlistBean.getField9value());
                arrivalHeadBean.setCbatch(confirmlistBean.getField5value());
                arrivalHeadBean.setcInvName(confirmlistBean.getField3value());
                arrivalHeadBean.setCvenabbname(getIntent().getStringExtra("cvenabbname"));

                binding.setBean(arrivalHeadBean);
            }
        }else if(menuBean.getMenushowname().equals("材料出库")){
            binding.lCvenabbname.setVisibility(View.GONE);

            if(company.equals("林肯SKF")) {
                useEditIquantity();
                binding.bSearch.setVisibility(View.VISIBLE);
                binding.tvTotal.setVisibility(View.VISIBLE);
            }else if(company.equals("新傲科技")){
                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);
                binding.tvCinvcode.setVisibility(View.GONE);
                binding.rlCwhcode.setVisibility(View.GONE);


                    useEditIquantity();

                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");

                arrivalHeadBean.setCcode(confirmlistBean.getField9value());
                arrivalHeadBean.setCbatch(confirmlistBean.getField5value());
                arrivalHeadBean.setcInvName(confirmlistBean.getField3value());
                arrivalHeadBean.setCvenabbname(getIntent().getStringExtra("cvenabbname"));

                binding.setBean(arrivalHeadBean);
            } else {
                useEditIquantity();
                binding.bSearch.setVisibility(View.GONE);
                binding.tvTotal.setVisibility(View.GONE);
            }

        }else if(menuBean.getMenushowname().equals("销售发货")){
            binding.lCvenabbname.setVisibility(View.GONE);
            binding.rlUpdate.setVisibility(View.GONE);
           useEditIquantity();

            if(company.equals("浦东瀚氏")) {
                binding.rlCustomer.setVisibility(View.VISIBLE);

                binding.rlTransport.setVisibility(View.VISIBLE);
                binding.rlMemo.setVisibility(View.VISIBLE);
            }else {
                binding.bSearch.setVisibility(View.GONE);
                binding.tvTotal.setVisibility(View.GONE);
            }

        }else if(menuBean.getMenushowname().equals("委外发料")){
            binding.lCvenabbname.setVisibility(View.GONE);
            binding.rlUpdate.setVisibility(View.GONE);
        }else  if(menuBean.getMenushowname().equals("采购入库")){
            if(company.equals("瑞格菲克斯")){
              useEditIquantity();
            }
            if(company.equals("浦东瀚氏")) {
                binding.ivHelp.setVisibility(View.VISIBLE);
            }
            if(company.equals("新傲科技")){
                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);
                binding.tvBoxcode.setText("箱码：");



                  useEditIquantity();

                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");

                arrivalHeadBean.setCcode(confirmlistBean.getField9value());

                arrivalHeadBean.setCvenabbname(getIntent().getStringExtra("cvenabbname"));

                binding.setBean(arrivalHeadBean);
            }

        }else  if(menuBean.getMenushowname().equals("到货入库")){

        }else  if(menuBean.getMenushowname().equals("到货确认")){
            binding.rlUpdate.setVisibility(View.GONE);
            binding.rlCwhcode.setVisibility(View.GONE);
        }else  if(menuBean.getMenushowname().equals("产成品入库")){
            binding.lCvenabbname.setVisibility(View.GONE);

            binding.rlUpdate.setVisibility(View.GONE);
        }else  if(menuBean.getMenushowname().equals("货位调整")){
            binding.lCvenabbname.setVisibility(View.GONE);

            if(company.equals("浦东瀚氏")) {
                binding.rlUpdate.setVisibility(View.VISIBLE);
                 useEditIquantity();
            }
            if(company.equals("林肯SKF")) {
               binding.rlCwhcode.setVisibility(View.GONE);
                binding.lCposition.setVisibility(View.VISIBLE);
            }

            if(company.equals("瑞格菲克斯")){
                binding.rlUpdate.setVisibility(View.VISIBLE);
            }else  if(company.equals("新傲科技")) {
                binding.rlUpdate.setVisibility(View.VISIBLE);
                binding.bAdjustment.setVisibility(View.VISIBLE);
                useEditIquantity();
                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);

                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");
                binding.setBean(arrivalHeadBean);
            }

        }else  if(menuBean.getMenushowname().equals("采购到货")){
            binding.rlCdefine10.setVisibility(View.VISIBLE);
            binding.rlUpdate.setVisibility(View.GONE);
            binding.tvCcodekey.setText("采购订单号：");
            binding.rlCwhcode.setVisibility(View.GONE);
        }else  if(menuBean.getMenushowname().equals("存货盘点")){
           useEditIquantity();
        } else  if(menuBean.getMenushowname().equals("补料发料")){
          useEditIquantity();
        } else  if(menuBean.getMenushowname().equals("采购退料")){
           useEditIquantity();
            binding.lCpocode.setVisibility(View.VISIBLE);
        }else  if(menuBean.getMenushowname().equals("库存调拨")){
            binding.lCvenabbname.setVisibility(View.GONE);
            binding.rlUpdate.setVisibility(View.VISIBLE);
            useEditIquantity();
            if(company.equals("浦东瀚氏")){
                    binding.rlTransport.setVisibility(View.VISIBLE);
                    binding.rlMemo.setVisibility(View.VISIBLE);

            }
        }else if(menuBean.getMenushowname().equals("销售出库")){
            binding.lCvenabbname.setVisibility(View.GONE);


            if(company.equals("浦东瀚氏")){

                binding.bSearch.setVisibility(View.VISIBLE);
                binding.tvTotal.setVisibility(View.VISIBLE);
               useEditIquantity();
            }else  if(company.equals("新傲科技")){
                binding.lBatch.setVisibility(View.GONE);
                binding.lBoxcode.setVisibility(View.VISIBLE);
                binding.tvCinvcode.setVisibility(View.GONE);
                binding.rlCwhcode.setVisibility(View.GONE);

                    useEditIquantity();


                arrivalHeadBean=new ArrivalHeadBean();
                arrivalHeadBean.setIquantity(defalultIquantity+"");

                arrivalHeadBean.setCcode(confirmlistBean.getField9value());
                arrivalHeadBean.setCbatch(confirmlistBean.getField5value());
                arrivalHeadBean.setcInvName(confirmlistBean.getField3value());
                arrivalHeadBean.setCvenabbname(getIntent().getStringExtra("cvenabbname"));

                binding.setBean(arrivalHeadBean);
            }else {
                binding.bSearch.setVisibility(View.GONE);
                binding.tvTotal.setVisibility(View.GONE);
            }



        }else if(menuBean.getMenushowname().equals("备货调拨")){

            binding.rlUpdate.setVisibility(View.VISIBLE);
            useEditIquantity();
            if(company.equals("浦东瀚氏")){
                binding.rlTransport.setVisibility(View.VISIBLE);
                binding.rlMemo.setVisibility(View.VISIBLE);


            }

        }else if(menuBean.getMenushowname().equals("发货出库")){

            binding.tvBatch.setText("客户箱码：");
            binding.lYhCode.setVisibility(View.VISIBLE);
            binding.rlCwhcode.setVisibility(View.GONE);
            binding.lCustomer.setVisibility(View.VISIBLE);
            binding.lWarehouse.setVisibility(View.VISIBLE);
            binding.lCvenabbname.setVisibility(View.GONE);
            useEditIquantity();



        }





        binding.etTimes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    int times;
                    times = Integer.parseInt(s.toString());
                    if (times < 1) {
                        times=1;
                        binding.etTimes.setText(times+"");
                        Toast.makeText(ProductionwarehousingActivity.this, "此值必须大于1", Toast.LENGTH_LONG).show();
                    }
                    changeIquantity(times);
                }
            }
        });
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
        binding.etBoxcode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if(i == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                            if(!detailsList.isEmpty()){
                                if(!detailsList.get(0).getField5value().isEmpty()){
                                    arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
                                    checkBoxCode(binding.etBoxcode.getText().toString());
                                }
                            }
                }
                if(event.getAction()==KeyEvent.ACTION_UP){
                    return  true;
                }else {
                    return false;
                }
            }
        });



        binding.etCwhcode.setOnKeyListener(onKeyListener);
        binding.etUpdatecwhcode.setOnKeyListener(onKeyListener);
        binding.etBatch.setOnKeyListener(onKeyListener);
        binding.etPlate.setOnKeyListener(onKeyListener);
        binding.etDock.setOnKeyListener(onKeyListener);
        binding.etTray.setOnKeyListener(onKeyListener);
        binding.etYhCode.setOnKeyListener(onKeyListener);







        binding.ivCwhcode.setOnClickListener(onClickListener);
        binding.ivPlate.setOnClickListener(onClickListener);
        binding.ivUpdatecwhcode.setOnClickListener(onClickListener);
        binding.ivBatch.setOnClickListener(onClickListener);
        binding.ivDock.setOnClickListener(onClickListener);
        binding.bSubmit.setOnClickListener(onClickListener);
        binding.bSearch.setOnClickListener(onClickListener);
        binding.ivAdd.setOnClickListener(onClickListener);
        binding.ivMinus.setOnClickListener(onClickListener);
        binding.etIquantity.setOnKeyListener(onKeyListener);
        binding.ivTransport.setOnClickListener(onClickListener);
        binding.ivClear.setOnClickListener(onClickListener);

        binding.ivTray.setOnClickListener(onClickListener);
        binding.ivHelp.setOnClickListener(onClickListener);
        binding.ivYhCode.setOnClickListener(onClickListener);
        binding.ivBoxcode.setOnClickListener(onClickListener);
        binding.bAdjustment.setOnClickListener(onClickListener);


    }

    private void useEditIquantity() {
        binding.rlIquantityedit.setVisibility(View.VISIBLE);
        binding.rlIquantity.setVisibility(View.GONE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //扫码
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String code=result.getContents();
                Log.i("scan",code);

                switch (tag){
                    case 0://仓位
                        if(code.contains("$")){
                            Toast.makeText(ProductionwarehousingActivity.this,"类型错误",Toast.LENGTH_LONG).show();
                        }else {
                            binding.etCwhcode.setText(code);
                           getBarcodeValues(code,0);
                        }
                        break;
                    case 1://存货编码
                            stringScan=code;
                            binding.etBatch.setText(code);

                            getBarcodeValues(stringScan,1);
                            binding.etTimes.setText("1");

                        break;
                    case 2:
                        //调入货位
                            binding.etUpdatecwhcode.setText(code);
                           getBarcodeValues(code,2);
                        break;
                    case 3:
                        binding.etCustomer.setText(code);
                        break;
                    case 4:
                        binding.etTransport.setText(code);
                        break;
                    case 5:
                        binding.etPlate.setText(code);
                        break;
                    case 6:

                        binding.etDock.setText(code);
                        break;
                    case 7:
                        binding.etTray.setText(code);
                        break;
                    case 8:
                        binding.etYhCode.setText(code);
                        getBarcodeValues(binding.etYhCode.getText().toString(),3);
                        break;
                    case 9:
                        binding.etBoxcode.setText(code);
                        if(binding.rlIquantityedit.getVisibility()==View.GONE){
                            checkBoxCode(code);
                        }else {
                            if(binding.rlCwhcode.getVisibility()==View.GONE){
                                arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
                                checkBoxCode(binding.etBoxcode.getText().toString());
                            }
                        }


                        break;




                }

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


    private void checkBoxCode(String code) {
        Log.i("sc",code);
        code=code.trim();
        Log.i("sc",code);
        arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
        if(company.equals("新傲科技") && menuBean.getMenushowname().equals("货位调整")){
            if(code.toLowerCase().contains("PO")){
                Toast.makeText(ProductionwarehousingActivity.this, "LOT条码类型错误！", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if(cposition==null && binding.rlCwhcode.getVisibility()==View.VISIBLE){
            Toast.makeText(ProductionwarehousingActivity.this, "请先扫描仓位！", Toast.LENGTH_LONG).show();
            return;
        }
        stringScan=code;
        if(binding.rlUpdate.getVisibility()==View.VISIBLE ){
            if(binding.etUpdatecwhcode.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "调入仓库不能为空", Toast.LENGTH_LONG).show();
                return;
            }else {

                if(cposition.equals(inposition) && cwhcode.equals(inwhcode)){
                    Toast.makeText(ProductionwarehousingActivity.this, "货位不能相同", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    arrivalHeadBean.setInposition(inposition);
                    arrivalHeadBean.setInwhcode(inwhcode);
                }

            }
        }


        if(binding.rlCwhcode.getVisibility()==View.VISIBLE ){
            if(binding.etCwhcode.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "仓库不能为空", Toast.LENGTH_LONG).show();
                return;
            }else {
                if(menuBean.getMenushowname().equals("材料出库")){
                    if(cposition.equals(detailsList.get(0).getField2value())){
                        Toast.makeText(ProductionwarehousingActivity.this, "仓位不正确", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                arrivalHeadBean.setCwhcode(cwhcode);
                arrivalHeadBean.setCposition(cposition);
            }

        }
        arrivalHeadBean.setBarcode(code);
        if(company.equals("新傲科技") && menuBean.getMenushowname().equals("材料出库")
                ||company.equals("新傲科技") && menuBean.getMenucode().equals("SCRK")
                ||company.equals("新傲科技") && menuBean.getMenushowname().equals("货位调整")
        ){
            arrivalHeadBean.setCbatch(code);

        }

        if(!detailsList.isEmpty()){
        if(detailsList.get(0).getField5value().isEmpty()){
            if(getIntent().getIntExtra("position",-1)!=-1) {
                arrivalHeadBean.setIrowno(detailsList.get(getIntent().getIntExtra("position",-1)).getField10value());
                arrivalHeadBean.setcInvCode(detailsList.get(getIntent().getIntExtra("position",-1)).getField4value());
            }

            update(detailsList.get(getIntent().getIntExtra("position",-1)));
        }else {
            for (int i = 0; i < detailsList.size(); i++) {

                if (detailsList.get(i).getField5value().equals(code) && !detailsList.get(i).getField8value().equals("0")) {

                    arrivalHeadBean.setIrowno(detailsList.get(i).getField10value());
                    arrivalHeadBean.setcInvCode(detailsList.get(i).getField4value());
                    arrivalHeadBean.setCbatch(detailsList.get(i).getField5value());
                    arrivalHeadBean.setcInvName(detailsList.get(i).getField3value());

                    binding.setBean(arrivalHeadBean);
                    update(detailsList.get(i));
                    isVerification = true;
                    if (company.equals("新傲科技") && menuBean.getMenushowname().equals("材料出库")) {
                        break;
                    }
                }
            }
            if (!isVerification) {
                Toast.makeText(ProductionwarehousingActivity.this, "未能匹配发货单或发货单已满！", Toast.LENGTH_LONG).show();
                binding.etBoxcode.setText("");


                return;
            } else {
                isVerification = false;

            }
        }

        }else {
            setList();
        }

    }


    View.OnKeyListener onKeyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Log.i("on key",keyCode+"/"+event.toString());
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (v.getId()) {
                    case R.id.et_cwhcode:
                        if(binding.etCwhcode.getText().toString().contains("仓库")){
                            Toast.makeText(ProductionwarehousingActivity.this,"如需" +
                                    "重新查询，请清空该项所有字符",Toast.LENGTH_LONG).show();
                            break;
                        }
                       getBarcodeValues(binding.etCwhcode.getText().toString(),0);
                        break;
                    case R.id.et_batch:
                        stringScan=binding.etBatch.getText().toString();


                         getBarcodeValues(stringScan,1);
                        binding.etTimes.setText("1");
                        break;
                    case R.id.et_updatecwhcode:
                        if(binding.etUpdatecwhcode.getText().toString().contains("仓库")){
                            Toast.makeText(ProductionwarehousingActivity.this,"如需" +
                                    "重新查询，请清空该项所有字符",Toast.LENGTH_LONG).show();
                            break;
                        }
                       getBarcodeValues(binding.etUpdatecwhcode.getText().toString(),2);
                        break;
                    case R.id.et_iquantity:
                        arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
                        break;
                    case  R.id.et_customer:
                        arrivalHeadBean.setCustomer(binding.etCustomer.getText().toString());
                        break;
                    case R.id.et_yh_code:
                        getBarcodeValues(binding.etYhCode.getText().toString(),3);
                        break;
                    case R.id.et_boxcode:

                       if(!detailsList.isEmpty()){
                           if(!detailsList.get(0).getField5value().isEmpty()){
                               arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
                               checkBoxCode(binding.etBoxcode.getText().toString());
                           }
                       }

                        break;



                }
            }

                return false;


        }
    };
    //0 position 1num
    private void getBarcodeValues(String code, final int type) {

        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("methodname","getBarcodeValues");
            jsonObject.put("usercode",usercode);
            jsonObject.put("acccode",acccode);
            jsonObject.put("menucode",menuBean.getMenucode());
            jsonObject.put("irowno","");
            if(menuBean.getMenushowname().equals("委外发料")){
                jsonObject.put("layout","3");
            }else {
                jsonObject.put("layout","1");
            }

           switch (type){
               case 3:
                   jsonObject.put("barcode","");
                   jsonObject.put("ccode",code);
                   break;

              default:
                  jsonObject.put("barcode",code);
                  if(menuBean.getMenushowname().equals("销售出库") && company.equals("浦东瀚氏")){
                      jsonObject.put("ccode",confirmlistBean.getField1value());
                  }else if(company.equals("新傲科技")){
                      if(confirmlistBean!=null){
                          jsonObject.put("ccode",confirmlistBean.getField9value());
                      }else {
                          jsonObject.put("ccode","");
                      }
                  }else {
                      jsonObject.put("ccode","");
                  }
           }


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
                           warehouseBean=new Gson().fromJson(object.toString(),WarehouseBean.class);
                           if(warehouseBean.getResultcode().equals("200")) {
                               if(warehouseBean.getPosformdata()!=null){
                                   String[] strings=new String[warehouseBean.getPosformdata().size()];
                                   for (int i = 0; i <warehouseBean.getPosformdata().size() ; i++) {

                                       strings[i]=warehouseBean.getPosformdata().get(i).getCwhcode()+warehouseBean.getPosformdata().get(i).getCwhName();
                                   }
                                   AlertDialog.Builder builder=new AlertDialog.Builder(ProductionwarehousingActivity.this);
                                   builder.setTitle("请选择:").setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int i) {
                                           cwhcode = warehouseBean.getPosformdata().get(i).getCwhcode();
                                           cposition = warehouseBean.getPosformdata().get(i).getCposition();
                                           if(company.equals("新傲科技")){
                                               binding.etCwhcode.setText(warehouseBean.getPosformdata().get(i).getCPositionName()
                                                       + "\\" + warehouseBean.getPosformdata().get(i).getCwhcode());
                                           }


                                           dialog.dismiss();
                                       }
                                   }).show();

                               }else {
                                   cwhcode = warehouseBean.getFormdata().getCwhcode();
                                   cposition = warehouseBean.getFormdata().getCposition();
                                   if(company.equals("新傲科技")){
                                       binding.etCwhcode.setText(warehouseBean.getFormdata().getCPositionName() + "\\" + warehouseBean.getFormdata().getCwhcode());
                                   }else {
                                       binding.etCwhcode.setText(warehouseBean.getFormdata().getCposition() + "\\" + warehouseBean.getFormdata().getCwhName());
                                   }

                               }

                           }else {
                               Toast.makeText(ProductionwarehousingActivity.this,warehouseBean.getResultMessage(),Toast.LENGTH_LONG).show();
                           }
                           break;
                       case 1:
                           initData();
                           ProductBean productBean=new Gson().fromJson(object.toString(),ProductBean.class);
                           if(!productBean.getResultcode().equals("200")){
                               Toast.makeText(ProductionwarehousingActivity.this,productBean.getResultMessage(),Toast.LENGTH_LONG).show();
                               return;
                           }else {
                               arrivalHeadBean=productBean.getFormdata();

                           }
                           if(binding.lYhCode.getVisibility()==View.VISIBLE){
                               arrivalHeadBean.setCcode(binding.etYhCode.getText().toString());
                       }




                           if(menuBean.getMenushowname().equals("委外发料")
                                   || menuBean.getMenushowname().equals("销售发货")
                                   || menuBean.getMenushowname().equals("材料出库")
                           ) {

                               arrivalHeadBean.setIrowno(confirmlistBean.getField10value());
                               arrivalHeadBean.setCcode(confirmlistBean.getField9value());
                           }





                           binding.setBean(arrivalHeadBean);
                           binding.tvCposition.setText(arrivalHeadBean.getCposition());


                           if(binding.lPlate.getVisibility()==View.VISIBLE){
                               binding.tvNumber.setText(arrivalHeadBean.getIquantity());
                               arrivalHeadBean.setPlate(binding.etPlate.getText().toString());
                           }else {

                               if(binding.rlIquantity.getVisibility()==View.VISIBLE){
                                   binding.tvNumber.setText(arrivalHeadBean.getIquantity());
                                   submit();
                               }else {
                                   binding.etIquantity.setText(arrivalHeadBean.getIquantity());

                               }
                           }
                           //根据数量编辑控件是否显示来判断是否自动添加





                           break;
                       case 2:
                           warehouseBean=new Gson().fromJson(object.toString(),WarehouseBean.class);
                           if(warehouseBean.getResultcode().equals("200")) {
                               if(warehouseBean.getPosformdata()!=null){
                                   String[] strings=new String[warehouseBean.getPosformdata().size()];
                                   for (int i = 0; i <warehouseBean.getPosformdata().size() ; i++) {

                                       strings[i]=warehouseBean.getPosformdata().get(i).getCwhcode()+warehouseBean.getPosformdata().get(i).getCwhName();
                                   }
                                   AlertDialog.Builder builder=new AlertDialog.Builder(ProductionwarehousingActivity.this);
                                   builder.setTitle("请选择:").setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int i) {
                                           inwhcode =warehouseBean.getPosformdata().get(i).getCwhcode();
                                           inposition=warehouseBean.getPosformdata().get(i).getCposition();

                                           if(company.equals("新傲科技")){
                                               binding.etUpdatecwhcode.setText(warehouseBean.getPosformdata().get(i).getCPositionName()
                                                       + "\\" + warehouseBean.getPosformdata().get(i).getCwhcode());
                                           }
                                           dialog.dismiss();
                                       }
                                   }).show();

                               }else {
                                   inwhcode =warehouseBean.getFormdata().getCwhcode();
                                   inposition=warehouseBean.getFormdata().getCposition();
                                   if(company.equals("新傲科技")){
                                       binding.etUpdatecwhcode.setText(warehouseBean.getFormdata().getCPositionName() + "\\" + warehouseBean.getFormdata().getCwhcode());
                                   }else {
                                       binding.etUpdatecwhcode.setText(warehouseBean.getFormdata().getCposition() + "\\" + warehouseBean.getFormdata().getCwhName());
                                   }

                               }

                           }else {
                               Toast.makeText(ProductionwarehousingActivity.this,warehouseBean.getResultMessage(),Toast.LENGTH_LONG).show();
                           }
                           break;
                       case 3:
                           warehouseBean=new Gson().fromJson(object.toString(),WarehouseBean.class);
                           if(warehouseBean.getResultcode().equals("200")) {
                               cwhcode = warehouseBean.getFormdata().getCwhcode();
                               cposition = warehouseBean.getFormdata().getCposition();
                              binding.tvCustomer.setText(warehouseBean.getFormdata().getCcuscode());
                              binding.tvWarehouse.setText(warehouseBean.getFormdata().getCwhName());
                           }else {
                               Toast.makeText(ProductionwarehousingActivity.this,warehouseBean.getResultMessage(),Toast.LENGTH_LONG).show();
                               binding.etYhCode.setText("");
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

    Intent intent;
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int times;//数量倍数
            switch (v.getId()){

                case R.id.iv_cwhcode:
                    tag=0;
                    openScan();
                    break;
                case  R.id.iv_batch:
                    tag=1;
                    openScan();
                    break;

                case R.id.iv_updatecwhcode:
                    tag=2;
                    openScan();
                    break;
                case R.id.iv_customer:
                    tag=3;
                    openScan();
                    break;
                case R.id.iv_transport:
                    tag=4;
                    openScan();
                    break;
                case R.id.iv_plate:
                    tag=5;
                    openScan();
                    break;
                case R.id.iv_dock:
                    tag=6;
                    openScan();
                    break;
                case R.id.iv_tray:
                    tag=7;
                    openScan();
                    break;
                case R.id.iv_yh_code:
                    tag=8;
                    openScan();
                    break;
                case R.id.iv_boxcode:
                    tag=9;
                    openScan();
                    break;

                case R.id.iv_add:
                    times=Integer.parseInt(binding.etTimes.getText().toString());
                    binding.etTimes.setText(times+1+"");
                    changeIquantity(times+1);
                    break;
                case R.id.iv_minus:
                    times=Integer.parseInt(binding.etTimes.getText().toString());
                    if(times>1) {
                        binding.etTimes.setText(times - 1+"");
                        changeIquantity(times-1);
                    }
                    break;

                case R.id.b_search:
                    intent=new Intent(ProductionwarehousingActivity.this,PutListActivity.class);
                    intent.putExtra("menuname",menuBean.getMenushowname());
                    intent.putExtra("menubean",menuBean);

                    if(binding.rlCdefine10.getVisibility()==View.VISIBLE){
                        intent.putExtra("cdefine10",binding.etCdefine10.getText().toString());
                    }
                    startActivity(intent);
                    break;
                case R.id.b_submit:
                    if(arrivalHeadBean==null) {
                        Toast.makeText(ProductionwarehousingActivity.this, "请扫描二维码", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(company.equals("新傲科技")){
                        arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());
                        checkBoxCode(binding.etBoxcode.getText().toString());

                    }else {
                        submit();
                    }


                    break;
                case R.id.b_adjustment:
                    AlertDialog.Builder builder=new AlertDialog.Builder(ProductionwarehousingActivity.this);
                    builder.setTitle("提示").setMessage("确认要执行整托调整操作吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            arrivalHeadBean=new ArrivalHeadBean();
                            if(binding.rlUpdate.getVisibility()==View.VISIBLE ){
                                if(binding.etUpdatecwhcode.getText().toString().isEmpty()){
                                    Toast.makeText(ProductionwarehousingActivity.this, "调入仓库不能为空", Toast.LENGTH_LONG).show();
                                    return;
                                }else {
                                    if(cposition.equals(inposition) && cwhcode.equals(inwhcode)){
                                        Toast.makeText(ProductionwarehousingActivity.this, "货位不能相同", Toast.LENGTH_LONG).show();
                                        return;
                                    }else {
                                        arrivalHeadBean.setInposition(inposition);
                                        arrivalHeadBean.setInwhcode(inwhcode);
                                    }
                                }
                            }

                            if(binding.rlCwhcode.getVisibility()==View.VISIBLE ){
                                if(binding.etCwhcode.getText().toString().isEmpty()){
                                    Toast.makeText(ProductionwarehousingActivity.this, "仓库不能为空", Toast.LENGTH_LONG).show();
                                    return;
                                }else {
                                    arrivalHeadBean.setCwhcode(cwhcode);
                                    arrivalHeadBean.setCposition(cposition);
                                }

                            }
                            updateData("整托调整");


                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();

                    break;


                case R.id.iv_clear:
                    binding.etCwhcode.setText("");
                    break;

                case  R.id.iv_help:
                    intent=new Intent(ProductionwarehousingActivity.this,HelpActivity.class);
                    intent.putExtra("cwhcode",binding.etCwhcode.getText().toString());
                    startActivity(intent);
                    break;
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
                if(company.equals("新傲科技")){
                    jsonObject.put("layout",passworld);
                }else {
                    jsonObject.put("layout","2");
                }
                jsonObject.put("button",text);
                jsonObject.put("condition","");
                jsonObject.put("formdata",new Gson().toJson(arrivalHeadBean));

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
                            Toast.makeText(ProductionwarehousingActivity.this, confirmBean.getResultMessage(), Toast.LENGTH_SHORT).show();





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





        private void openScan() {

            new IntentIntegrator(ProductionwarehousingActivity.this)
                    .setCaptureActivity(ScanActivity.class)
                    .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                    .setPrompt("请对准二维码")// 设置提示语
                    .setCameraId(0)// 选择摄像头,可使用前置或者后置
                    .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                    .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                    .initiateScan();// 初始化扫码

        }
    };

    private void submit() {
        if(binding.lYhCode.getVisibility()==View.VISIBLE){
            if(binding.etYhCode.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "要货单号不能为空", Toast.LENGTH_LONG).show();
                return;
            }

        }
        if(binding.lTray.getVisibility()==View.VISIBLE){
            arrivalHeadBean.setTray(binding.etTray.getText().toString());
        }
        if(binding.rlTransport.getVisibility()==View.VISIBLE){
            if (menuBean.getMenushowname().equals("销售发货")) {
                if (binding.etTransport.getText().toString().isEmpty()) {
                    Toast.makeText(ProductionwarehousingActivity.this, "承运商不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
            }else {
                arrivalHeadBean.setTransport(binding.etTransport.getText().toString());
            }

        }
        if(binding.rlMemo.getVisibility()==View.VISIBLE){
            arrivalHeadBean.setMemo(binding.etMemo.getText().toString());
        }

        if(binding.rlCustomer.getVisibility()==View.VISIBLE ){
            if(binding.etCustomer.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "客户字段不能为空", Toast.LENGTH_LONG).show();
                return;
            }else {
                arrivalHeadBean.setCustomer(binding.etCustomer.getText().toString());
            }

        }

        if(binding.lPlate.getVisibility()==View.VISIBLE &&binding.etPlate.getText().toString().isEmpty()){
            if(binding.etPlate.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "车码不能为空", Toast.LENGTH_LONG).show();
                return;
            }else {
                arrivalHeadBean.setPlate(binding.etPlate.getText().toString());
            }

        }

        if(binding.rlUpdate.getVisibility()==View.VISIBLE ){
            if(binding.etUpdatecwhcode.getText().toString().isEmpty()){
            Toast.makeText(ProductionwarehousingActivity.this, "调入仓库不能为空", Toast.LENGTH_LONG).show();
            return;
           }else {
                arrivalHeadBean.setInposition(inposition);
                arrivalHeadBean.setInwhcode(inwhcode);
            }
        }

        if(binding.rlCwhcode.getVisibility()==View.VISIBLE ){
            if(binding.etCwhcode.getText().toString().isEmpty()){
                Toast.makeText(ProductionwarehousingActivity.this, "仓库不能为空", Toast.LENGTH_LONG).show();
                return;
            }else {
                arrivalHeadBean.setCwhcode(cwhcode);
                arrivalHeadBean.setCposition(cposition);
            }

        }
        if(binding.tvWarehouse.getVisibility()==View.VISIBLE){
            arrivalHeadBean.setCwhcode(cwhcode);
            arrivalHeadBean.setCposition(cposition);
        }



        if(binding.etTimes.getText().toString().isEmpty()) {
            binding.etTimes.setText("1");
            Toast.makeText(ProductionwarehousingActivity.this, "数量倍数值必须大于1", Toast.LENGTH_LONG).show();
            changeIquantity(1);
        }

            if(menuBean.getMenushowname().equals("补料发料")) {
                if (!confirmlistBean.getField5value().equals(stringScan)) {
                    Toast.makeText(ProductionwarehousingActivity.this, "条码不一致！", Toast.LENGTH_LONG).show();
                    return;
                }
                arrivalHeadBean.setInposition(confirmlistBean.getField9value());
            }

            if(binding.rlIquantity.getVisibility()==View.VISIBLE){
                arrivalHeadBean.setIquantity(binding.tvNumber.getText().toString());
            }else {
                arrivalHeadBean.setIquantity(binding.etIquantity.getText().toString());

            }
            if(getIntent().getStringExtra("category")!=null) {
                arrivalHeadBean.setCategory(getIntent().getStringExtra("category"));
            }
            if(menuBean.getMenushowname().equals("采购退料")) {
                if(binding.etCpocode.getText().toString().isEmpty()){
                    Toast.makeText(ProductionwarehousingActivity.this, "采购单号不能为空", Toast.LENGTH_LONG).show();
                    return;
                }else {
                   arrivalHeadBean.setCpocode(binding.etCpocode.getText().toString());
                }
            }


            if(company.equals("瑞格菲克斯") && menuBean.getMenushowname().equals("销售发货")
                    ||menuBean.getMenushowname().equals("材料出库")
                    ||menuBean.getMenushowname().equals("备货调拨")
            ){

                for (int i = 0; i <detailsList.size() ; i++) {

                    if(
                            detailsList.get(i).getField1value().equals(arrivalHeadBean.getCInvCode())
                                    && detailsList.get(i).getField5value().equals(arrivalHeadBean.getCbatch())
                                    && detailsList.get(i).getField2value().equals(arrivalHeadBean.getCposition())
                    ){
                       update(detailsList.get(i));
                       isVerification=true;
                    }
                }

                if(!isVerification){
                    Toast.makeText(ProductionwarehousingActivity.this, "未能匹配发货单！", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    isVerification=false;
                }

            }


    }

    private void update(ConfirmlistBean bean) {
        ConfirmlistBean confirmlistBean=bean;
        if(menuBean.getMenushowname().equals("备货调拨")){
            updateList(confirmlistBean);
            if(Integer.parseInt(confirmlistBean.getField8value())<1){
                Toast.makeText(ProductionwarehousingActivity.this, "当前货物已达到需求数量！", Toast.LENGTH_LONG).show();
                finish();
            }
        }else {
            int current=Integer.parseInt(bean.getField7value());
            int total=Integer.parseInt(bean.getField6value());
            int add=Integer.parseInt(arrivalHeadBean.getIquantity());

            if(current+add>total){
                Toast.makeText(ProductionwarehousingActivity.this, "数量不能大于需求数量！", Toast.LENGTH_LONG).show();
                return;

            }else {

                updateList(confirmlistBean);
                if(!company.equals("新傲科技")) {
                    if (Integer.parseInt(confirmlistBean.getField8value()) == 0) {
                        Toast.makeText(ProductionwarehousingActivity.this, "当前货物已达到需求数量！", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }


            }
        }
    }

    private void updateList(ConfirmlistBean confirmlistBean) {
        Untils.updateIquantity(confirmlistBean,Integer.parseInt(arrivalHeadBean.getIquantity()));
        detailPreferences.edit().putString("data",new Gson().toJson(detailsList)).commit();
        if(company.equals("新傲科技")){
            setList();
            binding.etBoxcode.setText("");
            binding.etBoxcode.setFocusable(true);
            binding.etBoxcode.setFocusableInTouchMode(true);
            binding.etBoxcode.requestFocus();

        }
    }


    private void changeIquantity(int times) {
        if(arrivalHeadBean!=null){

            BigDecimal i=new BigDecimal(Double.parseDouble(arrivalHeadBean.getIquantity()));
            BigDecimal time=new BigDecimal(times);

            binding.etIquantity.setText(time.multiply(i)+"");

        }
    }
    /**
     * 制造入库列表
     */
    private void setList() {

        String stringarrivalHeadBeans="";
        stringarrivalHeadBeans= sharedPreferences.getString(menuBean.getMenucode()+"List", "");

        ArrayList<ArrivalHeadBean> arrivalHeadBeans = new ArrayList<>();
        if (!stringarrivalHeadBeans.equals("")) {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(stringarrivalHeadBeans).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                arrivalHeadBeans.add(gson.fromJson(jsonElement, ArrivalHeadBean.class));
            }
        }



        String stringscandata="";

        stringscandata= sharedPreferences.getString(menuBean.getMenucode()+"Scan", "");

        if(company.equals("瑞格菲克斯")||company.equals("新傲科技")){

        }else {
            if(stringscandata.contains(stringScan) ){
                Toast.makeText(ProductionwarehousingActivity.this, "此二维码数据已添加", Toast.LENGTH_LONG).show();
                binding.etBatch.setText("");
                return;
            }
        }




        binding.tvTotal.setText("总计："+arrivalHeadBeans.size()+"条");
        //update sharedPreferences->putlist item

        if(menuBean.getMenushowname().equals("销售发货")){
            arrivalHeadBean.setCustomer(binding.etCustomer.getText().toString());

        }





        arrivalHeadBeans.add(arrivalHeadBean);
        String strings = new Gson().toJson(arrivalHeadBeans);
        Log.i(menuBean.getMenucode()+"List", strings);
        sharedPreferences.edit().putString(menuBean.getMenucode()+"List", strings).commit();

        if(company.equals("林肯SKF")) {
            iToast.showToast(ProductionwarehousingActivity.this,"已添加至列表！",1000);
        }



        //update sharedPreferences->scan item
        List<String> listscan=new ArrayList<>();

        if(!stringscandata.isEmpty()){
            listscan=Untils.stingsToList(stringscandata);
        }

        listscan.add(stringScan);




        getCount();

        sharedPreferences.edit().putString(menuBean.getMenucode()+"Scan",listscan.toString()).commit();


    }

    private void initData() {
        arrivalHeadBean=null;
        binding.setBean(arrivalHeadBean);
        binding.etBatch.setText("");


        file=null;
        binding.etBatch.setFocusable(true);
        binding.etBatch.setFocusableInTouchMode(true);
        binding.etBatch.requestFocus();


    }



    @Override
    protected void onStart() {
        super.onStart();

        getCount();
    }
    //获取已加入清单量
    private void getCount() {
        ArrayList<ArrivalHeadBean> arrivalHeadBeans = new ArrayList<>();
        String stringarrivalHeadBeans="";

        stringarrivalHeadBeans = sharedPreferences.getString(menuBean.getMenucode()+"List", "");
        if (!stringarrivalHeadBeans.equals("")) {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(stringarrivalHeadBeans).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                arrivalHeadBeans.add(gson.fromJson(jsonElement, ArrivalHeadBean.class));
            }
        }
        binding.tvTotal.setText("总计："+arrivalHeadBeans.size()+"条");
    }

}
