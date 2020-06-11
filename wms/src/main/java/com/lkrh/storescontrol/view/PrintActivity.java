package com.lkrh.storescontrol.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.ArrivalHeadBean;
import com.lkrh.storescontrol.databinding.ActivityPrintBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.qr285.sdk.OnPrinterListener;
import com.qr285.sdk.PrinterPort;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PrintActivity extends BaseActivity {
    private BluetoothAdapter mBluetoothAdapter = null;
    private PrinterPort printerPort;
    View viewtag;
    ActivityPrintBinding binding;
    String code;

    List<String> list;
    ArrivalHeadBean arrivalHeadBean;

    double overplus=-1;
    boolean isRegister=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_print);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Untils.initTitle("打印",this);
       viewtag=binding.getRoot().findViewById(R.id.rl_tag);
        code=getIntent().getStringExtra("code");
        overplus=getIntent().getDoubleExtra("overplus",-1);

        arrivalHeadBean=getIntent().getParcelableExtra("arrivalHeadBean");
        binding.setArrivalHeadBean(arrivalHeadBean);


        if(!code.isEmpty()){
            String  numbers=code.replace("$",",");
             list= Arrays.asList(numbers.split(","));

            initView( Double.parseDouble(list.get(2))-overplus+"");
        }



        binding.bPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(printerPort==null) {
                    initbluetooth();
                }
               printeData();
            }
        });






    }

    private void initView(String iquantity) {

        list.set(2,iquantity+"");
        list.set(6,UUID.randomUUID().toString());
        String codenew=list.toString().replace(",","$");
        codenew=codenew.substring(1,codenew.length()-2);
        codenew=codenew.replace(" ","");
        createCode(codenew);
    }
    // boolean isRegister=false;
    private void initbluetooth() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        isRegister=true;
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        isRegister=true;
        this.registerReceiver(mReceiver, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            return;
        }
        // If we're already discovering, stop it
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBluetoothAdapter.startDiscovery();
        printerPort = new PrinterPort(PrintActivity.this, new OnPrinterListener() {
            @Override
            public void onConnected() {
                Toast.makeText(PrintActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectedFail() {

            }

            @Override
            public void ondisConnected() {
                Toast.makeText(PrintActivity.this, "连接断开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ondisConnectedFail() {

            }

            @Override
            public void onAbnormaldisconnection() {
                Toast.makeText(PrintActivity.this, "打印机异常断开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onsateOFF() {
                Toast.makeText(PrintActivity.this, "蓝牙关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onsateOn() {
                Toast.makeText(PrintActivity.this, "蓝牙开启", Toast.LENGTH_SHORT).show();
            }
        });
        Set<BluetoothDevice> pairedDevices =mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size()>0){
            for (BluetoothDevice device : pairedDevices) {
                Log.i("device-bind",device.getName());
                if(device.getName().contains("QR-285A")){
                    mBluetoothAdapter.cancelDiscovery();
                    printerPort.connect(device.getAddress());

                }
            }

        }else {
            Log.i("device-","no one");
        }
    }

    private void createCode(String content) {

        Log.i("content-->",content);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 200, 200);
            binding.ivCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter!= null) {
            mBluetoothAdapter.cancelDiscovery();
        }
        if(printerPort!=null) {
            printerPort.disconnect();
        }
        // Unregister broadcast listeners
           if(isRegister) {
            isRegister=false;
               this.unregisterReceiver(mReceiver);
           }

    }

    public void printeData() {
        viewtag.setDrawingCacheEnabled(true);
        viewtag.buildDrawingCache();
        final Bitmap bitmap=Bitmap.createBitmap(viewtag.getDrawingCache());
        viewtag.destroyDrawingCache();
        Matrix matrix = new Matrix();

        matrix.setRotate(90);
        matrix.postScale(0.7f, 0.7f);
        Bitmap bitmapnew=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,false);
       final Bitmap bmp = bitmapnew;

        new Thread(new Runnable() {
            @Override
            public void run() {
                printerPort.setDensity(0x02, 10);
                printerPort.setPaperType(0x02,0x20);

                if(binding.etPage.getText().toString().isEmpty()){
                    printerPort.printBitmap(bmp);
                    printerPort.printerLocation(0x20,0);
                }else {
                    for (int i = 0; i < Integer.parseInt(binding.etPage.getText().toString()); i++) {
                        printerPort.printBitmap(bmp);
                        printerPort.printerLocation(0x20,0);
                    }
                }



              //  printerPort.adjustPosition(0, 240);
                printerPort.printerLocation(0x20,10);
                if (printerPort.getSendResult(10000).equals("OK")) {
                    PrintActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(PrintActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    PrintActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(PrintActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();
    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.i("device-unbind",device.getName());
                    //  mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        }
    };
}
