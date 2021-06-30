package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.triples.giftibox.adapters.RecyclerHomeAdapter
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.data.Home
import com.triples.giftibox.databinding.FragHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
enum class SORT{
    KIND, DATE, NAME
}
enum class USED{
    FALSE, TRUE
}
class HomeFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragHomeBinding? = null

    // adapter
    private lateinit var recyclerHomeAdapter: RecyclerHomeAdapter

    // view
    private lateinit var tablayoutHomeIsuse: TabLayout
    private lateinit var listviewHomeCoupon: RecyclerView
    private lateinit var spinnerHomeSort: Spinner

    // variables
    private var spinnerSortList = arrayListOf("종류순","날짜순","이름순")

    // enum variable
    private var sort: SORT = SORT.KIND
    private var used: USED = USED.FALSE

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
        Log.d("HomeFrag","create")
        _binding = FragHomeBinding.inflate(inflater, container, false);
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSpinner()
        initTabLayout()

    }

    private fun initRecyclerView(){
        listviewHomeCoupon = _binding!!.recyclerviewHome
        recyclerHomeAdapter = RecyclerHomeAdapter(getCouponList(used))
        listviewHomeCoupon.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listviewHomeCoupon.adapter = recyclerHomeAdapter
    }

    private fun initSpinner(){
        spinnerHomeSort = _binding!!.spinnerHomeSort
        spinnerHomeSort.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerSortList)
        spinnerHomeSort.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sort = SORT.values()[position]
                var HomeList: ArrayList<Home> = getCouponList(used)
                recyclerHomeAdapter = RecyclerHomeAdapter(HomeList)
                listviewHomeCoupon.adapter = recyclerHomeAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun initTabLayout(){
        tablayoutHomeIsuse = _binding!!.tablayoutHomeIsuse
        tablayoutHomeIsuse.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                used = USED.values()[tab.position]
                var HomeList: ArrayList<Home> = getCouponList(used)
                recyclerHomeAdapter = RecyclerHomeAdapter(HomeList)
                listviewHomeCoupon.adapter = recyclerHomeAdapter
            }
        })
    }

    private fun getCouponList(used: USED): ArrayList<Home>{
        var retList : ArrayList<Home> = ArrayList<Home>()
        /* 대충 DB 연결하고 SELECT하는 과정 */
        // 임시 data
        var chickenCouponList: ArrayList<Coupon> = arrayListOf(
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BHC", "뿌링클", "2021.06.04"),
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BBQ", "맛초킹", "2021.06.05"),
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "교촌", "커리치킨", "2021.06.06")
        )
        var cafeCouponList: ArrayList<Coupon> = arrayListOf(
            Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "스타벅스", "아메리카노", "2021.06.04"),
            Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "투썸", "카페라떼", "2021.06.05"),
            Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "이디야", "프라푸치노", "2021.06.06")
        )

        var pizzaCouponList: ArrayList<Coupon> = arrayListOf(
            Coupon("https://cdn.dominos.co.kr/admin/upload/goods/20200311_x8StB1t3.jpg", "스타벅스", "아메리카노", "2021.06.04"),
            Coupon("https://cdn.dominos.co.kr/admin/upload/goods/20200311_x8StB1t3.jpg", "투썸", "카페라떼", "2021.06.05"),
            Coupon("https://cdn.dominos.co.kr/admin/upload/goods/20200311_x8StB1t3.jpg", "이디야", "프라푸치노", "2021.06.06")
        )

        if( used == USED.FALSE){
            if( sort == SORT.KIND ){
                retList = arrayListOf(
                    Home("카페", cafeCouponList),
                    Home("치킨", chickenCouponList),
                    Home("피자", pizzaCouponList)
                )
            }else if ( sort == SORT.DATE ){
                retList = arrayListOf(
                    Home("D-7", cafeCouponList),
                    Home("D-14", chickenCouponList),
                    Home("D-28", pizzaCouponList)
                )
            }else if( sort == SORT.NAME ){
                retList = arrayListOf(
                    Home("ㄱ", cafeCouponList),
                    Home("ㄴ", chickenCouponList),
                    Home("ㄷ", pizzaCouponList)
                )
            }
        }else if( used == USED.TRUE ){
            if( sort == SORT.KIND ){
                retList = arrayListOf(
                    Home("카페", pizzaCouponList),
                    Home("치킨", chickenCouponList),
                    Home("피자", cafeCouponList)
                )
            }else if ( sort == SORT.DATE ){
                retList = arrayListOf(
                    Home("D-7", pizzaCouponList),
                    Home("D-14", chickenCouponList),
                    Home("D-28", cafeCouponList)
                )
            }else if( sort == SORT.NAME ){
                retList = arrayListOf(
                    Home("ㄱ", pizzaCouponList),
                    Home("ㄴ", chickenCouponList),
                    Home("ㄷ", cafeCouponList)
                )
            }
        }


        return retList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}