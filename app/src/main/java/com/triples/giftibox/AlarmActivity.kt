package com.triples.giftibox

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triples.giftibox.adapters.RecyclerAlarmAdapter
import com.triples.giftibox.adapters.RecyclerHomeAdapter
import com.triples.giftibox.data.Alarm
import com.triples.giftibox.data.Home
import com.triples.giftibox.databinding.ActivityAlarmBinding
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlarmBinding

    // adapter
    private lateinit var recyclerAlarmAdapter: RecyclerAlarmAdapter

    // view
    private lateinit var recyclerviewAlarm: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initActionBar()
        initAlarm()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_alarm_actionbar, menu)
        return true
    }

    private fun initAlarm(){
        recyclerviewAlarm = binding!!.recyclerviewAlarm
        recyclerAlarmAdapter = RecyclerAlarmAdapter(getAlarmList())
        recyclerviewAlarm.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerviewAlarm.adapter = recyclerAlarmAdapter
    }

    private fun getAlarmList(): ArrayList<Alarm>{
        var retList : ArrayList<Alarm> = ArrayList<Alarm>()

        retList = arrayListOf(
            Alarm("카페베네 카페라떼","7일 남았다"),
            Alarm("싸피레이스","열흘 남았다")
        )

        return retList
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
            android.R.id.home -> { onBackPressed() }
            else -> {return super.onOptionsItemSelected(item)}
        }
        return true
    }

    override fun onBackPressed(){
        super.onBackPressed()
        if( isFinishing ) {
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
        }
    }

    private fun initBinding(){
        binding = ActivityAlarmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}