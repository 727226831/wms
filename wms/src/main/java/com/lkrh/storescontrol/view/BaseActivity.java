package com.lkrh.storescontrol.view;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lkrh.storescontrol.R;


public class BaseActivity extends AppCompatActivity {
    public static   String acccode,usercode;
    public static  String company="";
    public  AlertDialog dialog;
    public static  int qualified=0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AlertDialog.Builder builder=new AlertDialog.Builder(this).setView(R.layout.layout_progress);
        dialog=builder.create();



    }





}
