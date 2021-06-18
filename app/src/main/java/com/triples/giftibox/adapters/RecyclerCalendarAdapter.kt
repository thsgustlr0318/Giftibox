package com.triples.giftibox.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triples.giftibox.CouponActivity
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.data.Home
import com.triples.giftibox.adapters.RecyclerCouponAdapter
import com.triples.giftibox.data.CouponParcel

class RecyclerCalendarAdapter(private var couponList: MutableList<Coupon>): RecyclerView.Adapter<RecyclerCalendarAdapter.ListItemViewHolder>() {

    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        private var imageviewCardImg: ImageView = itemView!!.findViewById(R.id.imageview_calendar_img)
        private var textviewCardBrand: TextView = itemView!!.findViewById(R.id.textview_calendar_brand)
        private var textviewCardMenu: TextView = itemView!!.findViewById(R.id.textview_calendar_menu)
        private var textviewCardDate: TextView = itemView!!.findViewById(R.id.textview_calendar_date)

        fun bind(data: Coupon, position: Int){
            Log.d("RecyclerCalendarAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("RecyclerCalendarAdapter", data.getBrand()+" "+data.getMenu()+" "+data.getDate())
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            Glide.with(itemView).load(data.getImg()).centerCrop().into(imageviewCardImg)
            textviewCardBrand.text = data.getBrand()
            textviewCardMenu.text = data.getMenu()
            textviewCardDate.text = data.getDate()
            itemView.setOnClickListener{

                var couponData = CouponParcel(data.getMenu(), data.getImg(), "barcode", data.getBrand(),
                    data.getDate(), "치킨", "2021.06.15", "소연이가 사줌")
                val nextIntent = Intent(itemView.context, CouponActivity::class.java)
                nextIntent.putExtra("couponData", couponData)
                itemView.context.startActivity(nextIntent)

                //Toast.makeText(itemView.context, textviewCardMenu.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_calendar, parent, false)
        /*
        view.setOnClickListener{
            Toast.makeText(view.context, "click!", Toast.LENGTH_SHORT).show()
        }

         */
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return couponList.count()
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        Log.d("RecyclerCalendarAdapter", "===== ===== ===== ===== onBindViewHolder ("+getItemCount()+")===== ===== ===== =====")
        holder.bind(couponList[position], position)
    }

}