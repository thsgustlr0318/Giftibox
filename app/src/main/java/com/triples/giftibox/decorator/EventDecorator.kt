package com.triples.giftibox.decorator

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.style.*
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.triples.giftibox.CalendarFrag
import com.triples.giftibox.R


class EventDecorator(private val color: Int, dates: Collection<CalendarDay>, context: Context) :
    DayViewDecorator {
    private val dates: HashSet<CalendarDay>
    val drawable = context.getDrawable(R.drawable.ic_baseline_check_box_outline_blank_24)
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
//        view.addSpan(ForegroundColorSpan(Color.GREEN))
        view.addSpan( StyleSpan(Typeface.BOLD));
//        view.addSpan(RelativeSizeSpan(1.4f));
        if (drawable != null) {
            view.setBackgroundDrawable(drawable)
        }
    }

    init {
        this.dates = HashSet(dates)
    }
}