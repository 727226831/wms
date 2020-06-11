package com.lkrh.storescontrol.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.ConfirmBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.url.Request
import com.lkrh.storescontrol.untils.Untils
import kotlinx.android.synthetic.main.activity_pallet_adjustment.*
import kotlinx.android.synthetic.main.activity_pallet_adjustment.b_submit
import kotlinx.android.synthetic.main.activity_scan_check.*
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PalletAdjustmentActivity : BaseActivity() {
    var menuBean: LoginBean.Menu? = null
    var tag = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pallet_adjustment)

        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)
        b_submit.setOnClickListener(View.OnClickListener { updateData("整托调整") })
        iv_cwhcode.setOnClickListener(View.OnClickListener { openScan()  })
        iv_updatecwhcode.setOnClickListener(View.OnClickListener { openScan() })
    }
    private fun updateData(text: String) {
        dialog.show()
        val jsonObject = JSONObject()
        try {
            jsonObject.put("methodname", "updateDocVouch")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("acccode", BaseActivity.acccode)
            jsonObject.put("menucode", menuBean!!.getMenucode())
            jsonObject.put("layout", "2")
            jsonObject.put("button", text)

            jsonObject.put("condition", "")
            jsonObject.put("formdata", "")

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val obj = jsonObject.toString()
        Log.i("json object", obj)
        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                dialog.dismiss()
                if (response.code() == 200) {
                    try {
                        val confirmBean = Gson().fromJson(response.body()!!.string(), ConfirmBean::class.java)
                        Toast.makeText(this@PalletAdjustmentActivity, confirmBean.resultMessage, Toast.LENGTH_SHORT).show()
                        if (confirmBean.resultcode == "200") {

                            finish()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
            }
        })
    }
    private fun openScan() {
        IntentIntegrator(this)
                .setCaptureActivity(ScanActivity::class.java)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES) // 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码") // 设置提示语
                .setCameraId(0) // 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false) // 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true) // 扫完码之后生成二维码的图片
                .initiateScan() // 初始化扫码
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        //扫码
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val code = result.contents
                Log.i("scan", code)
                et_code.setText(code)


            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
}
