package com.triples.giftibox.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triples.giftibox.MapFrag
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon
import com.triples.giftibox.databinding.CardviewMapBinding
import com.triples.giftibox.databinding.FragMapBinding
import kotlin.math.log

class RecyclerMapAdapter(private val dataSet: ArrayList<Coupon>, val itemClick: (Coupon)-> Unit) : RecyclerView.Adapter<RecyclerMapAdapter.ViewHolder>() {

    // 뷰 홀더 새로 생성할 때 호출, view를 생성하지만 내용을 채우지는 않음. 어떤 xml을 이용하여 만들지 설정
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = CardviewMapBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view.root, itemClick)
    }

    // 뷰 홀더와 데이터 연결
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding(dataSet[position])
    }

    // 데이터 사이즈
    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(view: View, itemClick: (Coupon) -> Unit) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageView_map)

        fun binding(coupon: Coupon) {
            Glide.with(imageView.context)
                .load(coupon.img)
                .into(imageView)
            imageView.setOnClickListener({
                itemClick(coupon)
            })
        }
    }
}
