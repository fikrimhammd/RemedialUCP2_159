package com.example.ucp2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.repositori.RepositoriLibrary
import com.example.ucp2.room.Buku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeBukuViewModel(private val repository: RepositoriLibrary) : ViewModel() {
    val homeBukuUiState: StateFlow<HomeBukuUiState> =
        repository.getAllBuku().map { HomeBukuUiState(listBuku = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeBukuUiState()
            )
}

data class HomeBukuUiState(
    val listBuku: List<Buku.Buku> = listOf()
)
