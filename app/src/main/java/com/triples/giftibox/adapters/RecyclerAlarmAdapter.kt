package com.triples.giftibox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triples.giftibox.R
import com.triples.giftibox.data.Alarm

class RecyclerAlarmAdapter(private var alarmlist: MutableList<Alarm>): RecyclerView.Adapter<RecyclerAlarmAdapter.ListItemViewHolder>() {
    inner class ListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        //private var imageviewCardImg: ImageView = itemView!!.findViewById(R.id.imageview_card_img)
        private var textViewAlarmTitle: TextView = itemView!!.findViewById(R.id.textview_alarm_title)
        private var textviewAlarmContent: TextView = itemView!!.findViewById(R.id.textview_alarm_content)

        fun bind(data: Alarm, position: Int){
            //coupon_img.setImageResource(R.drawable.ic_launcher_foreground)
            //Glide.with(itemView).load(data.getImg()).centerCrop().into(imageviewCardImg)
            textViewAlarmTitle.text = data.getTitle()
            textviewAlarmContent.text = data.getContent()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_alarm, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return alarmlist.count()
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(alarmlist[position], position)
    }
}