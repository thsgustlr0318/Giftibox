package com.triples.giftibox


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
//import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.triples.giftibox.adapters.RecyclerSearchAdapter
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.data.Home
import com.triples.giftibox.databinding.ActivitySearchBinding
import com.triples.giftibox.decorator.EventDecorator
import com.triples.giftibox.decorator.SaturdayDecorator
import java.util.ArrayList

enum class SEARCH_SORT{
    DATE, NAME
}

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {
    private lateinit var binding: ActivitySearchBinding
    // Coupon List
    val allCouponList: ArrayList<Coupon> = ArrayList()
    val serachCouponList: ArrayList<Coupon> = ArrayList()
    private var searchView: SearchView? = null

    // view
    private lateinit var recyclerviewSearch: RecyclerView
    private lateinit var spinnerSearchSort: Spinner
    private var spinnerSortList = arrayListOf("날짜순","이름순")
    // enum variable
    private var sort: SEARCH_SORT = SEARCH_SORT.DATE
//    private var used: USED = USED.FALSE

    // adapter
    private lateinit var recyclerSearchAdapter: RecyclerSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initBinding()
        initActionBar()
        initSearch()
        initSpinner()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_search_actionbar, menu)       //작성한 메뉴파일 설정
        searchView = findViewById<SearchView>(R.id.searchView_content)
        searchView!!.queryHint = "검색어를 입력하시오"
        searchView!!.setOnQueryTextListener(this)

//        searchView = findItem(R.id.searchView_content) as SearchView
//
//        searchView!!.queryHint = "검색어를 입력하시오32"
//        Log.d("SearchActivity", "4444---------------------------------------")
//
//
//        searchView!!.setOnQueryTextListener(this)
//        Log.d("SearchActivity", "3333---------------------------------------")
//
//        Log.d("SearchActivity", "2222---------------------------------------")

        return true
    }

    private fun initSearch(){
        //Set Coupon List
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "스타벅스", "아메리카노", "2021.06.04"))
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "투썸", "카페라떼", "2021.06.04"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨맛있는치킨", "2021.06.06"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "뿌링클", "2021.06.06"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집", "맛있는치킨", "2021.06.05"))
        allCouponList.add(Coupon("https://pelicana.co.kr/resources/images/menu/best_menu02_200824.jpg", "맛있는치킨집맛있는치킨집", "맛있는치킨맛있는치킨", "2021.06.05"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "뿌링클순살", "2021.06.07"))
        allCouponList.add(Coupon("https://lh3.googleusercontent.com/proxy/w6hPXbO-foQazmouW90IaKppOPFc0G6VfWIdBhJIkDvQLjYoHSxJveRkJwozf-nAcX943nd4g8wXO_BKG04qudOVL76y2iY3gnDcP8V-O6G2ESDFcUM", "BHC", "맛초킹", "2021.06.07"))
        allCouponList.add(Coupon("https://img4.yna.co.kr/etc/inner/KR/2019/06/03/AKR20190603122400009_01_i_P2.jpg", "이디야", "프라푸치노", "2021.06.08"))

        recyclerviewSearch = binding.recyclerviewSearch
        recyclerSearchAdapter = RecyclerSearchAdapter(getCouponList())
        recyclerviewSearch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerviewSearch.adapter = recyclerSearchAdapter
    }

    private fun initSpinner(){
        spinnerSearchSort = binding.spinnerSearchSort
        spinnerSearchSort.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerSortList)
        spinnerSearchSort.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sort = SEARCH_SORT.values()[position]
                var CouponList: ArrayList<Coupon> = getCouponList()
                recyclerSearchAdapter = RecyclerSearchAdapter(CouponList)
                recyclerviewSearch.adapter = recyclerSearchAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


    private fun initActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle("GIFTIBOX")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> { onBackPressed() }
            else -> {return super.onOptionsItemSelected(item)}
        }
        return true
    }

    override fun onBackPressed(){
        finish()
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
    }

    private fun initBinding(){
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getCouponList(): ArrayList<Coupon>{
        if(sort==SEARCH_SORT.DATE) {
            Log.d("SearchActivity", "Sort By Date")
            allCouponList.sortWith(compareBy({ it.date }, { it.date }))
        }
        else if(sort == SEARCH_SORT.NAME) {
            Log.d("SearchActivity", "Sort By Name")
            allCouponList.sortWith(compareBy({ it.menu }, { it.menu }))
        }
        return allCouponList
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        recyclerSearchAdapter.filter.filter(query)
//        recyclerSearchAdapter = RecyclerSearchAdapter(getCouponList())

        recyclerSearchAdapter.notifyDataSetChanged()
        Log.d("SearchActivity", "query: " + query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        recyclerSearchAdapter.filter.filter(newText)
//        recyclerSearchAdapter = RecyclerSearchAdapter(getCouponList())

        recyclerSearchAdapter.notifyDataSetChanged()
        Log.d("SearchActivity", "query: " + newText)
        return true
    }
}