package com.make.deve.androidtestdev1.local.database

import androidx.room.*

@Entity(tableName = "photos")
data class PhotoDBEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "path") val path: String
)

@Dao
interface PhotoDBDAO {
    @Query("SELECT * FROM photos")
    suspend fun getAll(): List<PhotoDBEntity>

    @Query("SELECT * from photos where uid = :id")
    suspend fun getById(id: Int): PhotoDBEntity?

    @Insert
    suspend fun addAll(items: List<PhotoDBEntity>)

    @Update
    suspend fun updateSingle(photo: PhotoDBEntity)
}

@Database(entities = [PhotoDBEntity::class], version = 1, exportSchema = true)
abstract class PhotosDatabase : RoomDatabase() {
    abstract fun getDao(): PhotoDBDAO
}