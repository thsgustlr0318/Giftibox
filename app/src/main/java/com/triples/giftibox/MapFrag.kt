package com.triples.giftibox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var recyclerView: RecyclerView? = null
    private lateinit var recyclerMapAdapter : RecyclerMapAdapter

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
        var mapView:MapView = MapView(activity)
        var mapViewContainer:ViewGroup = view.findViewById(R.id.map_view)
        mapViewContainer.addView(mapView)

        var couponList: ArrayList<Coupon> = arrayListOf(
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BHC", "뿌링클", "2021.06.04"),
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "BBQ", "맛초킹", "2021.06.05"),
            Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "교촌", "커리치킨", "2021.06.06")
        )
        recyclerMapAdapter = RecyclerMapAdapter(couponList)

        recyclerView = _binding?.recyclerviewMap as RecyclerView // down castring을 적용
        recyclerView!!.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)
        recyclerView!!.adapter = recyclerMapAdapter;
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