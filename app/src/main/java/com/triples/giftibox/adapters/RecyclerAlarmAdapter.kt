package com.triples.giftibox.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triples.giftibox.CouponActivity
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.data.CouponParcel

class RecyclerAlarmAdapter(private var couponList: MutableList<Coupon>): RecyclerView.Adapter<RecyclerAlarmAdapter.ListItemViewHolder>() {
    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        private var imageviewCardImg: ImageView = itemView!!.findViewById(R.id.imageview_card_img)
        private var textviewCardBrand: TextView = itemView!!.findViewById(R.id.textview_card_brand)
        private var textviewCardMenu: TextView = itemView!!.findViewById(R.id.textview_card_menu)
        private var textviewCardDate: TextView = itemView!!.findViewById(R.id.textview_card_date)

        fun bind(data: Coupon, position: Int){
            Log.d("RecyclerCouponAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("RecyclerCouponAdapter", data.getBrand()+" "+data.getMenu()+" "+data.getDate())
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            Glide.with(itemView).load(data.getImg()).centerCrop().into(imageviewCardImg)
            textviewCardBrand.text = data.getBrand()
            textviewCardMenu.text = data.getMenu()
            textviewCardDate.text = data.getDate()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_home, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return couponList.count()
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(couponList[position], position)
    }
}