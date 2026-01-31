package com.example.ucp2.room

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

class Pengarang {
    @Entity(tableName = "tblPengarang")
    data class Pengarang(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val nama_pengarang: String,
        val email: String,
        val bidang_keahlian: String
    )

    @Dao
    interface PengarangDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(pengarang: Pengarang)

        @Query("SELECT * FROM tblPengarang ORDER BY nama_pengarang ASC")
        fun getAllPengarang(): Flow<List<Pengarang>>

        @Query("SELECT * FROM tblPengarang WHERE id = :id")
        fun getPengarang(id: Int): Flow<Pengarang>

        @androidx.room.Update
        suspend fun update(pengarang: Pengarang)

        @androidx.room.Delete
        suspend fun delete(pengarang: Pengarang)
    }
}
