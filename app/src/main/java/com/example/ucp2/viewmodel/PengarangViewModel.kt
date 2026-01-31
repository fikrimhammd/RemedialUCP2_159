package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.room.Pengarang

class PengarangViewModel(private val repository: RepositoriLibrary) : ViewModel() {
    var uiState by mutableStateOf(PengarangUIState())
        private set

    fun updateUiState(pengarangEvent: PengarangEvent) {
        uiState = PengarangUIState(
            pengarangEvent = pengarangEvent,
            isEntryValid = validateInput(pengarangEvent)
        )
    }

    private fun validateInput(event: PengarangEvent): Boolean {
        return event.nama_pengarang.isNotBlank() && 
               event.email.isNotBlank() && 
               event.bidang_keahlian.isNotBlank()
    }

    suspend fun savePengarang() {
        if (validateInput(uiState.pengarangEvent)) {
            repository.insertPengarang(uiState.pengarangEvent.toPengarang())
        }
    }
}

data class PengarangUIState(
    val pengarangEvent: PengarangEvent = PengarangEvent(),
    val isEntryValid: Boolean = false
)

data class PengarangEvent(
    val id: Int = 0,
    val nama_pengarang: String = "",
    val email: String = "",
    val bidang_keahlian: String = ""
)

fun PengarangEvent.toPengarang(): Pengarang.Pengarang = Pengarang.Pengarang(
    id = id,
    nama_pengarang = nama_pengarang,
    email = email,
    bidang_keahlian = bidang_keahlian
)
