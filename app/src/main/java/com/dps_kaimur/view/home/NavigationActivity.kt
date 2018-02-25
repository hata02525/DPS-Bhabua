package com.dps_kaimur.view.home
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dps_kaimur.R
import com.dps_kaimur.activity_play.ActivityPlay
import com.dps_kaimur.controller.application.AppController
import com.dps_kaimur.controller.sharedpreferences.LoginPrefences
import com.dps_kaimur.news.NewsActivity
import com.dps_kaimur.privecy.PrivecyPolicyActivity
import com.dps_kaimur.restservices.APIService
import com.dps_kaimur.restservices.ApiUtils
import com.dps_kaimur.utils.ConnectivityReceiver
import com.dps_kaimur.utils.CustomProgressBar
import com.dps_kaimur.utils.Utils
import com.dps_kaimur.view.attendance.AttendanceCalender
import com.dps_kaimur.view.login.ChangePasswordActivity
import com.dps_kaimur.view.login.EditProfileActivity
import com.dps_kaimur.view.login.LoginActivity
import com.dps_kaimur.view.notification.NotificationActivity
import com.dps_kaimur.view.profile_details.ProfileDetails
import com.dps_kaimur.view.statisticReport.StatisticReportActivity
import com.dps_kaimur.view.support.SupportActivitys
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle


class NavigationActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showSnack(isConnected)
    }

    private var session_id: String = ""
    private var mViewHolder: ViewHolder? = null

    private var APIService: APIService? = null
    private lateinit var pb: CustomProgressBar
    private var loginPreference: LoginPrefences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.activity_navigation)

        session_id = intent.getStringExtra("session")
        loginPreference = LoginPrefences.getInstance();

        initView()
        APIService = ApiUtils.apiService
        pb = CustomProgressBar(this);
        pb.setCancelable(false)
        pb.show()

        mViewHolder = ViewHolder()

        handleDrawer()

        if (ConnectivityReceiver.isConnected) {
            try {
                // jsonParse()
            } catch (e: Exception) {
            }

            // showSnack(true);
        } else {
            try {
                // jsonParse()
            } catch (e: Exception) {
            }
        }

        Handler().postDelayed({
            pb.dismiss()
        }, 1000)
    }


    /*init View */

    private fun initView() {

        val btn_menu_option_1: ImageButton = findViewById(R.id.btn_activity)
        val btn_menu_option_2: ImageButton = findViewById(R.id.btn_application)
        val btn_menu_option_3: ImageButton = findViewById(R.id.btn_notification)
        val btn_menu_option_4: ImageButton = findViewById(R.id.btn_news)
        val btn_menu_option_5: ImageButton = findViewById(R.id.btn_diary)
        val btn_menu_option_6: ImageButton = findViewById(R.id.btn_attendance)

        btn_menu_option_1.setOnClickListener {
            startActivity(Intent(this@NavigationActivity, ActivityPlay::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_1))
            )
        }

        btn_menu_option_2.setOnClickListener {
          /*  startActivity(Intent(this@NavigationActivity, EditProfileActivity::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_2))
            )*/

            Utils.showToast(this, "Coming soon !!!", Color.YELLOW)
        }

        btn_menu_option_3.setOnClickListener {
            startActivity(Intent(this@NavigationActivity, NotificationActivity::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_3))
            )
        }

        btn_menu_option_4.setOnClickListener {
            startActivity(Intent(this@NavigationActivity, NewsActivity::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_4))
            )
        }

        btn_menu_option_5.setOnClickListener {
          /*  startActivity(Intent(this@NavigationActivity, EditProfileActivity::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_5))
            )*/

            Utils.showToast(this, "Coming soon !!!", Color.YELLOW)
        }

        btn_menu_option_6.setOnClickListener {
            startActivity(Intent(this@NavigationActivity, AttendanceCalender::class.java)
                    .putExtra("option", resources.getString(R.string.send_intent_6))
            )
        }

    }


    private fun showSnack(isConnected: Boolean) {
        if (isConnected)
            Utils.showToast(this, "Good! Connected to Internet", Color.GREEN)
        else
            Utils.showToast(this, "Sorry!No internet available", Color.WHITE)
    }

    //----------------------------------------------------Open Menu Intent
    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(this,
                mViewHolder!!.mDuoDrawerLayout,
                mViewHolder!!.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)



        try {
            Glide.with(baseContext)
                    .load("https://s13.postimg.org/oursvhvon/Untitled.png")
                    .asBitmap()
                    .error(R.drawable.progress_animation)
                    .placeholder(R.drawable.progress_animation)
                    .into( mViewHolder!!.profile_pic)
        } catch (e: Exception) {
            e.printStackTrace()
        }



            //--------------------------------btn_Edit--------------------


            mViewHolder!!.profile_pic.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, EditProfileActivity::class.java))
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }


            mViewHolder!!.txt_profile_name.text = resources.getString(R.string.app_namess)



            //--------------------------------btn_1--------------------




            mViewHolder!!.txt_option_1.text = resources.getString(R.string.title_option_1)
            mViewHolder!!.txt_option_1.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, ProfileDetails::class.java)
                        //.putExtra("option", 1)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_2--------------------


            mViewHolder!!.txt_option_2.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, AttendanceCalender::class.java)
                        .putExtra("option", 2)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_3--------------------


            mViewHolder!!.txt_option_3.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, StatisticReportActivity::class.java)
                        .putExtra("option", 3)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_4--------------------



            mViewHolder!!.noti_relative_click.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, NotificationActivity::class.java)
                        .putExtra("option", 4)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_5--------------------


            mViewHolder!!.txt_option_5.setOnClickListener { _ ->
              /*  startActivity(Intent(this@NavigationActivity, ChangePasswordActivity::class.java)
                        .putExtra("option", 5)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()*/
                Utils.showToast(this, "Coming soon !!!", Color.YELLOW)
            }

            //--------------------------------btn_6--------------------


            mViewHolder!!.txt_option_6.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, ChangePasswordActivity::class.java)
                        .putExtra("option", 6)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }


            //--------------------------------btn_7--------------------


            mViewHolder!!.txt_option_7.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, PrivecyPolicyActivity::class.java)
                        .putExtra("option", 4)
                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_8--------------------


            mViewHolder!!.txt_option_8.setOnClickListener { _ ->
                startActivity(Intent(this@NavigationActivity, SupportActivitys::class.java)

                )
                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }

            //--------------------------------btn_9--------------------






            mViewHolder!!.txt_option_9.setOnClickListener { _ ->


                AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("")
                        .setMessage(resources.getString(R.string.txt_close_app))
                        .setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ ->
                            startActivity(Intent(this@NavigationActivity, LoginActivity::class.java))
                            loginPreference!!.removeData(loginPreference!!.getLoginPreferences(this@NavigationActivity));
                            finish()
                        }
                        .setNegativeButton(resources.getString(R.string.txt_No), null)
                        .show()


                mViewHolder!!.mDuoDrawerLayout.closeDrawer()
            }




        mViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

        mViewHolder!!.mToolbar.setNavigationIcon(R.drawable.ic_lines_menu)
        mViewHolder!!.mToolbar.hideOverflowMenu()
        mViewHolder!!.mToolbar.showContextMenu()

    }

    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout = findViewById(R.id.drawer)
        val mToolbar: Toolbar = findViewById(R.id.toolbar)

        val profile_pic: ImageView = findViewById(R.id.profile_pic)
        val txt_profile_name: TextView = findViewById(R.id.txt_profile_name)
        val txt_noti_count: TextView = findViewById(R.id.txt_noti_count)
        val noti_relative_click: LinearLayout = findViewById(R.id.noti_relative_click)




        val txt_option_1: TextView = findViewById(R.id.txt_option_1)
        val txt_option_2: TextView = findViewById(R.id.txt_option_2)
        val txt_option_3: TextView = findViewById(R.id.txt_option_3)
        val txt_option_4: TextView = findViewById(R.id.txt_option_4)
        val txt_option_5: TextView = findViewById(R.id.txt_option_5)
        val txt_option_6: TextView = findViewById(R.id.txt_option_6)
        val txt_option_7: TextView = findViewById(R.id.txt_option_7)
        val txt_option_8: TextView = findViewById(R.id.txt_option_8)
        val txt_option_9: TextView = findViewById(R.id.txt_option_9)


    }


    override fun onBackPressed() {

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        /* AlertDialog.Builder(this)
                 .setIcon(android.R.drawable.ic_dialog_alert)
                 .setTitle("")
                 .setMessage(resources.getString(R.string.txt_close_app))
                 .setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ -> callFinish() }
                 .setNegativeButton(resources.getString(R.string.txt_No), null)
                 .show()*/

        AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setMessage(resources.getString(R.string.txt_close_app))
                .setPositiveButton(resources.getString(R.string.txt_yes)) { _, _ ->
                    startActivity(Intent(this@NavigationActivity, LoginActivity::class.java))
                    loginPreference!!.removeData(loginPreference!!.getLoginPreferences(this@NavigationActivity));
                    finish()
                }
                .setNegativeButton(resources.getString(R.string.txt_No), null)
                .show()
    }


    override fun onStop() {
        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }

        AppController.getInstance().setConnectivityListener(this)
    }

    //--------------------------------------------API---Work----------



    //------------------------------------0peration --------------------our------------

    private fun callFinish() {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask()
        } else {
            this.moveTaskToBack(true);

        }
    }
}