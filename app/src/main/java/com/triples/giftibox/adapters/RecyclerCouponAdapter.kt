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

class RecyclerCouponAdapter(private var couponList: MutableList<Coupon>): RecyclerView.Adapter<RecyclerCouponAdapter.ListItemViewHolder>() {

    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        private var imageviewCardImg: ImageView = itemView!!.findViewById(R.id.imageview_card_img)
        private var textviewCardBrand: TextView = itemView!!.findViewById(R.id.textview_card_brand)
        private var textviewCardMenu: TextView = itemView!!.findViewById(R.id.textview_card_menu)
        private var textviewCardDate: TextView = itemView!!.findViewById(R.id.textview_card_date)

        fun bind(data: Coupon, position: Int){
            Log.d("RecyclerCouponAdapter", "===== ===== ===== ===== bind ===== ===== ===== =====")
            Log.d("RecyclerCouponAdapter", data.brand+" "+data.menu+" "+data.date)
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            Glide.with(itemView).load(data.img).centerCrop().into(imageviewCardImg)
            textviewCardBrand.text = data.brand
            textviewCardMenu.text = data.menu
            textviewCardDate.text = data.date
            itemView.setOnClickListener{

                var couponData = Coupon(data.menu, data.img, "barcode", data.brand,
                data.date, "치킨", "2021.06.15", "소연이가 사줌dafsfsfsfsfasdfsafsfasfdasfasfasfsfasfsafsfsfsfsfsfsfsdf")
                val nextIntent = Intent(itemView.context, CouponActivity::class.java)
                nextIntent.putExtra("couponData", couponData)
                itemView.context.startActivity(nextIntent)

                //Toast.makeText(itemView.context, textviewCardMenu.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_home, parent, false)
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
        Log.d("RecyclerCouponAdapter", "===== ===== ===== ===== onBindViewHolder ("+getItemCount()+")===== ===== ===== =====")
        holder.bind(couponList[position], position)
    }

}