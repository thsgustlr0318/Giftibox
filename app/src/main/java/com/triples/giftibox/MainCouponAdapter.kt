package com.triples.giftibox
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainCouponAdapter(private var couponList: MutableList<MainCouponItem>): RecyclerView.Adapter<MainCouponAdapter.ListItemViewHolder>() {

    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var coupon_img: ImageView = itemView!!.findViewById(R.id.coupon_img)
        var coupon_brand: TextView = itemView!!.findViewById(R.id.coupon_brand)
        var coupon_menu: TextView = itemView!!.findViewById(R.id.coupon_menu)
        var coupon_date: TextView = itemView!!.findViewById(R.id.coupon_date)

        fun bind(data: MainCouponItem, position: Int){
            Log.d("ListAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("ListAdapter", data.getCouponBrand()+" "+data.getCouponMenu()+" "+data.getCouponDate())
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            Glide.with(itemView).load(data.getCouponImg()).into(coupon_img)
            coupon_brand.text = data.getCouponBrand()
            coupon_menu.text = data.getCouponMenu()
            coupon_date.text = data.getCouponDate()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return couponList.count()
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== ===== onBindViewHolder ("+getItemCount()+")===== ===== ===== =====")
        holder.bind(couponList[position], position)
    }

}