package com.dps_kaimur.view.profile_details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dps_kaimur.R
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_profile_details.*
import org.json.JSONArray
import org.json.JSONObject

class ProfileDetails : AppCompatActivity() {

    private lateinit var list:ArrayList<Model>
    private lateinit var myAdapter:MyAdapter


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)
        list= ArrayList()

        list_student.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)






        try {
            Glide.with(baseContext)
                    .load("https://s13.postimg.org/oursvhvon/Untitled.png")
                    .asBitmap()
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into(profile_pic)
        } catch (e: Exception) {
            e.printStackTrace()
        }


        try {
            Glide.with(baseContext)
                    .load("https://image.freepik.com/free-psd/abstract-background-design_1297-73.jpg")
                    .asBitmap()
                    .into(iv_back_profile)
        } catch (e: Exception) {
            e.printStackTrace()
        }



        initJson()
    }

    private fun initJson() {
        AndroidNetworking.get("https://raw.githubusercontent.com/digitalkaimur/DPSApp/master/student_details")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val arr=response.getJSONArray("result")
                        for(i in 0 until arr.length()){
                            val obj=arr.getJSONObject(i)
                            Log.d("TAGS",obj.toString())
                            parseJson(obj)
                        }
                    }

                    override fun onError(error: ANError) {
                        Log.d("TAGS",error.toString())
                    }
                })
    }
    private fun parseJson(data: JSONObject?) {
        if (data != null) {
            val it = data.keys()
            while (it.hasNext()) {
                val key = it.next()
                try {
                    if (data.get(key) is JSONArray) {
                        val arry = data.getJSONArray(key)
                        val size = arry.length()
                        for (i in 0 until size) {
                            parseJson(arry.getJSONObject(i))
                        }
                    } else if (data.get(key) is JSONObject) {
                        parseJson(data.getJSONObject(key))
                    } else {
                        list.add(Model(key+"~"+data.optString(key)))
                        Log.d("TAGS", "" + key + " : " + data.optString(key))
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }

        Log.d("TAGS", "List"+list.toString())

        myAdapter = MyAdapter(list)
        list_student.adapter = myAdapter
    }
}
