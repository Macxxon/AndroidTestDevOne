package com.make.deve.androidtestdev1.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class DataBaseLocal(val context: Context) : IDataBaseLocal {

    private val db = Room.databaseBuilder(
        context,
        PhotosDatabase::class.java, "database-name"
    )
        .build()

    override suspend fun getAll(): List<PhotoDBEntity> {
        return db.getDao().getAll()
    }

    override suspend fun addAll(items: List<PhotoDBEntity>) {
        db.getDao().addAll(items)
    }

    override suspend fun getById(id: Int): PhotoDBEntity? {
        return db.getDao().getById(id)
    }

    override suspend fun updateSingle(photo: PhotoDBEntity) {
        db.getDao().updateSingle(photo)
    }

    override suspend fun markUploaded(id: Int) {
        db.getDao().run {
            val xd = getById(id)
            xd?.let {
                updateSingle(PhotoDBEntity(uid = it.uid, name = it.name, description = it.description, path = it.path))
            }
        }
    }
}