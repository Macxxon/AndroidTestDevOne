package com.make.deve.androidtestdev1.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.make.deve.androidtestdev1.local.database.IDataBaseLocal
import com.make.deve.androidtestdev1.local.database.PhotoDBEntity
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ListViewModel: ViewModel(), KoinComponent {

    val repo: IDataBaseLocal by inject()
    private val photoEntityList: MutableLiveData<List<PhotoDBEntity>> = MutableLiveData()
    val loading = MutableLiveData(false)

    fun getAllItems() {
        viewModelScope.launch {
            loading.value = true

            val rem = repo.getAll()
            photoEntityList.value = rem

            loading.value = false
        }
    }
}