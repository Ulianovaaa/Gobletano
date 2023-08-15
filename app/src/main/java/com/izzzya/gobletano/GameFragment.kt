package com.izzzya.gobletano

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.random.Random
import kotlin.random.nextInt

data class Card(
    val cup: Boolean,
    val image: Int //res id
)

class GameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val level = SharedPrefs.getLv()
        val quantity = when(level){
            1 -> 10
            2 -> 11
            3 -> 12
            4 -> 13
            5 -> 14
            6 -> 15
            else -> 10
        }
        val cards = generateCardList(quantity)
        //Log.i("LIST: ", " level ${level.toString()} / q ${quantity.toString()} / size ${cards.size.toString()} / list ${cards.toString()}")
    }

    private fun generateCardList(q: Int): List<Card>{
        val winIndex = Random.nextInt(0..q)
        val listCards: MutableList<Card> = mutableListOf()
        for (i in 0 until q){
            listCards.add(
                Card(
                    (i == winIndex),
                    when(i == winIndex){
                        true -> R.drawable.card_front_cup
                        else -> R.drawable.card_front_nocup
                    }
                ),)
        }

        return listCards
    }

    companion object {

    }
}