package com.izzzya.gobletano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<ConstraintLayout>(R.id.upMenu).visibility = View.GONE

        view.findViewById<ImageButton>(R.id.playBtn).setOnClickListener{
            findNavController().navigate(R.id.action_global_gameFragment)
        }
        view.findViewById<ImageButton>(R.id.settingsBtn).setOnClickListener{
            findNavController().navigate(R.id.action_global_settingsFragment)
        }

        view.findViewById<ImageButton>(R.id.statsBtn).setOnClickListener{
            findNavController().navigate(R.id.action_global_statisticsFragment)
        }
        view.findViewById<ImageButton>(R.id.guideBtn).setOnClickListener{
            findNavController().navigate(R.id.action_global_guideFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    companion object {

    }
}