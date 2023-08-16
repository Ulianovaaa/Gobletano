package com.izzzya.gobletano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<ConstraintLayout>(R.id.upMenu).visibility = View.VISIBLE
        requireActivity().findViewById<ImageButton>(R.id.backBtn).setOnClickListener {
            findNavController().popBackStack()
        }

        val soundSwitch = view.findViewById<ImageView>(R.id.switchIV)
        val nameET = view.findViewById<EditText>(R.id.usernameET)
        val okBtn = view.findViewById<MaterialButton>(R.id.okSettingsBtn)

        soundSwitch.setImageResource(when(SharedPrefs.getSound()){
            true -> R.drawable.switchon
            else -> R.drawable.switchoff
        })
        nameET.setText(SharedPrefs.getUn())

        soundSwitch.setOnClickListener {
            if (SharedPrefs.getSound()){
                soundSwitch.setImageResource(R.drawable.switchoff)
                SharedPrefs.setSound(false)
            }else {
                soundSwitch.setImageResource(R.drawable.switchon)
                SharedPrefs.setSound(true)
            }
            Toast.makeText(requireContext(), "restart app to hear changes", Toast.LENGTH_SHORT).show()

        }

        okBtn.setOnClickListener {
            if (nameET.text != null){
                SharedPrefs.setUn(nameET.text.toString())
                Toast.makeText(requireContext(), "saved.", Toast.LENGTH_SHORT).show()
            }
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    companion object {

    }
}