package com.triples.giftibox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.triples.giftibox.R
import com.triples.giftibox.data.Coupon

class RecyclerMapAdapter(private val dataSet: ArrayList<Coupon>) : RecyclerView.Adapter<RecyclerMapAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            imageView = view.findViewById(R.id.imageViewMap)
        }
    }

    // 뷰 홀더 새로 생성할 때 호출, view를 생성하지만 내용을 채우지는 않음.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_map, viewGroup, false)
        return ViewHolder(view)
    }

    // 뷰 홀더와 데이터 연결
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        viewHolder.textView.text = dataSet[position]
//        viewHolder.imageView.setImageResource(R.drawable.ic_launcher_foreground)
    }

    // 데이터 사이즈
    override fun getItemCount(): Int {
        return dataSet.size
    }

}
