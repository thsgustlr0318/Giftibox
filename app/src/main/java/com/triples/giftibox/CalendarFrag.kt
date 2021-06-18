package com.triples.giftibox

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.triples.giftibox.adapters.RecyclerCalendarAdapter
import com.triples.giftibox.adapters.RecyclerHomeAdapter
import com.triples.giftibox.adapters.RecyclerMapAdapter
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.decorator.EventDecorator
import com.triples.giftibox.decorator.SaturdayDecorator
import com.triples.giftibox.decorator.SundayDecorator
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalendarFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalendarFrag : Fragment(), OnDateSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var widget: MaterialCalendarView
    lateinit var textView: TextView

    val calendarDayList: ArrayList<CalendarDay> = ArrayList()

    private lateinit var recyclerCalendarAdapter: RecyclerCalendarAdapter
    private lateinit var listviewCalendarCoupon: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_calendar, container, false)
        widget = view.findViewById(R.id.calendarView)
//        textView = view.findViewById(R.id.textView)
        listviewCalendarCoupon= view.findViewById(R.id.recyclerview_calendar_content)



        calendarDayList.add(CalendarDay.today())
        calendarDayList.add(CalendarDay.from(2021,6-1,12))
        calendarDayList.add(CalendarDay.from(2021,6-1,13))
        calendarDayList.add(CalendarDay.from(2021,0,13))

        widget.setOnDateChangedListener(this);
        widget.addDecorators(SaturdayDecorator(), SundayDecorator(), EventDecorator(Color.RED, calendarDayList))

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalendarFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalendarFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDateSelected(
        widget: MaterialCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {
        Log.d("CalendarFrag", "날짜 선택")
        if(calendarDayList.contains(date)){
//            textView.text = "깊티 정보"
            var couponList: ArrayList<Coupon> = arrayListOf(
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "스타벅스", "아메리카노", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "투썸", "카페라떼", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "맛있는치킨집", "맛있는치킨", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "맛있는치킨집맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨맛있는치킨", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "BHC", "뿌링클", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "BHC", "뿌링클순살", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "BHC", "맛초킹", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString()),
                Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "이디야", "프라푸치노", date.year.toString()+"."+(date.month+1).toString()+"."+date.day.toString())
            )
            getRecycler(couponList)
//            recyclerCalendarAdapter = RecyclerCalendarAdapter(couponList)
//            listviewCalendarCoupon.layoutManager= LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//            listviewCalendarCoupon.adapter=recyclerCalendarAdapter
        }else {
//            textView.text = ""
            val couponList = arrayListOf<Coupon>()
            getRecycler(couponList)
        }

    }

    private fun getRecycler(couponList: ArrayList<Coupon>){
        recyclerCalendarAdapter = RecyclerCalendarAdapter(couponList)
//        listviewCalendarCoupon.layoutManager= LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listviewCalendarCoupon.layoutManager=GridLayoutManager(activity, 2)
        listviewCalendarCoupon.adapter=recyclerCalendarAdapter
    }
}