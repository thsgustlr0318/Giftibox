package com.triples.giftibox

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var couponList: ArrayList<Coupon> = arrayListOf(
        Coupon("test.png", "BHC", "뿌링클", "2021.06.04"),
        Coupon("test1.png", "BHC", "맛초킹", "2021.06.05"),
        Coupon("test2.png", "BHC", "커리치킨", "2021.06.06")
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
        getHashKey()
        initActionBar()
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_main_actionbar, menu)		//작성한 메뉴파일 설정
        return true
    }

    private fun initActionBar(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.outline_notifications_24)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle("GIFTIBOX")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_action_search -> { return viewSearch() }
            android.R.id.home -> { return viewAlarm() }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    private fun viewSearch(): Boolean{
        val nextIntent = Intent(this, SearchActivity::class.java)
        this.startActivity(nextIntent)
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
        return true
    }

    private fun viewAlarm(): Boolean{
        val nextIntent = Intent(this, AlarmActivity::class.java)
        this.startActivity(nextIntent)
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
        return true
    }

    private fun initNavigation(){
        NavigationUI.setupWithNavController(binding.bottomNav, findNavController(R.id.navi_host))
        binding.bottomNav.background = null
        binding.bottomNav.menu.getItem(2).isEnabled = false
        binding.fab.setColorFilter(Color.parseColor("#ffffffff"))
    }

    private fun initBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.lifecycleOwner = this
    }

    private fun getHashKey(){
        var packageInfo: PackageInfo? = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (e:PackageManager.NameNotFoundException) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        if (packageInfo != null) {
            for (signature : Signature in packageInfo.signatures) {
                try {
                    var md:MessageDigest = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                } catch (e: NoSuchAlgorithmException) {
                    Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
                }
            }
        }
    }
}