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
import com.make.deve.androidtestdev1.databinding.FragmentFormularioEditBinding
import com.make.deve.androidtestdev1.local.database.DataBaseLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FormularioEditFragment : Fragment(){

    lateinit var binding: FragmentFormularioEditBinding
    private val vm: CameraViewModel by navGraphViewModels(R.id.nav_fragment_camera)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormularioEditBinding.inflate(inflater, container, false)


        vm.photo.observe(viewLifecycleOwner){
            it?.let {
                val savedUri = Uri.parse(it)
                binding.photo.setImageURI(savedUri)
            }
        }



        binding.addButton.setOnClickListener {
            vm.addData(name = binding.name.text.toString(),description = binding.description.text.toString())
            findNavController().navigate(R.id.nav_menu)
        }


        return binding.root
    }

}