package com.make.deve.androidtestdev1.ui.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make.deve.androidtestdev1.local.database.IDataBaseLocal
import com.make.deve.androidtestdev1.local.database.PhotoDBEntity
import com.make.deve.androidtestdev1.util.FileUtils
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class CameraViewModel: ViewModel(), KoinComponent {

    val photo: MutableLiveData<String> = MutableLiveData<String>()
    private val repo: IDataBaseLocal by inject()

    fun addPhoto(path: String) {
        viewModelScope.launch {

            FileUtils.resizeImage(path)
            photo.postValue(path)

        }
    }

    fun addData(name: String, description:String){
        viewModelScope.launch {
            repo.addAll(items = arrayListOf(PhotoDBEntity(name=name,description = description,path = photo.value.toString())))
        }
    }

}