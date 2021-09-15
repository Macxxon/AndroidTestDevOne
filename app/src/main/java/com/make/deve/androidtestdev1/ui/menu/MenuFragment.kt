package com.make.deve.androidtestdev1.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.make.deve.androidtestdev1.R
import com.make.deve.androidtestdev1.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)


        binding.camera.setOnClickListener {
            findNavController().navigate(R.id.nav_fragment_camera)
        }

        binding.buttonList.setOnClickListener {
            findNavController().navigate(R.id.nav_fragment_list)
        }

        return binding.root
    }

}