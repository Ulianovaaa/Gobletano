package com.izzzya.gobletano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.izzzya.gobletano.adapter.Record
import com.izzzya.gobletano.adapter.StatsDataSource
import com.izzzya.gobletano.adapter.statsRVAdapter


class StatisticsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ConstraintLayout>(R.id.upMenu).visibility = View.VISIBLE
        requireActivity().findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.statsRV)
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        val dataSet: List<Record> = StatsDataSource.statsList.sortedBy { it.time }
        recyclerView.adapter = statsRVAdapter(
            requireContext(),
            dataSet
        )
        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    companion object {

    }
}