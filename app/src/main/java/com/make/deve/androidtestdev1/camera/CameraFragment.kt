package com.make.deve.androidtestdev1.camera

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.make.deve.androidtestdev1.R
import com.make.deve.androidtestdev1.Utils.FileUtils.createImageFile
import com.make.deve.androidtestdev1.databinding.FragmentCameraBinding
import java.io.File
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment:Fragment() {

    lateinit var binding: FragmentCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private var mCurrentPhotoPath = ""
    private var imageCapture: ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCameraBinding.inflate(inflater, container, false)

        // hide the action bar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        // Check camera permissions if all permission granted
        // start camera else ask for the permission

        startCamera()

        // set on click listener for the button of capture photo
        // it calls a method which is implemented below


        binding.cameraCaptureButton!!.setOnClickListener {
            takePhoto()
        }
        cameraExecutor = Executors.newSingleThreadExecutor()

        return binding.root
    }

    private fun takePhoto() {
/*
        //other

        /* val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)*/
        // Create the File where the photo should go


        //new
        val imageCapture = imageCapture ?: return

        // Create time-stamped output file to hold the image
        var photoFile: File? = null
        try {
            photoFile = FileUtils.createImageFile(
                requireContext(),
                vm.selectedVin.value ?: "",
                FileUtils.PhotoType.INSPECTIONENTRY


            )//createImageFile()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            Toast.makeText(
                requireContext(),
                "Ocurrió un error creando el archivo",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile!!).build()

        // Set up image capture listener,
        // which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    Log.d(TAG, "captureImage: Content URI is ")

                    mCurrentPhotoPath = photoFile!!.absolutePath

                    // set the saved uri to the image view


                    val msg = "Photo capture succeeded: $savedUri"
                    Log.d(TAG, msg)
                }
            })*/

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {

            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }



            imageCapture = ImageCapture.Builder().build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(context))
    }
}