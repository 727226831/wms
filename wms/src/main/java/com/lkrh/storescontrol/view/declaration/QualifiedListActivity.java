package com.lkrh.storescontrol.view.declaration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.view.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QualifiedListActivity extends BaseActivity {
    RecyclerView recyclerView;
    List<String> stringList=new ArrayList<>();
    EditText editText;
    FunctionAdapter adapter;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualified_list);
        Untils.initTitle("完工合格",this);
        recyclerView=findViewById(R.id.rv_list);
        editText=findViewById(R.id.et_qualified);
        sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(handler.hasMessages(0)){
                    handler.removeMessages(0);
                }
                handler.sendEmptyMessageDelayed(0,1000);

            }
        });

        if(!sharedPreferences.getString("QualifiedList","").equals("")) {
            String string=sharedPreferences.getString("QualifiedList","");
            stringList =new ArrayList<>( Arrays.asList(string.substring(1,string.length()-1).split(",")));
            adapter=new FunctionAdapter(stringList);
            recyclerView.setLayoutManager(new LinearLayoutManager(QualifiedListActivity.this));
            recyclerView.addItemDecoration(new DividerItemDecoration(QualifiedListActivity.this,DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);

        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    stringList.add(editText.getText().toString());
                    qualified=stringList.size();

                    sharedPreferences.edit().putString("QualifiedList",stringList.toString()).commit();
                    editText.setText("");

                    Log.i("QualifiedList",sharedPreferences.getString("QualifiedList", ""));

                    handler.removeMessages(0);
                    adapter=new FunctionAdapter(stringList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(QualifiedListActivity.this));
                    recyclerView.addItemDecoration(new DividerItemDecoration(QualifiedListActivity.this,DividerItemDecoration.VERTICAL));
                    recyclerView.setAdapter(adapter);

                    editText.requestFocus();
                    editText.setSelection(editText.getText().toString().length());




                    break;

            }


        }
    };
    class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.VH>{

        @NonNull
        @Override
        public FunctionAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v=getLayoutInflater().inflate(R.layout.item_qualified,null,false);
            return new FunctionAdapter.VH(v);


        }

        private List<String> mDatas;
        public FunctionAdapter(List<String> data) {
            this.mDatas = data;
        }

        @Override
        public void onBindViewHolder(@NonNull FunctionAdapter.VH vh, final int i) {
          vh.textViewcValue.setText(mDatas.get(i));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
        class  VH extends RecyclerView.ViewHolder{
            TextView textViewcValue;
            public VH(@NonNull View itemView) {
                super(itemView);
               textViewcValue=itemView.findViewById(R.id.tv_value);

            }
        }
    }
}
