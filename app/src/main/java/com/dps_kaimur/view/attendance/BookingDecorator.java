package com.dps_kaimur.view.attendance;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.dps_kaimur.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;


 class BookingDecorator implements DayViewDecorator {
    private Context context;
    private String status;
    private HashSet<CalendarDay> mCalendarDayCollection;
    BookingDecorator(Context context, String status, Collection<CalendarDay> dates) {
        this.context = context;
        this.status = status;
        mCalendarDayCollection = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return mCalendarDayCollection.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
     //   view.addSpan(new ForegroundColorSpan(mColor));
        //view.addSpan(new BackgroundColorSpan(Color.BLUE));
        if (status.equals("1"))
            view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_green));
       else if (status.equals("2"))
            view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_red));
       else if (status.equals("3"))
         view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_blue));
        else
            view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_yellow));

    }
}
