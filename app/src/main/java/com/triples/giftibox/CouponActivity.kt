package com.triples.giftibox

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triples.giftibox.data.CouponParcel
import com.triples.giftibox.databinding.ActivityCouponBinding

class CouponActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCouponBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("CouponActivity", "Create")
        initBinding()
        initData()
    }

    private fun initBinding(){
        binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initData(){
        if( intent.hasExtra("couponData") ){
            var coupon = intent.getParcelableExtra<CouponParcel>("couponData")
            binding.textviewCouponMenu.text = coupon!!.menu
            binding.textviewCouponBrand.text = coupon!!.brand
            binding.textviewCouponCategory.text = coupon!!.category
            binding.textviewCouponDate.text = coupon!!.date
            binding.textviewCouponRegister.text = coupon!!.register
            binding.textviewCouponMemo.text = coupon!!.memo
            Glide.with(this).load(coupon!!.img).centerCrop().into(binding.imageviewCouponImg)
            Glide.with(this).load("https://internationalbarcodes.com/wp-content/uploads/sites/95/2013/11/SSCC-Pallet-Barcode.jpg").centerCrop().into(binding.imageviewCouponBarcode)

        }
    }

}