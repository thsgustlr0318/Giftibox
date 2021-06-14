package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.triples.giftibox.databinding.ActivityCouponBinding

class CouponActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("CouponActivity", "Create")
        initBinding()
    }

    private fun initBinding(){
        binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}