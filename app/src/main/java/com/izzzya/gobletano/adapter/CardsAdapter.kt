package com.izzzya.gobletano.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.gobletano.Card
import com.izzzya.gobletano.R
import com.izzzya.gobletano.SharedPrefs

class CardsAdapter(private val context: Context?,
                   private val dataset: List<Card>
): RecyclerView.Adapter<CardsAdapter.cardsViewHolder>() {

    class cardsViewHolder(view: View): RecyclerView.ViewHolder(view!!){
        val IV = view.findViewById<ImageView>(R.id.cardIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardsViewHolder {
        val mLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return cardsViewHolder(mLayout)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: cardsViewHolder, position: Int) {
        val card = dataset[position]
        holder.IV.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                holder.IV.setImageResource(card.image)
                if(card.image == R.drawable.card_front_cup){
                    Toast.makeText(context, "You win!", Toast.LENGTH_SHORT).show()
                    val lv = SharedPrefs.getLvs()+1
                    SharedPrefs.setLvs(lv)
                    p0!!.findNavController().navigate(R.id.action_global_winLoseFragment)
                }
            }
        })
    }
}