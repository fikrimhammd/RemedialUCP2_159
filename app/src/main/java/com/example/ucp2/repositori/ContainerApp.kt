package com.example.ucp2.repositori

import android.app.Application
import android.content.Context
import com.example.ucp2.room.AppDatabase

interface ContainerApp {
    val repositoriLibrary: RepositoriLibrary
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriLibrary: RepositoriLibrary by lazy {
        val database = AppDatabase.AppDatabase.getDatabase(context)
        OfflineRepositoriLibrary(
            pengarangDao = database.pengarangDao(),
            kategoriDao = database.kategoriDao(),
            bukuDao = database.bukuDao()
        )
    }
}

class PerpustakaanApp : Application() {
    lateinit var container: ContainerApp
    
    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}