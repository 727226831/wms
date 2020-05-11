package com.lkrh.storescontrol.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.Completion1Bean;
import com.lkrh.storescontrol.databinding.ItemCompletion1Binding;

import java.util.List;

public class Completion1Adapter extends  RecyclerView.Adapter<Completion1Adapter.RecyclerViewHolder> {
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCompletion1Binding binding= DataBindingUtil.
                inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_completion1,null,false);

        return new RecyclerViewHolder(binding.getRoot());
    }

    private List<Completion1Bean> mDatas;
    public Completion1Adapter(List<Completion1Bean> data) {
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder vh, final int i) {

        ItemCompletion1Binding  binding=DataBindingUtil.getBinding(vh.itemView);
         Completion1Bean bean=mDatas.get(i);
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
