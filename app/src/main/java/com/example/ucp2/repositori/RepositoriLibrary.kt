package com.example.ucp2.repositori

import com.example.ucp2.room.Buku
import com.example.ucp2.room.Kategori
import com.example.ucp2.room.Pengarang
import kotlinx.coroutines.flow.Flow

interface RepositoriLibrary {

    suspend fun insertPengarang(pengarang: Pengarang.Pengarang)
    suspend fun updatePengarang(pengarang: Pengarang.Pengarang)
    suspend fun deletePengarang(pengarang: Pengarang.Pengarang)
    fun getAllPengarang(): Flow<List<Pengarang.Pengarang>>
    fun getPengarang(id: Int): Flow<Pengarang.Pengarang>


    suspend fun insertKategori(kategori: Kategori.Kategori)
    suspend fun updateKategori(kategori: Kategori.Kategori)
    fun getAllKategori(): Flow<List<Kategori.Kategori>>
    fun getKategori(id: Int): Flow<Kategori.Kategori>
    suspend fun softDeleteKategori(id: Int)


    suspend fun insertBuku(buku: Buku.Buku)
    suspend fun updateBuku(buku: Buku.Buku)
    suspend fun deleteBuku(buku: Buku.Buku)
    fun getAllBuku(): Flow<List<Buku.Buku>>
    fun getBuku(id: Int): Flow<Buku.Buku>
    suspend fun softDeleteBuku(id: Int)
}
