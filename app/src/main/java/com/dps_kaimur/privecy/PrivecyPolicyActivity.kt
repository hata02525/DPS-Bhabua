package com.dps_kaimur.privecy

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import com.dps_kaimur.R
import com.dps_kaimur.utils.CustomProgressBar
import kotlinx.android.synthetic.main.activity_privecy_policy.*



class PrivecyPolicyActivity : AppCompatActivity() {
    private lateinit var pb: CustomProgressBar
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_privecy_policy)



        pb = CustomProgressBar(this);
        pb.setCancelable(false)
        pb.show()


        Handler().postDelayed({
            pb.dismiss()
        }, 4000)
        webview.loadUrl("http://www.dpssrinagar.com/school/privacy-policy/");

        priv_btn_click_back.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }





    }
}
