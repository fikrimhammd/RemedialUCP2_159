package com.example.ucp2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.view.route.DestinasiEditPengarang
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditPengarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoriLibrary
) : ViewModel() {
    var uiState by mutableStateOf(PengarangUIState())
        private set

    private val pengarangId: Int = checkNotNull(savedStateHandle[DestinasiEditPengarang.pengarangIdArg])

    init {
        viewModelScope.launch {
            uiState = repository.getPengarang(pengarangId)
                .filterNotNull()
                .first()
                .toUiStatePengarang(true)
        }
    }

    private fun validateInput(event: PengarangEvent): Boolean {
        return event.nama_pengarang.isNotBlank() && 
               event.email.isNotBlank() && 
               event.bidang_keahlian.isNotBlank()
    }

    fun updateUiState(pengarangEvent: PengarangEvent) {
        uiState = PengarangUIState(
            pengarangEvent = pengarangEvent,
            isEntryValid = validateInput(pengarangEvent)
        )
    }

    suspend fun updatePengarang() {
        if (validateInput(uiState.pengarangEvent)) {
            repository.updatePengarang(uiState.pengarangEvent.toPengarang())
        }
    }
}

fun com.example.ucp2.room.Pengarang.Pengarang.toUiStatePengarang(isEntryValid: Boolean): PengarangUIState = PengarangUIState(
    pengarangEvent = this.toPengarangEvent(),
    isEntryValid = isEntryValid
)
