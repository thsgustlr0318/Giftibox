package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triples.giftibox.Util.RecyclerViewDecoration
import com.triples.giftibox.adapters.RecyclerMapAdapter
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.databinding.FragMapBinding
import net.daum.mf.map.api.MapView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragMapBinding? = null
    private var dataSet: ArrayList<Coupon>? = null
    private var mapView: MapView? = null

    // fragment 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // fragment 생성 후 화면 구성
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragMapBinding.inflate(inflater, container, false);
//        return inflater.inflate(R.layout.frag_map, container, false)
        return _binding!!.root
    }

    // onCreateView에서 뷰 초기화 시 제대로 초기화 안될 수 있음.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MapFrag : ", "onViewCreated")
        mapView = MapView(activity)
        var mapViewContainer:ViewGroup = _binding!!.mapView // ? 널일수 있다. !!은 널이 아니다.
        mapViewContainer.addView(mapView)
        dataSet = arrayListOf();
        addDate()

        val recyclerView = _binding?.recyclerviewMap as RecyclerView // down castring을 적용
        recyclerView!!.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView!!.adapter = RecyclerMapAdapter(dataSet!!, { coupon ->
            _binding!!.textViewInfoTitle.text = "제목 : ${coupon.menu}";
            _binding!!.textViewInfoStore.text = "사용처 : ${coupon.brand}";
            _binding!!.textViewInfoValidity.text = "유효기간 : ${coupon.date}";
        })
    //        recyclerView!!.addItemDecoration(RecyclerViewDecoration(20))
    }

    private fun addDate(){
        dataSet?.addAll(arrayListOf(
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/20200717_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(%ED%95%AB%ED%99%A9%EA%B8%88%EC%98%AC%EB%A6%AC%EB%B8%8C-%EB%8B%A8%ED%92%88%EB%A5%98)_%EB%A0%88%EB%93%9C%EC%B0%A9%EC%B0%A9.png", "BHC", "뿌링클", "2021.06.04"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/20200717_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(%ED%95%AB%ED%99%A9%EA%B8%88%EC%98%AC%EB%A6%AC%EB%B8%8C-%EB%8B%A8%ED%92%88%EB%A5%98)_%EB%B8%94%EB%9E%99%ED%8E%98%ED%8D%BC.png", "BBQ", "맛초킹", "2021.06.05"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/2021413_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(480x480)_%ED%9B%84%EB%9D%BC%EC%9D%B4%EB%93%9C%EB%A5%98_%EC%88%9C%EC%82%B4%ED%81%AC%EB%9E%98%EC%BB%A4_%EC%88%98%EC%A0%95.png", "교촌", "커리치킨", "2021.06.06"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/20200717_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(%ED%95%AB%ED%99%A9%EA%B8%88%EC%98%AC%EB%A6%AC%EB%B8%8C-%EB%8B%A8%ED%92%88%EB%A5%98)_%ED%81%AC%EB%A6%AC%EC%8A%A4%ED%94%BC.png", "교촌", "커리치킨", "2021.06.06"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/202007017_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(%ED%9B%84%EB%9D%BC%EC%9D%B4%EB%93%9C%EB%A5%98)_%ED%95%AB%EC%9C%99.png", "교촌", "커리치킨", "2021.06.06"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/20210413_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(480x480)_%ED%9B%84%EB%9D%BC%EC%9D%B4%EB%93%9C%EB%A5%98_%ED%99%A9%EA%B8%88%EC%98%AC%EB%A6%AC%EB%B8%8C%EC%B9%98%ED%82%A8%EC%86%8D%EC%95%88%EC%8B%AC_%EC%88%98%EC%A0%95.png", "교촌", "커리치킨", "2021.06.06"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/20210413_BBQ_%EC%95%B1_%EC%8D%B8%EB%84%A4%EC%9D%BC(480x480)_%EC%96%91%EB%85%90%EB%A5%98_%ED%99%A9%EA%B8%88%EC%98%AC%EB%A6%AC%EB%B8%8C%EC%B9%98%ED%82%A8%EB%B0%98%EB%B0%98_%EC%88%98%EC%A0%95.png", "교촌", "커리치킨", "2021.06.06"),
            Coupon("https://img.bbq.co.kr:449/uploads/bbq_d/thumbnail/BBQ_앱_썸네일(480x480)_후라이드류_황금올리브치킨_수정_out.png", "교촌", "커리치킨", "2021.06.06")
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MapFrag : ", "DestroyView")
        _binding = null
    }


    override fun onStop() {
        super.onStop()
        Log.d("MapFrag : ", "onStop")
        // navigation bar에서 동일 tab 클릭 시 map view가 이미 생성되어 있어, 에러 발생함. 그래서 stop했을 때 제거해야한다.
        _binding?.mapView?.removeView(mapView)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MapFrag : ", "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MapFrag : ", "onStart")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}