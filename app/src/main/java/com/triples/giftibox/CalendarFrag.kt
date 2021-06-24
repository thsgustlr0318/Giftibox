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
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.triples.giftibox.adapters.RecyclerCalendarAdapter
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

    // Coupon List
    val allCouponList: ArrayList<Coupon> = ArrayList()
    // CalendarDay Set
    val calendarDaySet: HashSet<CalendarDay> = HashSet()

    private lateinit var recyclerCalendarAdapter: RecyclerCalendarAdapter
    private lateinit var listviewCalendarCoupon: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        // Set Coupon List
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "스타벅스", "아메리카노", "2021.06.04"))
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "투썸", "카페라떼", "2021.06.04"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨맛있는치킨", "2021.06.06"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "뿌링클", "2021.06.06"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집", "맛있는치킨", "2021.06.05"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨", "2021.06.05"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "뿌링클순살", "2021.06.07"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "맛초킹", "2021.06.07"))
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "이디야", "프라푸치노", "2021.06.08"))

        // Set CalendarDay Set
        for(curCoupon in allCouponList){
            addCalendarDaySet(curCoupon.getDate())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.frag_calendar, container, false)
        widget = view.findViewById(R.id.calendarView)
        listviewCalendarCoupon= view.findViewById(R.id.recyclerview_calendar_content)
        widget.setOnDateChangedListener(this);
        if (container != null) {
            widget.addDecorators(SaturdayDecorator(), SundayDecorator(), EventDecorator(Color.RED, calendarDaySet, container.getContext()))
        }

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
        if(calendarDaySet.contains(date)){
            var couponList: ArrayList<Coupon> = ArrayList()
            for(curCoupon in allCouponList){
//                Log.d("CalendarFrag", curCoupon.getDate().toString() + "//"+getDateFromCalendarDay(date))
                if(curCoupon.getDate().equals(getDateFromCalendarDay(date))){
                    couponList.add(curCoupon)
                }
            }
            getRecycler(couponList)
        }else {
            val couponList = arrayListOf<Coupon>()
            getRecycler(couponList)
        }
    }

    private fun getDateFromCalendarDay(calendarDay: CalendarDay): String {
        var sb : StringBuilder = StringBuilder()
        sb.append(calendarDay.year.toString()+".")
        if(calendarDay.month+1 < 10)
            sb.append("0"+(calendarDay.month+1).toString()+".")
        else
            sb.append((calendarDay.month+1).toString()+".")
        if(calendarDay.day < 10)
            sb.append("0"+calendarDay.day.toString())
        else
            sb.append(calendarDay.day.toString())
        return sb.toString()
    }

    private fun addCalendarDaySet(date: String?){
        val arr = date?.split(".")
        calendarDaySet.add(CalendarDay.from(arr!![0].toInt(), arr[1].toInt()-1, arr[2].toInt()))
    }

    private fun getRecycler(couponList: ArrayList<Coupon>){
        recyclerCalendarAdapter = RecyclerCalendarAdapter(couponList)
//        listviewCalendarCoupon.layoutManager= LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listviewCalendarCoupon.layoutManager=GridLayoutManager(activity, 2)
        listviewCalendarCoupon.adapter=recyclerCalendarAdapter
    }
}