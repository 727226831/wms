package com.lkrh.storescontrol.view.declaration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Handler;
import android.os.Message;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.url.Request;
import com.lkrh.storescontrol.untils.Untils;
import com.lkrh.storescontrol.bean.MoInfoBean;
import com.lkrh.storescontrol.databinding.ActivityReportBinding;
import com.lkrh.storescontrol.view.BaseActivity;
import com.lkrh.storescontrol.view.ScanActivity;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 完工报单
 */
public class ReportActivity extends BaseActivity {
    ActivityReportBinding binding;
    String  stringScan;
    List<String> list;
    String COpdesc;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_report);
        Untils.initTitle("完工报单",this);


        sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("QualifiedList","").commit();

          binding.etCusercode.addTextChangedListener(new TextWatcher() {
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
          binding.etCmemo2.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {

              }

              @Override
              public void afterTextChanged(Editable s) {
                  if(handler.hasMessages(1)){
                      handler.removeMessages(1);
                  }
                  handler.sendEmptyMessageDelayed(1,1000);

              }
          });
          binding.etCode.setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View v, int keyCode, KeyEvent event) {

                  if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                          getMoInfo(binding.etCode.getText().toString());

                  }

                  return false;
              }
          });
          binding.bSubmit.setOnClickListener(onClickListener);
          binding.bMaterial.setOnClickListener(onClickListener);
          binding.ivScan.setOnClickListener(onClickListener);
          binding.llUnqualified.setOnClickListener(onClickListener);
          binding.bUser.setOnClickListener(onClickListener);
          binding.bDes.setOnClickListener(onClickListener);
          binding.llQualified.setOnClickListener(onClickListener);



    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
              switch (v.getId()){
                  case R.id.b_submit:

                      if(list!=null) {
                          createWG();
                          createSerialIn();
                      }
                      break;
                  case R.id.b_material:
                      if(list!=null) {
                          Intent intent = new Intent(ReportActivity.this, MaterialActivity.class);
                          intent.putExtra("cmocode", list.get(0));
                          intent.putExtra("copname",COpdesc);
                          intent.putExtra("cuser",binding.etCusercode.getText().toString());
                          intent.putExtra("ccode",list.get(1));
                          intent.putExtra("ctuopan1",binding.etCtuopan1.getText().toString());
                          startActivity(intent);
                      }
                      break;
                  case R.id.iv_scan:
                      openScan();
                      break;
                  case R.id.ll_unqualified:
                      if(list!=null) {
                      Intent intent = new Intent(ReportActivity.this, UnqualifiedListActivity.class);
                      intent.putExtra("cmocode", list.get(0));
                      startActivity(intent);
                      }
                      break;
                  case R.id.ll_qualified:
                          Intent intent = new Intent(ReportActivity.this, QualifiedListActivity.class);
                          startActivity(intent);

                      break;
                  case R.id.b_user:
                      if(!binding.etCusercode.getText().toString().isEmpty()){
                          getMesUserName();
                      }

                      break;
                  case  R.id.b_des:
                      if(!binding.etCmemo2.getText().toString().isEmpty()){
                          getDesName();
                      }

                      break;

              }
        }
    };

    private void createSerialIn() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","CreateSerialIn");
            jsonObject.put("acccode",acccode);
            jsonObject.put("cmocode",list.get(0));
            jsonObject.put("ccode",list.get(1));
            jsonObject.put("userid",binding.etCusercode.getText());
            if(sharedPreferences.getString("QualifiedList","").equals("")){
                jsonObject.put("datatetails","");
            }else {
                jsonObject.put("datatetails",new JSONArray(sharedPreferences.getString("QualifiedList","")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        sharedPreferences.edit().putString("QualifiedList","").commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }

    private void openScan() {

        new IntentIntegrator(ReportActivity.this)
                .setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan();// 初始化扫码

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);

        String string=sharedPreferences.getString("QualifiedList","");
        if(!string.equals("")) {
            List<String> stringList = Arrays.asList(string.substring(1, string.length() - 1).split(","));
            binding.etIhgqty.setText(stringList.size() + "");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                String code=result.getContents();
                Log.i("scan",code);
                binding.etCode.setText(code);
                getMoInfo(code);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void getMesUserName() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetMesUserName");

            jsonObject.put("cusercode",binding.etCusercode.getText().toString().substring(0,binding.etCusercode.getText().toString().length()-1));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                     binding.etCusercode.setText(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
    private void getDesName() {
        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetDesName");
            jsonObject.put("ccode",binding.etCmemo2.getText().toString().substring(0,binding.etCmemo2.getText().length()-1));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        binding.etCmemo2.setText(response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }


    private void getMoInfo(String code) {
        list=Untils.parseCode(code,1);
        Log.i("list-->",list.toString());
        stringScan=binding.etCode.getText().toString();

        if(list.isEmpty() || list.size()<3){
            return;
        }

        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","GetMoInfo");
            jsonObject.put("acccode",acccode);
            jsonObject.put("cmocode",list.get(0));
            jsonObject.put("ccode",list.get(1));
            jsonObject.put("copcode",list.get(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        JSONArray jsonArray=new JSONArray(response.body().string());
                        MoInfoBean bean=new Gson().fromJson(jsonArray.getJSONObject(0).toString(),MoInfoBean.class);
                        binding.tvCmocode.setText(bean.getCmocode());
                        binding.tvCOpdesc.setText(bean.getCOpdesc());
                        COpdesc=bean.getCOpdesc();
                        binding.tvImoqty.setText(bean.getImoqty()+"");
                        binding.tvCInvName.setText(bean.getCInvName());

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }
    //完工填报表单提交
    private void createWG() {

        JSONObject jsonObject=new JSONObject();
        try {

            jsonObject.put("methodname","CreateWG");
            jsonObject.put("acccode",acccode);
            jsonObject.put("cmocode",list.get(0));
            jsonObject.put("ccode",list.get(1));
            jsonObject.put("copname",COpdesc);
            jsonObject.put("copcode",list.get(2));
            jsonObject.put("ihgqty",binding.etIhgqty.getText());
            jsonObject.put("ibhgqty",binding.etIbhgqty.getText());
            jsonObject.put("ctuopan1", binding.etCtuopan1.getText());
            jsonObject.put("ctuopan2",binding.etCtuopan2.getText());
            jsonObject.put("cmemo","");
            jsonObject.put("cmemo2",binding.etCmemo2.getText());
            jsonObject.put("cusercode",binding.etCusercode.getText());
            jsonObject.put("clasttuopan",binding.etCtuopan.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String obj=jsonObject.toString();
        Log.i("json object",obj);

        Call<ResponseBody> data =Request.getRequestbody(obj);
        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if(response.code()==200) {
                        JSONObject jsonObjectresponse=new JSONObject(response.body().string());
                        Toast.makeText(ReportActivity.this,
                                jsonObjectresponse.getString("ResultMessage"),Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(ReportActivity.this).setMessage("是否打印?")
                                .setNegativeButton("需要", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(ReportActivity.this,ReportprintActivity.class);
                                        intent.putExtra("cmocode", list.get(0));
                                        intent.putExtra("ccode",list.get(1));
                                        intent.putExtra("copcode",list.get(2));
                                        intent.putExtra("iqty",binding.etIhgqty.getText().toString());
                                        intent.putExtra("user",binding.etCusercode.getText().toString());
                                        intent.putExtra("ctuopan1",binding.etCtuopan1.getText().toString());
                                        intent.putExtra("clasttuopan",binding.etCtuopan.getText().toString());

                                        startActivity(intent);
                                    }
                                }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

            } });
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    binding.etCusercode.setText(binding.etCusercode.getText().toString()+",");
                    handler.removeMessages(0);
                    binding.etCusercode.requestFocus();
                    binding.etCusercode.setSelection(binding.etCusercode.getText().length());
                    break;
                case 1:
                    binding.etCmemo2.setText(binding.etCmemo2.getText().toString()+",");
                    handler.removeMessages(1);
                    binding.etCmemo2.requestFocus();
                    binding.etCmemo2.setSelection(binding.etCmemo2.getText().length());
                    break;
            }


        }
    };


}
