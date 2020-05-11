package com.lkrh.storescontrol.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.MeterialBean;
import com.lkrh.storescontrol.databinding.ItemCompletion2Binding;

import java.util.List;

public class Completion2Adapter extends  RecyclerView.Adapter<Completion2Adapter.RecyclerViewHolder> {
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCompletion2Binding binding= DataBindingUtil.
                inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_completion2,null,false);

        return new RecyclerViewHolder(binding.getRoot());
    }

    private List<MeterialBean> mDatas;
    public Completion2Adapter(List<MeterialBean> data) {
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder vh, final int i) {

        ItemCompletion2Binding binding=DataBindingUtil.getBinding(vh.itemView);
        MeterialBean bean=mDatas.get(i);
        binding.setData(bean);
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class  RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(View view) {
            super(view);
        }



    }
}
