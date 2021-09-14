package com.make.deve.androidtestdev1.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {

    @SuppressLint("SimpleDateFormat")
    fun createImageFile(context: Context, vin: String, type: PhotoType = PhotoType.NONE): File {
        // Create an image file name
        val r = Random()
        val min = r.nextInt(15376 - 10007 + 1) + 10007
        val max = r.nextInt(99357 - 97261 + 1) + 97261
        val rand = r.nextInt(max - min + 1) + min
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = when (type) {
            PhotoType.INSPECTIONENTRY -> "IE_"
            PhotoType.INSPECTIONCAMP -> "IC_"
            PhotoType.INSPECTIONEXIT -> "IS_"
            PhotoType.NOVELTY -> "N_"
            PhotoType.NONE -> "P_"
        } + vin + "_" + timeStamp + "_" + rand

        val storageDir = File(context.filesDir, "photos/")
        //File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path + "/Travesa")
        storageDir.mkdirs()
        Log.d("CreateImageFile", "Path to folder: " + storageDir.path)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        return image
    }

    fun resizeImage(path: String) {
        val file = File(path)
        Log.d("ImgSize", "" + file.length() / 1024)
        val opt = BitmapFactory.Options()
        opt.inSampleSize = 2
        while (file.length() / 1024 > 2560) {
            val smallBitmap = BitmapFactory.decodeFile(path, opt)
            var stream: FileOutputStream
            try {
                stream = FileOutputStream(file)
                smallBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.flush()
                stream.close()
                smallBitmap.recycle()
            } catch (e: Exception) {
            }
            Log.d("ImgSize", "" + file.length() / 1024)
        }
    }

    enum class PhotoType {
        INSPECTIONENTRY,
        INSPECTIONCAMP,
        INSPECTIONEXIT,
        NOVELTY,

        NONE;
    }
}