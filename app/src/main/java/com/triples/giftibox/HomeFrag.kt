package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainCouponAdapter: MainCouponAdapter
    private var spinnerCouponItem = arrayListOf("종류순","날짜순","이름순")

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
        val view = inflater.inflate(R.layout.frag_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // spinner coupon sort
        var couponSortSpinner: Spinner = requireView().findViewById(R.id.spinner_coupon)

        var mainCouponSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerCouponItem)
        //mainCouponSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        couponSortSpinner.adapter = mainCouponSpinnerAdapter
        // view coupon list
        //var CouponList: ArrayList<MainCouponItem> = requireActivity().intent!!.extras!!.get("CouponList") as ArrayList<MainCouponItem>

        var CouponList: ArrayList<MainCouponItem> = arrayListOf(
            MainCouponItem("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BHC", "뿌링클", "2021.06.04"),
            MainCouponItem("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BBQ", "맛초킹", "2021.06.05"),
            MainCouponItem("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "교촌", "커리치킨", "2021.06.06")
        )

        Log.e("FirstFragment", "Data List: ${CouponList}")

        mainCouponAdapter = MainCouponAdapter(CouponList)
        var listView = requireView().findViewById(R.id.listView) as RecyclerView
        listView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        listView.adapter = mainCouponAdapter

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