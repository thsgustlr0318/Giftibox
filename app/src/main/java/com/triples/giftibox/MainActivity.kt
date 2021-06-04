package com.triples.giftibox

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.triples.giftibox.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        ininNavigation()
        getHashKey()
    }

    private fun ininNavigation(){
        NavigationUI.setupWithNavController(binding.bottomNav, findNavController(R.id.navi_host))
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