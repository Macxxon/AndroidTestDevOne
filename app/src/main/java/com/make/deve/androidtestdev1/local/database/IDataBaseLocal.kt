package com.make.deve.androidtestdev1.local.database

interface IDataBaseLocal {
    suspend fun getAll(): List<PhotoDBEntity>
    suspend fun getById(id: Int): PhotoDBEntity?

    suspend fun addAll(items: List<PhotoDBEntity>)

    suspend fun updateSingle(photo: PhotoDBEntity)
    suspend fun markUploaded(id: Int)
}