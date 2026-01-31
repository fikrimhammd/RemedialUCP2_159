package com.example.ucp2.room

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class Kategori {
    @Entity(tableName = "tblKategori")
    data class Kategori(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val nama_kategori: String,
        val parent_id: Int? = null, // For hierarchical structure
        val is_deleted: Boolean = false // Soft Delete
    )

    @Dao
    interface KategoriDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(kategori: Kategori)

        @Update
        suspend fun update(kategori: Kategori)

        @Query("SELECT * FROM tblKategori WHERE is_deleted = 0 ORDER BY nama_kategori ASC")
        fun getAllKategori(): Flow<List<Kategori>>

        @Query("SELECT * FROM tblKategori WHERE id = :id AND is_deleted = 0")
        fun getKategori(id: Int): Flow<Kategori>

        @Query("UPDATE tblKategori SET is_deleted = 1 WHERE id = :id")
        suspend fun softDelete(id: Int)
        
        // Recursive search placeholder - usually handled in repository or with simplified query
        @Query("SELECT * FROM tblKategori WHERE parent_id = :parentId AND is_deleted = 0")
        fun getSubCategories(parentId: Int): Flow<List<Kategori>>
    }
}
