package com.triples.giftibox.decorator

import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan


class EventDecorator(private val color: Int, dates: Collection<CalendarDay>, textView: TextView) :
    DayViewDecorator {
    private val dates: HashSet<CalendarDay>
    private var textView: TextView? = null
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, color))
    }

    init {
        this.dates = HashSet(dates)
        this.textView = textView
    }
}