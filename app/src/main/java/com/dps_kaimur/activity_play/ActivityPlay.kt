package com.dps_kaimur.activity_play

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dps_kaimur.R
import com.dps_kaimur.utils.CustomProgressBar
import kotlinx.android.synthetic.main.activity_play.*

class ActivityPlay : AppCompatActivity() {
    private lateinit var pb: CustomProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        setContentView(R.layout.activity_play)


        pb = CustomProgressBar(this);
        pb.setCancelable(false)
        pb.show()


        Handler().postDelayed({
            pb.dismiss()
        }, 2000)



        activity_btn_click_back.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }
    }
}
