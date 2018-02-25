package com.dps_kaimur.view.attendance

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.dps_kaimur.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import kotlinx.android.synthetic.main.parent_activity_calender_attendance.*
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AttendanceCalender : AppCompatActivity(), OnRangeSelectedListener {

    /*

    {
  "success": true,
  "attlist": [
    {
      "status": "1",
      "atten_date": "2018-01-10"
    },
    {
      "status": "2",
      "atten_date": "2018-01-12"
    },
    {
      "status": "1",
      "atten_date": "2018-01-20"
    },
    {
      "status": "1",
      "atten_date": "2018-01-13"
    },
    {
      "status": "3",
      "atten_date": "2018-01-14"
    },
    {
      "status": "3",
      "atten_date": "2018-01-26"
    },
    {
      "status": "4",
      "atten_date": "2018-01-22"
    },
    {
      "status": "4",
      "atten_date": "2018-03-2"
    }
  ]
}


*/

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


    internal var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private var materialCalendarView: MaterialCalendarView? = null
    private var list: MutableList<Model_Attendance>? = null
    private var dateList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        setContentView(R.layout.parent_activity_calender_attendance)

        materialCalendarView = findViewById<View>(R.id.calendarView) as MaterialCalendarView
        materialCalendarView!!.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE
        materialCalendarView!!.setOnRangeSelectedListener(this)


        att_btn_click_back.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }


        loadJSON()




    }


    private fun loadJSON() {

        AndroidNetworking.get("https://raw.githubusercontent.com/karunkumarpune/Calender/master/calender_select.json")
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("Attandance Date", " Response " + response)

                        try {
                            val jsonArray = response.getJSONArray("attlist")
                            list = ArrayList()
                            dateList = ArrayList()
                            for (i in 0 until jsonArray.length()) {
                                val object1 = jsonArray.getJSONObject(i)
                                val dateString = object1.getString("atten_date")
                                list!!.add(Model_Attendance(dateString, object1.getString("status")))
                                dateList!!.add(dateString)
                                materialCalendarView!!.selectRange(CalendarDay.from(sdf.parse(dateString)), CalendarDay.from(sdf.parse(dateString)))
                            }

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        } catch (e: ParseException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(anError: ANError) {

                    }
                })

    }

    override fun onRangeSelected(widget: MaterialCalendarView, dates: List<CalendarDay>) {
        for (i in dates.indices) {
            //    widget.setDateSelected(dates.get(i), true);
            Log.d("Date OnRange", dates[i].date.toString())
            val indexOfDate = dateList!!.indexOf(sdf.format(dates[i].date))
            Log.d("DateIndex", indexOfDate.toString())
            if (indexOfDate >= 0) {
                if (list!![indexOfDate].status == "1") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else if (list!![indexOfDate].status == "3") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else if (list!![indexOfDate].status == "4") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                }
            } else {
                widget.setDateSelected(dates[i], false)
            }

        }
    }

}


