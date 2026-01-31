package com.example.ucp2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class Buku {
    @Entity(tableName = "tblBuku")
    data class Buku(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val judul: String,
        val isbn: String,
        val nama_pengarang: String, // Simplified as string for this UCP task if not using full join
        val nama_kategori: String,
        val status_pinjam: String = "Tersedia", // Tersedia, Dipinjam
        val is_deleted: Boolean = false
    )

    @Dao
    interface BukuDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(buku: Buku)

        @Update
        suspend fun update(buku: Buku)

        @Delete
        suspend fun delete(buku: Buku)

        @Query("SELECT * FROM tblBuku WHERE is_deleted = 0 ORDER BY judul ASC")
        fun getAllBuku(): Flow<List<Buku>>

        @Query("SELECT * FROM tblBuku WHERE id = :id AND is_deleted = 0")
        fun getBuku(id: Int): Flow<Buku>

        @Query("SELECT * FROM tblBuku WHERE nama_kategori = :kategori AND is_deleted = 0")
        fun getBukuByCategory(kategori: String): Flow<List<Buku>>

        @Query("UPDATE tblBuku SET is_deleted = 1 WHERE id = :id")
        suspend fun softDelete(id: Int)
    }
}
