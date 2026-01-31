package com.example.ucp2.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.repositori.PerpustakaanApp
import com.example.ucp2.viewmodel.BukuViewModel
import com.example.ucp2.viewmodel.PengarangViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            PengarangViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            BukuViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)