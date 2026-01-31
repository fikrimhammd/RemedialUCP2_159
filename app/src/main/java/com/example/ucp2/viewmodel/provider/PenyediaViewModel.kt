package com.example.ucp2.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.repositori.PerpustakaanApp
import com.example.ucp2.viewmodel.BukuViewModel
import com.example.ucp2.viewmodel.DetailBukuViewModel
import com.example.ucp2.viewmodel.DetailPengarangViewModel
import com.example.ucp2.viewmodel.EditBukuViewModel
import com.example.ucp2.viewmodel.EditPengarangViewModel
import com.example.ucp2.viewmodel.HomeBukuViewModel
import com.example.ucp2.viewmodel.HomePengarangViewModel
import com.example.ucp2.viewmodel.PengarangViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            PengarangViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            BukuViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            HomePengarangViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            HomeBukuViewModel(aplikasiPerpustakaan().container.repositoriLibrary)
        }
        initializer {
            DetailBukuViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            EditBukuViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            DetailPengarangViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
        initializer {
            EditPengarangViewModel(
                this.createSavedStateHandle(),
                aplikasiPerpustakaan().container.repositoriLibrary
            )
        }
    }
}

fun CreationExtras.aplikasiPerpustakaan(): PerpustakaanApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PerpustakaanApp)