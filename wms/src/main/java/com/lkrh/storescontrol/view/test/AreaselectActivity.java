package com.lkrh.storescontrol.view.test;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.LoginBean;
import com.lkrh.storescontrol.databinding.ActivityAreaselectBinding;
import com.lkrh.storescontrol.untils.Untils;

public class AreaselectActivity extends AppCompatActivity {
    LoginBean.Menu menuBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAreaselectBinding binding= DataBindingUtil.setContentView(AreaselectActivity.this,R.layout.activity_areaselect);
        menuBean=getIntent().getParcelableExtra("menubean");
        Untils.initTitle(menuBean.getMenushowname(),this);
        binding.lOne.setOnClickListener(onClickListenern);
        binding.lTwo.setOnClickListener(onClickListenern);
        binding.lThree.setOnClickListener(onClickListenern);
        binding.lFour.setOnClickListener(onClickListenern);


    }
    View.OnClickListener onClickListenern=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(AreaselectActivity.this,TestlistActivity.class);
            intent.putExtra("menubean",getIntent().getParcelableExtra("menubean"));
             switch (v.getId()){
                 case R.id.l_one:
                     intent.putExtra("condition","待检区1");
                     break;
                 case R.id.l_two:
                     intent.putExtra("condition","待检区2");
                     break;
                 case R.id.l_three:
                     intent.putExtra("condition","待检区3");
                     break;
                 case R.id.l_four:
                     intent.putExtra("condition","待检区4");
                     break;

             }
             startActivity(intent);
        }
    };

}
