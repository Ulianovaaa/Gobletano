package com.izzzya.gobletano

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.gobletano.adapter.CardsAdapter
import com.izzzya.gobletano.adapter.LevelsAdapter
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
    private fun timeStrFromLong(ms: Long): String{
        val secs = (ms / 1000) % 60
        val mins = (ms / (1000 * 60) % 60)
        return String.format("%02d:%02d", mins, secs)
    }

    //таймер
    val minute: Long = 60000
    val timer = object: CountDownTimer(minute, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            view!!.findViewById<TextView>(R.id.timerTV).text = timeStrFromLong(millisUntilFinished)
            SharedPrefs.setTime(millisUntilFinished)
            //Toast.makeText(requireContext(), timeStrFromLong(millisUntilFinished), Toast.LENGTH_SHORT).show()
        }

        override fun onFinish() {
            if ((SharedPrefs.getTime() <= 1900)){
                onRanOut()
            }
            findNavController().navigate(R.id.action_global_winLoseFragment)
        }

        fun onRanOut(){
            SharedPrefs.setTime(60001)
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }
        timer.start()
        val level = SharedPrefs.getLv()
        val quantity = when(level){
            1 -> 10
            2 -> 12
            3 -> 14
            4 -> 16
            5 -> 18
            6 -> 20
            else -> 10
        }
        val cards = generateCardList(quantity)
        //Log.i("LIST: ", " level ${level.toString()} / q ${quantity.toString()} / size ${cards.size.toString()} / list ${cards.toString()}")
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.cardsGrid)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = CardsAdapter(requireContext(), cards)
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

    override fun onDestroyView() {
        super.onDestroyView()
        //timer.
        timer.cancel()

    }

    companion object {

    }
}