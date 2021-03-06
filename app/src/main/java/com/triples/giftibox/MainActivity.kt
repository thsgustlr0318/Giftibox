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
import android.widget.Toast
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.zxing.LuminanceSource
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.MultiFormatReader
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.BinaryBitmap
import com.triples.giftibox.Util.CouponDB
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.databinding.ActivityMainBinding
import java.io.FileDescriptor
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var catList = listOf<Coupon>()

    private val MAIN_REQUEST_CODE = 100
    private val MAIN_CAMERA_REQUEST_CODE = 0

    private var couponDb : CouponDB? = null

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val parcelFileDescriptor: ParcelFileDescriptor? =
            contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image: Bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor!!.close()
        return image
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

        var bmap: Bitmap = getBitmapFromUri(uri!!)
        val width = bmap.width
        val height = bmap.height
        val pixels = IntArray(width * height)
        bmap.getPixels(pixels, 0, width, 0, 0, width, height)
        var source: LuminanceSource = RGBLuminanceSource(width, height, pixels)
        val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
        var result = MultiFormatReader().decode(binaryBitmap)?.text.toString()
        Log.d("MainActivity", result.toString())

        val addIntent: Intent = Intent(this, CouponAddActivity::class.java)
        addIntent.putExtra("couponData", Coupon(barcode = result, img = uri.toString())) // add image parameter
        startActivity(addIntent)

    }

    var couponList: ArrayList<Coupon> = arrayListOf(
        Coupon("test.png", "BHC", "?????????", "2021.06.04"),
        Coupon("test1.png", "BHC", "?????????", "2021.06.05"),
        Coupon("test2.png", "BHC", "????????????", "2021.06.06")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "Create")
        initBinding()
        initDB()
        getHashKey()
        initActionBar()
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_main_actionbar, menu)		//????????? ???????????? ??????
        return true
    }

    private fun initDB(){
        couponDb = CouponDB.getInstance(this)

        val r = Runnable {


        }

        val thread = Thread(r)
        thread.start()
    }

    private fun initActionBar(){
        setSupportActionBar(binding.toolbar)
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
        binding.fab.setOnClickListener{
            Log.d("MainActivity","Click fab")
            Toast.makeText(this, "click fab", Toast.LENGTH_SHORT).show()
            selectGallery()
        }
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

    private fun selectGallery() {

        var writePermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var readPermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), MAIN_CAMERA_REQUEST_CODE)
        } else {
            getContent.launch("image/*")
        }
    }

}