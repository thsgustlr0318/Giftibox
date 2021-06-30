package com.triples.giftibox.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triples.giftibox.CouponActivity
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon
import java.util.ArrayList

class RecyclerSearchAdapter(private var couponList: ArrayList<Coupon>): RecyclerView.Adapter<RecyclerSearchAdapter.ListItemViewHolder>(), Filterable  {

    private var searchCouponList: ArrayList<Coupon> = couponList
    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        private var imageviewCardImg: ImageView = itemView!!.findViewById(R.id.imageview_search_img)
        private var textviewCardBrand: TextView = itemView!!.findViewById(R.id.textview_search_brand)
        private var textviewCardMenu: TextView = itemView!!.findViewById(R.id.textview_search_menu)
        private var textviewCardDate: TextView = itemView!!.findViewById(R.id.textview_search_date)

        fun bind(data: Coupon, position: Int){
            Log.d("RecyclerSearchAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("RecyclerSearcgAdapter", data.brand+" "+data.menu+" "+data.date)
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            Glide.with(itemView).load(data.img).centerCrop().into(imageviewCardImg)
            textviewCardBrand.text = data.brand
            textviewCardMenu.text = data.menu
            textviewCardDate.text = data.date
            itemView.setOnClickListener{

                var couponData = Coupon(data.menu, data.img, "barcode", data.brand,
                    data.date, "치킨", "2021.06.15", "소연이가 사줌")
                val nextIntent = Intent(itemView.context, CouponActivity::class.java)
                nextIntent.putExtra("couponData", couponData)
                itemView.context.startActivity(nextIntent)

                //Toast.makeText(itemView.context, textviewCardMenu.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_search, parent, false)
        /*
        view.setOnClickListener{
            Toast.makeText(view.context, "click!", Toast.LENGTH_SHORT).show()
        }

         */
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchCouponList.count()
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        Log.d("RecyclerSearchAdapter", "===== ===== ===== ===== onBindViewHolder ("+getItemCount()+")===== ===== ===== =====")
        holder.bind(searchCouponList[position], position)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    Log.d("RecyclerSearchAdapter", "empty query")
                    searchCouponList = couponList
                } else {
                    val filteredList = ArrayList<Coupon>()
                    //이부분에서 원하는 데이터를 검색할 수 있음
                    for (row in couponList) {
                        if (row.menu!!.lowercase()
                                .contains(charString.lowercase()) || row.brand!!
                                .lowercase().contains(charString.lowercase())
                        ) {
                            filteredList.add(row)
                        }
                    }
                    searchCouponList = filteredList

                }
                val filterResults = FilterResults()
                filterResults.values = searchCouponList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if(filterResults.values == null){
                    Log.d("RecyclerSearchAdapter", "empty array")
                    searchCouponList = ArrayList()
                }else {
                    Log.d("RecyclerSearchAdapter", "not empty query")
                    searchCouponList = filterResults.values as ArrayList<Coupon>
                }
                for(row in searchCouponList){
                    Log.d("RecyclerSearchAdapter", "publishResults/ 메뉴: " + row.menu.toString())
                }
                notifyDataSetChanged()
            }
        }
    }

}