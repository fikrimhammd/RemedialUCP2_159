package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.room.Buku

class BukuViewModel(private val repository: RepositoriLibrary) : ViewModel() {
    var uiState by mutableStateOf(BukuUIState())
        private set

    fun updateUiState(bukuEvent: BukuEvent) {
        uiState = BukuUIState(
            bukuEvent = bukuEvent,
            isEntryValid = validateInput(bukuEvent)
        )
    }

    private fun validateInput(event: BukuEvent): Boolean {
        return event.judul.isNotBlank() && 
               event.isbn.isNotBlank() && 
               event.nama_pengarang.isNotBlank() &&
               event.nama_kategori.isNotBlank()
    }

    suspend fun saveBuku() {
        if (validateInput(uiState.bukuEvent)) {
            repository.insertBuku(uiState.bukuEvent.toBuku())
        }
    }
}

data class BukuUIState(
    val bukuEvent: BukuEvent = BukuEvent(),
    val isEntryValid: Boolean = false
)

data class BukuEvent(
    val id: Int = 0,
    val judul: String = "",
    val isbn: String = "",
    val nama_pengarang: String = "",
    val nama_kategori: String = "",
    val status_pinjam: String = "Tersedia"
)

fun BukuEvent.toBuku(): Buku.Buku = Buku.Buku(
    id = id,
    judul = judul,
    isbn = isbn,
    nama_pengarang = nama_pengarang,
    nama_kategori = nama_kategori,
    status_pinjam = status_pinjam
)
