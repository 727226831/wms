package com.lkrh.storescontrol.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.zxing.integration.android.IntentIntegrator
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.ConfirmBean
import com.lkrh.storescontrol.bean.ConfirmlistBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.bean.ScanCheckBean
import com.lkrh.storescontrol.databinding.ItemPackingInspetionBinding
import com.lkrh.storescontrol.untils.Untils
import com.lkrh.storescontrol.url.Request
import kotlinx.android.synthetic.main.activity_scan_check.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScanCheckActivity :BaseActivity() {
    internal var stringList:ArrayList<String>?=null
    internal var scanCheckBeanList:ArrayList<ScanCheckBean>?=ArrayList()
    internal var scanList:ArrayList<String>?= ArrayList()
    private var functionAdapter: FunctionAdapter? = null
    internal var menuBean: LoginBean.Menu?=null
    var confirmlistBean: ConfirmlistBean? = null

    var tag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_check)

        menuBean = intent.getParcelableExtra("menubean")
        Untils.initTitle(menuBean!!.menushowname, this)
        rv_list.setLayoutManager(LinearLayoutManager(this))
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

         et_code.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_DOWN) {
               check()

            }
            true
        })
        b_search.setOnClickListener(View.OnClickListener {
            check()
        })
        iv_code.setOnClickListener(View.OnClickListener {
            openScan()
        })
        b_submit.setOnClickListener(View.OnClickListener {

            putData()
        })

        confirmlistBean=intent.getParcelableExtra("ConfirmlistBean")

        stringList= ArrayList()
        stringList=Untils.stingsToList(confirmlistBean!!.field5value)

        for (i in stringList!!.indices) {
            val scanCheckBean = ScanCheckBean("", false)
            scanCheckBean.code = stringList!![i]
            scanCheckBean.chccked = false
            scanCheckBeanList!!.add(scanCheckBean)

        }



        functionAdapter=FunctionAdapter(scanCheckBeanList!!)
        rv_list.adapter=functionAdapter
        functionAdapter!!.notifyDataSetChanged()

    }
    private fun openScan() {

        IntentIntegrator(this)
                .setCaptureActivity(ScanActivity::class.java)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                .setPrompt("请对准二维码")// 设置提示语
                .setCameraId(0)// 选择摄像头,可使用前置或者后置
                .setBeepEnabled(false)// 是否开启声音,扫完码之后会"哔"的一声
                .setBarcodeImageEnabled(true)// 扫完码之后生成二维码的图片
                .initiateScan()// 初始化扫码

    }

    private  fun  check(){

        if(scanList!!.contains(et_code.text.toString())){
            Toast.makeText(this, "条码重复", Toast.LENGTH_LONG).show()

            return
        }else{
            if(confirmlistBean!!.field3value=="顺序装箱"){

                  if(stringList!!.get(tag)==et_code.text.toString()){
                      scanList!!.add(stringList!!.get(tag))
                      scanCheckBeanList!![tag].chccked=true
                      if(tag<stringList!!.size-1){
                          tag=tag+1;
                      }
                  }else{
                      Toast.makeText(this, "条码错误", Toast.LENGTH_LONG).show()
                  }

            }else{
                if(stringList!!.contains(et_code.text.toString())){
                    scanList!!.add(et_code.text.toString())

                    for (i in scanCheckBeanList!!.indices) {
                       if(scanCheckBeanList!![i].code.contains(et_code.text.toString())){
                           scanCheckBeanList!![i].chccked=true
                       }

                    }

                }else{
                    Toast.makeText(this, "条码错误", Toast.LENGTH_LONG).show()

                }
            }

            tv_total.text="总计："+scanList!!.size

        }
        et_code.setText("")
        et_code.requestFocus()

        functionAdapter=FunctionAdapter(scanCheckBeanList!!)
        rv_list.adapter=functionAdapter
        functionAdapter!!.notifyDataSetChanged()





    }
    inner class FunctionAdapter(private val mDatas:ArrayList<ScanCheckBean>) : RecyclerView.Adapter<FunctionAdapter.VH>() {



        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FunctionAdapter.VH {
            val binding = DataBindingUtil.inflate<ItemPackingInspetionBinding>(LayoutInflater.from(viewGroup.context), R.layout.item_packing_inspetion, viewGroup, false)
            return VH(binding.root)
        }

        override fun onBindViewHolder(vh: FunctionAdapter.VH, i: Int) {
            val binding = DataBindingUtil.getBinding<ItemPackingInspetionBinding>(vh.itemView)
            if(mDatas!![i].chccked){
                binding!!.tvCode.setTextColor(resources.getColor(R.color.blue))
            }else{
                binding!!.tvCode.setTextColor(resources.getColor(R.color.black))
            }
            binding!!.tvCode.text = (i+1).toString()+"/"+mDatas!![i].code
        }

        override fun getItemCount(): Int {
            return mDatas!!.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        //扫码
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val code = result.contents
                Log.i("scan", code)
                et_code.setText(code)
                check()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }


    }
    private fun putData() {
        if(confirmlistBean!!.field3value=="顺序装箱"){
            if(scanList!!.size!=stringList!!.size){
                Toast.makeText(this@ScanCheckActivity, "存在未扫描条码", Toast.LENGTH_LONG).show()
                return
            }
        }
        dialog.show()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")// HH:mm:ss
        //获取当前时间
        val date = Date(System.currentTimeMillis())
        val strings = simpleDateFormat.format(date)


        val jsonObject = JSONObject()
        try {
            jsonObject.put("methodname", "updateDocVouch")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("acccode", BaseActivity.acccode)
            jsonObject.put("menucode", menuBean!!.getMenucode())
            jsonObject.put("layout", "1")
            jsonObject.put("button", b_submit.getText().toString())
            jsonObject.put("condition", confirmlistBean!!.field1value)

            val jsonArray = JSONArray()
            for (i in scanList!!.indices) {
                val `object` = JSONObject()
                `object`.put("barcode", scanList!!.get(i))
                jsonArray.put(`object`)
            }
            jsonObject.put("formdata",jsonArray.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val obj = jsonObject.toString()
        Log.i("requestbody-->", obj)
        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                dialog.dismiss()
                var bean: ConfirmBean? = null
                try {
                    bean = Gson().fromJson(response.body()!!.string(), ConfirmBean::class.java)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
                Toast.makeText(this@ScanCheckActivity, bean!!.resultMessage, Toast.LENGTH_LONG).show()
                finish()

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@ScanCheckActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
