package com.triples.giftibox.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.data.Home
import com.triples.giftibox.adapters.RecyclerCouponAdapter

class RecyclerHomeAdapter(private var homeList: MutableList<Home>): RecyclerView.Adapter<RecyclerHomeAdapter.ListItemViewHolder>() {

    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        private var textviewRecyclerSubject: TextView = itemView!!.findViewById(R.id.textview_recycler_subject)
        private var recyclerviewRecyclerContent: RecyclerView = itemView!!.findViewById(R.id.recyclerview_recycler_content)

        fun bind(data: Home, position: Int){
            Log.d("RecyclerHomeAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)

            textviewRecyclerSubject.text = data.getSubject()

            var recyclerCouponAdapter =  RecyclerCouponAdapter(data.getCouponList())
            recyclerviewRecyclerContent.layoutManager = LinearLayoutManager(itemView.context,  RecyclerView.HORIZONTAL, false)
            recyclerviewRecyclerContent.adapter = recyclerCouponAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHomeAdapter.ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_home, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeList.count()
    }

    override fun onBindViewHolder(holder: RecyclerHomeAdapter.ListItemViewHolder, position: Int) {
        Log.d("RecyclerHomeAdapter", "===== ===== ===== ===== onBindViewHolder ("+getItemCount()+")===== ===== ===== =====")
        holder.bind(homeList[position], position)
    }
}