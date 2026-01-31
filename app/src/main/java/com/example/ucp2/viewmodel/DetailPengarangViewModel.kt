package com.example.ucp2.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.room.Pengarang
import com.example.ucp2.view.route.DestinasiDetailPengarang
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailPengarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: RepositoriLibrary
) : ViewModel() {
    private val pengarangId: Int = checkNotNull(savedStateHandle[DestinasiDetailPengarang.pengarangIdArg])

    val detailPengarangUiState: StateFlow<DetailPengarangUiState> =
        repository.getPengarang(pengarangId)
            .filterNotNull()
            .map { DetailPengarangUiState(pengarangEvent = it.toPengarangEvent()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = DetailPengarangUiState()
            )

    suspend fun deletePengarang() {
        repository.deletePengarang(detailPengarangUiState.value.pengarangEvent.toPengarang())
    }
}

data class DetailPengarangUiState(
    val pengarangEvent: PengarangEvent = PengarangEvent()
)

fun Pengarang.Pengarang.toPengarangEvent(): PengarangEvent = PengarangEvent(
    id = id,
    nama_pengarang = nama_pengarang,
    email = email,
    bidang_keahlian = bidang_keahlian
)
