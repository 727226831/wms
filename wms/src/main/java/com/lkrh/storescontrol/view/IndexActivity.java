package com.lkrh.storescontrol.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.drawable.Drawable;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityIndexBinding;
import com.lkrh.storescontrol.view.packinginspetion.PackingInspetionActivity;
import com.lkrh.storescontrol.view.confirm.ConfirmActivity;
import com.google.gson.Gson;
import com.lkrh.storescontrol.view.inspection.Inspectionlist2Activity;
import com.lkrh.storescontrol.view.test.AreaselectActivity;

public class IndexActivity extends BaseActivity {

    RecyclerView recyclerView;
    FunctionAdapter functionAdapter;
    TextView titleTv;
    LoginBean userinfoBean;
    ActivityIndexBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_index);

        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String data=sharedPreferences.getString("userinfo","");
        if(!data.equals("")){
            userinfoBean=new Gson().fromJson(data,LoginBean.class);
        }



        binding.setUser(userinfoBean);
        binding.tvUsername.setText(userinfoBean.getUsername()+"("+userinfoBean.getAcccode()+")");

        titleTv=binding.getRoot().findViewById(R.id.tv_title);
        titleTv.setText("首页");

        functionAdapter=new FunctionAdapter();
        recyclerView=findViewById(R.id.rv_function);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(functionAdapter);
        binding.bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexActivity.this,LoginActivity.class));
                IndexActivity.this.finish();
            }
        });


    }



    class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.VH>{

        @NonNull
        @Override
        public FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v=getLayoutInflater().inflate(R.layout.item_funcation,viewGroup,false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh, final int i) {
            vh.funcationButton.setText(userinfoBean.getMenu().get(i).getMenushowname());


            vh.funcationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=null;
                    if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货确认")){
                        if(company.equals("浦东瀚氏")){
                            intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                        }else {
                            intent  =new Intent(IndexActivity.this, ConfirmActivity.class);
                        }

                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货送检")){
                        intent  =new Intent(IndexActivity.this, Inspectionlist2Activity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货初检")){
                        intent  =new Intent(IndexActivity.this, AreaselectActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货复检")){
                        intent  =new Intent(IndexActivity.this, AreaselectActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货挑选")){
                        intent  =new Intent(IndexActivity.this, AreaselectActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("破坏抽检")){
                        intent  =new Intent(IndexActivity.this, ConfirmActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("采购入库")){
                        if(company.equals("浦东瀚氏")||company.equals("新傲科技")){
                            intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                            intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                        }

                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("到货入库")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenucode().equals("SCRK")){
                        //生产入库 产成品入库
                        if(company.equals("新傲科技")){
                            intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                            intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                        }


                    }else   if(userinfoBean.getMenu().get(i).getMenucode().equals("HZCLCK")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("其他入库")){
                        if(company.equals("瑞格菲克斯")||company.equals("林肯SKF")) {
                            intent  =new Intent(IndexActivity.this, ChartListActivity.class);
                        }else  if(company.equals("新傲科技")){
                            intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                            intent = new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                        }
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("其他出库")){
                        if(company.equals("瑞格菲克斯")||company.equals("林肯SKF")) {
                            intent  =new Intent(IndexActivity.this, ChartListActivity.class);
                        }else  if(company.equals("新傲科技")){
                            intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                            intent = new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                        }

                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("货位调整")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("存货盘点")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("委外发料")){

                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("补料发料")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("其他出库确认")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("其他入库确认")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("生产入库确认")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);
                    }else   if(userinfoBean.getMenu().get(i).getMenushowname().equals("库存调拨")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("销售发货")){
                         if(company.equals("瑞格菲克斯")){
                             intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                             intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);
                         }

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("库存查询")){
                        intent  =new Intent(IndexActivity.this, StockcheckActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("生产订单下达")){
                        intent  =new Intent(IndexActivity.this, ProductionOrderActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("发货检查")){
                        intent  =new Intent(IndexActivity.this, PackingInspetionActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("生产订单变更")){
                        intent  =new Intent(IndexActivity.this, OrderChangeActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("库存看板")){
                        intent  =new Intent(IndexActivity.this, ChartListActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("生产领料申请")){
                        intent  =new Intent(IndexActivity.this, ClaimMaterialActivity.class);
                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("材料出库")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("销售出库")){
                      //  intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        if(company.equals("新傲科技")){
                            intent  =new Intent(IndexActivity.this, BillListActivity.class);
                        }else {
                            intent  =new Intent(IndexActivity.this, StockAllocationActivity.class);
                        }

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("采购退料")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("装车发运")){
                        intent  =new Intent(IndexActivity.this, LogisticsDistributionActivity.class);

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("备货调拨")){
                        intent  =new Intent(IndexActivity.this, StockAllocationActivity.class);

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("发货出库")){
                        intent  =new Intent(IndexActivity.this, ProductionwarehousingActivity.class);

                    }else if(userinfoBean.getMenu().get(i).getMenushowname().equals("扫码检查")){
                        intent  =new Intent(IndexActivity.this, BillListActivity.class);

                    }else {
                        return;
                    }
                    intent.putExtra("menuname",userinfoBean.getMenu().get(i).getMenushowname());
                    intent.putExtra("menubean",userinfoBean.getMenu().get(i));
                    startActivity(intent);
                }
            });
            Drawable drawableLeft = null;
            if(userinfoBean.getMenu().get(i).getMenushowname().contains("入库")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_put);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("出库")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_put);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("盘点")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_inventory);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("查询")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_search);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("填报")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_dbrk);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("到货")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_input);
            }else if(userinfoBean.getMenu().get(i).getMenushowname().contains("调整")){
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_input);
            }else {
                drawableLeft = IndexActivity.this.getResources().getDrawable(R.mipmap.ic_input);
            }
            vh.funcationButton.setCompoundDrawablesWithIntrinsicBounds(null,
                    drawableLeft, null, null);
        }

        @Override
        public int getItemCount() {
            return userinfoBean.getMenu().size();
        }
        class  VH extends RecyclerView.ViewHolder{
            Button funcationButton;
            public VH(@NonNull View itemView) {
                super(itemView);
                funcationButton=itemView.findViewById(R.id.b_funcation);
            }
        }
    }
}
