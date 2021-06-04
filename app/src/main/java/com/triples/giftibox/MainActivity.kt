package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.triples.giftibox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var CouponList: ArrayList<MainCouponItem> = arrayListOf(
        MainCouponItem("test.png", "BHC", "뿌링클", "2021.06.04"),
        MainCouponItem("test1.png", "BHC", "맛초킹", "2021.06.05"),
        MainCouponItem("test2.png", "BHC", "커리치킨", "2021.06.06")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "Create")
        /*
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.homeFrag,
            HomeFrag()
        )
        transaction.commit()
        intent.putExtra("CouponList", CouponList)
        */
        initBinding()
        initNavigation()


    }

    private fun initNavigation(){
        NavigationUI.setupWithNavController(binding.bottomNav, findNavController(R.id.navi_host))
    }

    private fun initBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.lifecycleOwner = this
    }
}