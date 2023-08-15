package com.izzzya.gobletano.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.gobletano.R

class statsRVAdapter(private val context: Context?,
                     private val dataset: List<Record>
): RecyclerView.Adapter<statsRVAdapter.statsViewHolder>() {

    class statsViewHolder(view: View): RecyclerView.ViewHolder(view!!){
        val usernameTV = view.findViewById<TextView>(R.id.unTV)
        val timeTV = view.findViewById<TextView>(R.id.timeTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): statsViewHolder {
        val mLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.stats_item, parent, false)
        return statsViewHolder(mLayout)    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: statsViewHolder, position: Int) {
        val rec = dataset[position]
        holder.usernameTV.text = rec.name
        holder.timeTV.text = timeStrFromLong(rec.time)
    }

    private fun timeStrFromLong(ms: Long): String{
        val secs = (ms / 1000) % 60
        val mins = (ms / (1000 * 60) % 60)
//        val hrs = ((ms / 1000 * 60* 60) % 24)
        return String.format("%02d:%02d", mins, secs)
    }
}