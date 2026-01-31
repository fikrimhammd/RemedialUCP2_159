package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.view.route.DestinasiEditBuku
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditBukuViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoriLibrary
) : ViewModel() {
    var uiState by mutableStateOf(BukuUIState())
        private set

    private val bukuId: Int = checkNotNull(savedStateHandle[DestinasiEditBuku.bukuIdArg])

    init {
        viewModelScope.launch {
            uiState = repository.getBuku(bukuId)
                .filterNotNull()
                .first()
                .toUiStateBuku(true)
        }
    }

    private fun validateInput(event: BukuEvent): Boolean {
        return event.judul.isNotBlank() && 
               event.isbn.isNotBlank() && 
               event.nama_pengarang.isNotBlank() &&
               event.nama_kategori.isNotBlank()
    }

    fun updateUiState(bukuEvent: BukuEvent) {
        uiState = BukuUIState(
            bukuEvent = bukuEvent,
            isEntryValid = validateInput(bukuEvent)
        )
    }

    suspend fun updateBuku() {
        if (validateInput(uiState.bukuEvent)) {
            repository.updateBuku(uiState.bukuEvent.toBuku())
        }
    }
}

fun com.example.ucp2.room.Buku.Buku.toUiStateBuku(isEntryValid: Boolean): BukuUIState = BukuUIState(
    bukuEvent = this.toBukuEvent(),
    isEntryValid = isEntryValid
)
