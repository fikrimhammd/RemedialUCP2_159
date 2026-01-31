package com.example.ucp2.repositori

import com.example.ucp2.room.Buku
import com.example.ucp2.room.Kategori
import com.example.ucp2.room.Pengarang
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriLibrary(
    private val pengarangDao: Pengarang.PengarangDao,
    private val kategoriDao: Kategori.KategoriDao,
    private val bukuDao: Buku.BukuDao
) : RepositoriLibrary {
    override suspend fun insertPengarang(pengarang: Pengarang.Pengarang) = pengarangDao.insert(pengarang)
    override suspend fun updatePengarang(pengarang: Pengarang.Pengarang) = pengarangDao.update(pengarang)
    override suspend fun deletePengarang(pengarang: Pengarang.Pengarang) = pengarangDao.delete(pengarang)
    override fun getAllPengarang(): Flow<List<Pengarang.Pengarang>> = pengarangDao.getAllPengarang()
    override fun getPengarang(id: Int): Flow<Pengarang.Pengarang> = pengarangDao.getPengarang(id)

    override suspend fun insertKategori(kategori: Kategori.Kategori) = kategoriDao.insert(kategori)
    override suspend fun updateKategori(kategori: Kategori.Kategori) = kategoriDao.update(kategori)
    override fun getAllKategori(): Flow<List<Kategori.Kategori>> = kategoriDao.getAllKategori()
    override fun getKategori(id: Int): Flow<Kategori.Kategori> = kategoriDao.getKategori(id)
    override suspend fun softDeleteKategori(id: Int) = kategoriDao.softDelete(id)

    override suspend fun insertBuku(buku: Buku.Buku) = bukuDao.insert(buku)
    override suspend fun updateBuku(buku: Buku.Buku) = bukuDao.update(buku)
    override suspend fun deleteBuku(buku: Buku.Buku) = bukuDao.delete(buku)
    override fun getAllBuku(): Flow<List<Buku.Buku>> = bukuDao.getAllBuku()
    override fun getBuku(id: Int): Flow<Buku.Buku> = bukuDao.getBuku(id)
    override suspend fun softDeleteBuku(id: Int) = bukuDao.softDelete(id)
}
