package com.lkrh.storescontrol.view

import android.content.Context

import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.gson.Gson
import com.lkrh.storescontrol.R
import com.lkrh.storescontrol.bean.GuideBean
import com.lkrh.storescontrol.bean.LoginBean
import com.lkrh.storescontrol.databinding.ItemHwydBinding
import com.lkrh.storescontrol.url.Request
import com.lkrh.storescontrol.untils.Untils

import org.json.JSONException
import org.json.JSONObject

import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Response

class HelpActivity : BaseActivity() {

    internal var menuBean: LoginBean.Menu?=null
    internal var list: List<GuideBean.Data>? = null
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var adapter: Confirm2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_list)
        Untils.initTitle("货位引导", this)
        menuBean = intent.getParcelableExtra("menubean")
        recyclerView = findViewById(R.id.rv_list)
        getData()


    }

    private fun getData() {
        val jsonObject = JSONObject()
        try {

            jsonObject.put("methodname", "getMenuFieldAndValue")
            jsonObject.put("usercode", BaseActivity.usercode)
            jsonObject.put("menucode", "HWYD")
            jsonObject.put("layout", "1")
            jsonObject.put("condition", "")
            jsonObject.put("barcode", intent.getStringExtra("cwhcode"))
            jsonObject.put("formdata", "")
            jsonObject.put("acccode", BaseActivity.acccode)


        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val obj = jsonObject.toString()
        Log.i("json object", obj)

        val data = Request.getRequestbody(obj)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {

                try {
                    if (response.code() == 200) {
                        val (_, _, data1) = Gson().fromJson(response.body()!!.string(), GuideBean::class.java)
                        adapter = Confirm2Adapter(data1, this@HelpActivity)
                        recyclerView.layoutManager = LinearLayoutManager(this@HelpActivity)
                        recyclerView.addItemDecoration(DividerItemDecoration(this@HelpActivity, DividerItemDecoration.VERTICAL))
                        recyclerView.adapter = adapter


                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    inner class Confirm2Adapter(private val mDatas: List<GuideBean.Data>, private val context: Context) : RecyclerView.Adapter<Confirm2Adapter.VH>() {
        internal var binding: ItemHwydBinding? = null

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Confirm2Adapter.VH {
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_hwyd, viewGroup, false)
            return VH(binding!!.root)



        }


        override fun onBindViewHolder(vh: Confirm2Adapter.VH, i: Int) {
            binding = DataBindingUtil.getBinding(vh.itemView)
            binding!!.tvTag.text = (i + 1).toString() + ""
            val data = mDatas[i]
            binding!!.data = data
            binding!!.executePendingBindings()
            binding!!.rlLayout.setOnClickListener { }


        }

        override fun getItemCount(): Int {
            return mDatas.size
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

}
