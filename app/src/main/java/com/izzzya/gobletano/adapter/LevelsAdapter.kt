package com.izzzya.gobletano.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.gobletano.R
import com.izzzya.gobletano.SharedPrefs

class LevelsAdapter(private val context: Context?,
                    private val dataset: List<Int> = listOf(1, 2, 3, 4, 5, 6)
): RecyclerView.Adapter<LevelsAdapter.LevelsViewHolder>() {

    class LevelsViewHolder(view: View): RecyclerView.ViewHolder(view!!){
        val number = view.findViewById<TextView>(R.id.numTV)
        val IV = view.findViewById<ImageView>(R.id.levelButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_item, parent, false)
        return LevelsViewHolder(view)

    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: LevelsViewHolder, position: Int) {
        val level = dataset[position]
        //unlock levels
        val clickable = position<=SharedPrefs.getLvs()
        holder.number.text = level.toString()
        if (clickable){
            holder.IV.backgroundTintMode = null
            holder.number.setOnClickListener(object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    SharedPrefs.setLv(level)
                    p0!!.findNavController().navigate(R.id.action_global_gameFragment)

                }
            })
        }
    }
}