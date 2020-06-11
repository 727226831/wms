package com.lkrh.storescontrol.view;


import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.lkrh.storescontrol.R;

import com.lkrh.storescontrol.bean.ConfirmlistBean;
import com.lkrh.storescontrol.bean.LoginBean;

import com.lkrh.storescontrol.databinding.ActivityStockAllocationBinding;

import com.lkrh.storescontrol.untils.Untils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class StockAllocationActivity extends BaseActivity {
    ActivityStockAllocationBinding binding;
    LoginBean.Menu menuBean;
    List<ConfirmlistBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(StockAllocationActivity.this,R.layout.activity_stock_allocation);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        menuBean=getIntent().getParcelableExtra("menubean");
        binding.bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=null;
                if(menuBean.getMenushowname().equals("销售出库")){
                    intent  =new Intent(StockAllocationActivity.this, BillListActivity.class);
                }else {
                    intent=new Intent(StockAllocationActivity.this,BillDetailActivity.class);
                }
                intent.putExtra("menubean",menuBean);
                JSONObject object=new JSONObject();
                try {
                    object.put("ccode",binding.etCcode.getText().toString());
                    object.put("cinvcode",binding.etCinvcode.getText().toString());
                    object.put("customer",binding.etCustomer.getText().toString());
                    object.put("warehouse",binding.etCwhcode.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                intent.putExtra("condition",object.toString());
                startActivity(intent);
            }
        });

    }

}
