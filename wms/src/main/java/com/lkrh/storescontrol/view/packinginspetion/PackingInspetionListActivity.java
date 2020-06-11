package com.lkrh.storescontrol.view.packinginspetion;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.databinding.ItemPackingInspetionBinding;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.view.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class PackingInspetionListActivity extends BaseActivity {
    RecyclerView recyclerView;
    List<String> stringList;
    private FunctionAdapter functionAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v=getLayoutInflater().inflate(R.layout.activity_production_list,null,false);
        setContentView(v);
        Untils.initTitle(getIntent().getStringExtra("menuname"),this);
        String codelist=getIntent().getStringExtra("listCode");
        codelist=codelist.substring(1,codelist.length()-1);
        stringList= Arrays.asList(codelist.split(","));

        recyclerView=findViewById(R.id.rv_list);
        functionAdapter= new FunctionAdapter(stringList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(functionAdapter);
    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
    public   class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.VH>{

        private OnItemClickListener listener;

        //第二步， 写一个公共的方法
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
        @NonNull
        @Override
        public FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
            ItemPackingInspetionBinding binding= DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.item_packing_inspetion,viewGroup,false);
            binding.lItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(i);
                }
            });
            return new FunctionAdapter.VH(binding.getRoot());
        }

        private List<String> mDatas;
        public FunctionAdapter(List<String> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull  FunctionAdapter.VH vh, final int i) {
            ItemPackingInspetionBinding binding=DataBindingUtil.getBinding(vh.itemView);
            binding.tvCode.setText(mDatas.get(i));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{

            public VH(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
