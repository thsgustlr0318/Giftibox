package com.triples.giftibox

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.triples.giftibox.databinding.ActivityAlarmBinding
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initActionBar()
        initAlarm()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_alarm_actionbar, menu)       //작성한 메뉴파일 설정
        return true
    }

    private fun initAlarm(){

    }

    private fun initActionBar(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setTitle("GIFTIBOX")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.button_action_search -> { return true }
            android.R.id.home -> { return true }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }

    private fun initBinding(){
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}