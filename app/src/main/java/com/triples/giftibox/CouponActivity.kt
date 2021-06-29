package com.triples.giftibox

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triples.giftibox.data.CouponParcel
import com.triples.giftibox.databinding.ActivityCouponBinding

class CouponActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCouponBinding
    private lateinit var coupon: CouponParcel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("CouponActivity", "Create")
        initBinding()
        initData()
        initActionBar()
        initListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_coupon_actionbar, menu)
        return true
    }

    private fun initActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.outline_delete_24)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun initListener(){
        binding.imageviewCouponImg.setOnClickListener{
            val nextIntent = Intent(this, ImageActivity::class.java)
            nextIntent.putExtra("couponImage", coupon.img)
            this.startActivity(nextIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_action_write -> { return writeCoupon() }
            android.R.id.home -> { return deleteCoupon() }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    private fun deleteCoupon(): Boolean{
        val builder = AlertDialog.Builder(this)
        builder.setTitle("쿠폰 삭제")
        builder.setMessage("쿠폰을 정말 삭제하시겠습니까?")
        builder.setPositiveButton("확인") { _, _ ->

        }
        builder.setNegativeButton("취소") { _, _ ->

        }

        builder.show()
        return true
    }

    private fun writeCoupon(): Boolean{
        Log.d("CouponActivity", "수정시작")
        val nextIntent = Intent(this, ChangeActivity::class.java)
        var coupon = intent.getParcelableExtra<CouponParcel>("couponData")
        nextIntent.putExtra("couponData", coupon)
        this.startActivity(nextIntent)
        return true
    }

    private fun initBinding(){
        binding = ActivityCouponBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initData(){
        if( intent.hasExtra("couponData") ){
            coupon = intent.getParcelableExtra<CouponParcel>("couponData")!!
            binding.textviewCouponMenu.text = coupon.menu
            binding.textviewCouponBrand.text = coupon.brand
            binding.textviewCouponCategory.text = coupon.category
            binding.textviewCouponDate.text = coupon.date
            binding.textviewCouponRegister.text = coupon.register
            binding.textviewCouponMemo.text = coupon.memo
            Glide.with(this).load(coupon.img).centerCrop().into(binding.imageviewCouponImg)
            Glide.with(this).load("https://internationalbarcodes.com/wp-content/uploads/sites/95/2013/11/SSCC-Pallet-Barcode.jpg").centerCrop().into(binding.imageviewCouponBarcode)
        }
    }

}