package com.triples.giftibox

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.triples.giftibox.data.CouponParcel
import com.triples.giftibox.databinding.ActivityChangeBinding
import java.lang.StringBuilder
import java.util.*


class CouponAddActivity : AppCompatActivity(){
    private lateinit var binding: ActivityChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("CouponAddActivity", "Create")

        initBinding()
        initData()
        initActionBar()
        initClickListener()
    }

    private fun initClickListener() {
        binding.edittextChangeDate.setOnClickListener {
            showDatePicker()
        }
        binding.edittextChangeCategory.setOnClickListener {
            showCategoryList()
        }
        binding.buttonChangeCancel.setOnClickListener{
            onBackPressed()
        }
    }

    private fun showCategoryList() {
        val items = arrayOf("치킨", "카페", "편의점", "아이스크림/빙수", "버거/피자", "기타")
        var selectedItem: String? = null
        val builder = AlertDialog.Builder(this)
            .setTitle("Category")
            .setSingleChoiceItems(items, -1) { dialog, which ->
                selectedItem = items[which]
            }
            .setPositiveButton("OK") { dialog, which ->
                Log.d("ChangeActivity", "${selectedItem.toString()} is Selected")
                binding.edittextChangeCategory.text = Editable.Factory.getInstance().newEditable("${selectedItem.toString()}")
//                toast("${selectedItem.toString()} is Selected")
            }
            .show()
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, y, m, d->
            binding.edittextChangeDate.text = Editable.Factory.getInstance().newEditable(getDateFromYMD(y, m, d))
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
    }

    private fun getDateFromYMD(y: Int, m: Int, d: Int) = with(StringBuilder()){
        append(y)
        append(".")
        if( m+1 < 10 )
            append("0")
        append(m+1)
        append(".")
        if( d < 10 )
            append("0")
        append(d)
        toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_change_actionbar, menu)
        supportActionBar!!.setTitle("GIFTIBOX")
        return true
    }

    private fun initActionBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.button_action_write -> { writeCoupon() }
            android.R.id.home -> { onBackPressed() }
            else -> { super.onOptionsItemSelected(item) }
        }
        return true
    }

    override fun onBackPressed(){
        super.onBackPressed()
    }

    private fun writeCoupon(): Boolean{
        return true
    }

    private fun initBinding(){
        binding = ActivityChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initData(){
        Log.d("CouponAddActivity", intent.data.toString())
        if( intent.hasExtra("couponData") ){
            var coupon = intent.getParcelableExtra<CouponParcel>("couponData")
            Log.d("CouponAddActivity", coupon.toString())
            binding.edittextChangeName.text = Editable.Factory.getInstance().newEditable(coupon?.menu)
            binding.edittextChangeBrand.text = Editable.Factory.getInstance().newEditable(coupon?.brand)
            binding.edittextChangeCategory.text = Editable.Factory.getInstance().newEditable(coupon?.category)
            binding.edittextChangeDate.text = Editable.Factory.getInstance().newEditable(coupon?.date)
            binding.edittextChangeMemo.text = Editable.Factory.getInstance().newEditable(coupon?.memo)
            binding.edittextChangeBarcode.text = Editable.Factory.getInstance().newEditable(coupon?.barcode)
            Glide.with(this).load(coupon?.img).centerCrop().into(binding.imageviewChangeImg)
        }
    }
}