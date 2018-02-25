package com.dps_kaimur.view.support

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dps_kaimur.R
import com.dps_kaimur.view.home.NavigationActivity
import kotlinx.android.synthetic.main.activity_support.*

class SupportActivitys : AppCompatActivity() {

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_support)


        support_btn_click_back.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        btn_submit_support.setOnClickListener {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
    }
}
