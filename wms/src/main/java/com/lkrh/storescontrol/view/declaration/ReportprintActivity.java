package com.lkrh.storescontrol.view.declaration;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.adapter.Completion1Adapter;
import com.lkrh.storescontrol.adapter.Completion2Adapter;
import com.lkrh.storescontrol.bean.Completion1Bean;
import com.lkrh.storescontrol.bean.MeterialBean;
import com.lkrh.storescontrol.view.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.qr285.sdk.OnPrinterListener;
import com.qr285.sdk.PrinterPort;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportprintActivity extends BaseActivity {


    private BluetoothAdapter mBluetoothAdapter = null;
    private PrinterPort printerPort;
    boolean isRegister=false;
    View viewPrint;
    RecyclerView recyclerView1,recyclerView2;
    List<Completion1Bean> completion1BeanList=new ArrayList<>();
    List<MeterialBean> meterialBeanList=new ArrayList<>();
    ImageView imageViewQRcode;
    TextView textViewcinvname;
    TextView textViewtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportprint);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button buttonPrint=findViewById(R.id.b_print);
        buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(printerPort==null) {
                    initbluetooth();
                }
                printeData();
            }
        });
        viewPrint=findViewById(R.id.ll_form);
        recyclerView1=findViewById(R.id.rv_list1);
        recyclerView2=findViewById(R.id.rv_list2);
        imageViewQRcode=findViewById(R.id.iv_qrcode);
        textViewcinvname=findViewById(R.id.tv_cinvname);
        textViewtime=findViewById(R.id.tv_time);



        getMeteriallist();
        getGetWGInfo();



    }
    private void createCode(String content) {


        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 200, 200);
            imageViewQRcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    private void getGetWGInfo() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetWGInfo");
            jsonObject.put("acccode",acccode);
            jsonObject.put("ccode",getIntent().getStringExtra("ccode"));
            jsonObject.put("cmocode",getIntent().getStringExtra("cmocode"));
            jsonObject.put("clasttuopan",getIntent().getStringExtra("clasttuopan"));
            jsonObject.put("ctuopan1",getIntent().getStringExtra("ctuopan1"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        Gson gson = new Gson();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            completion1BeanList.add(gson.fromJson(jsonElement, Completion1Bean.class));
                        }

                        for (int i = 0; i <completion1BeanList.size() ; i++) {
                            if(completion1BeanList.get(i).getCopcode().equals(getIntent().getStringExtra("copcode"))){

                                completion1BeanList.get(i).setCuser(getIntent().getStringExtra("user"));
                                completion1BeanList.get(i).setIqty(getIntent().getStringExtra("iqty"));
                            }

                        }

                        textViewcinvname.setText(completion1BeanList.get(completion1BeanList.size()-1).getCinvname());
                        textViewtime.setText("面料进入静置房时间:"+completion1BeanList.get(0).getDpritdate());
                        recyclerView1.setLayoutManager(new LinearLayoutManager(ReportprintActivity.this));
                        recyclerView1.addItemDecoration(new DividerItemDecoration(ReportprintActivity.this,DividerItemDecoration.VERTICAL));
                        Completion1Adapter completion1Adapter=new Completion1Adapter(completion1BeanList);
                        recyclerView1.setAdapter(completion1Adapter);



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
    private void getMeteriallist() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetPrintBeiliao");
            jsonObject.put("acccode",acccode);
            jsonObject.put("ccode",getIntent().getStringExtra("ccode"));
            jsonObject.put("cmocode",getIntent().getStringExtra("cmocode"));
            jsonObject.put("ctuopan1",getIntent().getStringExtra("ctuopan1"));
            jsonObject.put("clasttuopan",getIntent().getStringExtra("clasttuopan"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data = Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        Gson gson = new Gson();
                        JsonArray arry = new JsonParser().parse(response.body().string()).getAsJsonArray();
                        for (JsonElement jsonElement : arry) {
                            meterialBeanList.add(gson.fromJson(jsonElement, MeterialBean.class));
                        }
                        Date curDate =  new Date(System.currentTimeMillis());
                        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd");
                        createCode(meterialBeanList.get(0).getCinvcode()+"$"+meterialBeanList.get(0).getCbatch()+"$"
                                +meterialBeanList.get(0).getIqty()+"$"+"$"
                                +getIntent().getStringExtra("cmocode")+"$"+"$"+formatter.format(curDate)+"$"+meterialBeanList.get(0).getCvenbatch());


                        recyclerView2.setLayoutManager(new LinearLayoutManager(ReportprintActivity.this));
                        recyclerView2.addItemDecoration(new DividerItemDecoration(ReportprintActivity.this,DividerItemDecoration.VERTICAL));
                        Completion2Adapter completion2Adapter=new Completion2Adapter(meterialBeanList);
                        recyclerView2.setAdapter(completion2Adapter);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }


    public void printeData() {
        viewPrint.setDrawingCacheEnabled(true);
        viewPrint.buildDrawingCache();
        Bitmap bitmap=Bitmap.createBitmap(viewPrint.getDrawingCache());
        viewPrint.destroyDrawingCache();

        Matrix matrix = new Matrix();
        matrix.postScale(0.7f, 0.7f);
        matrix.postRotate(90);
        Bitmap bitmapnew=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        final Bitmap bmp = bitmapnew;

        new Thread(new Runnable() {
            @Override
            public void run() {
                printerPort.setDensity(0x02, 16);
                printerPort.printBitmap(bmp);
                //  printerPort.adjustPosition(0, 240);
                printerPort.printerLocation(0x20,10);
                if (printerPort.getSendResult(10000).equals("OK")) {
                    ReportprintActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ReportprintActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    ReportprintActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ReportprintActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();
    }
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
        printerPort = new PrinterPort(ReportprintActivity.this, new OnPrinterListener() {
            @Override
            public void onConnected() {
                Toast.makeText(ReportprintActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onConnectedFail() {

            }

            @Override
            public void ondisConnected() {
                Toast.makeText(ReportprintActivity.this, "连接断开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void ondisConnectedFail() {

            }

            @Override
            public void onAbnormaldisconnection() {
                Toast.makeText(ReportprintActivity.this, "打印机异常断开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onsateOFF() {
                Toast.makeText(ReportprintActivity.this, "蓝牙关闭", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onsateOn() {
                Toast.makeText(ReportprintActivity.this, "蓝牙开启", Toast.LENGTH_SHORT).show();
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


}
