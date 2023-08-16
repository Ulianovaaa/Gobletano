package com.izzzya.gobletano

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.izzzya.gobletano.adapter.Record
import com.izzzya.gobletano.adapter.StatsDataSource


class WinLoseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private fun timeStrFromLong(ms: Long): String{
        val secs = (ms / 1000) % 60
        val mins = (ms / (1000 * 60) % 60)
        return String.format("%02d:%02d", mins, secs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }

        val win = SharedPrefs.getTime()<60000
        val header = view.findViewById<TextView>(R.id.headerTV)
        val nextBtn = view.findViewById<ImageButton>(R.id.nextBtn)
        val nextBtnTitle = view.findViewById<TextView>(R.id.nextBtnTitle)
        val menuBtn = view.findViewById<ImageButton>(R.id.menuBtn)
        val highScore = StatsDataSource.statsList.filter { it.name == SharedPrefs.getUn() }
        if (win){
            //win case
            if (highScore.isEmpty()){
                StatsDataSource.statsList.add(Record(
                    SharedPrefs.getUn(),
                    (60000-SharedPrefs.getTime())
                ))
                Toast.makeText(requireContext(), "New high score! Check statistics!", Toast.LENGTH_SHORT).show()
            }else if (highScore[0].time > 60000-SharedPrefs.getTime()){
                StatsDataSource.statsList.remove(highScore[0])
                StatsDataSource.statsList.add(Record(
                    SharedPrefs.getUn(),
                    (60000-SharedPrefs.getTime())
                ))
                Toast.makeText(requireContext(), "New high score! Check statistics!", Toast.LENGTH_SHORT).show()
            }
            header.text = "Level completed!"
            nextBtnTitle.text = "next level"
            nextBtn.setOnClickListener {
                findNavController().navigate(R.id.action_global_levelsFragment)
            }


        }else{
            //lose case
            header.text = "Time's up! You lost \n :("
            nextBtnTitle.text = "play again"
            nextBtn.setOnClickListener {
                findNavController().navigate(R.id.action_global_gameFragment)
            }
        }
        menuBtn.setOnClickListener {
            findNavController().navigate(R.id.action_global_menuFragment)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_win_lose, container, false)
    }

    companion object {

    }
}