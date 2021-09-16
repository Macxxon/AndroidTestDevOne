package com.make.deve.androidtestdev1.ui.camera

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.make.deve.androidtestdev1.R
import com.make.deve.androidtestdev1.databinding.FragmentPreviewCameraBinding

class CameraPreviewFragment: Fragment() {

    lateinit var binding: FragmentPreviewCameraBinding
    private val vm: CameraViewModel by navGraphViewModels(R.id.nav_fragment_camera)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreviewCameraBinding.inflate(inflater, container, false)

        vm.photo.observe(viewLifecycleOwner){
            it?.let {
                val savedUri = Uri.parse(it)
                binding.photo.setImageURI(savedUri)
            }
        }

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.nav_fragment_add)
        }

        return binding.root
    }

}