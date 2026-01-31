package com.example.ucp2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class AppDatabase {

    @Database(
        entities = [
            Pengarang.Pengarang::class,
            Kategori.Kategori::class,
            Buku.Buku::class
        ],
        version = 1,
        exportSchema = false
    )
    abstract class AppDatabase : RoomDatabase() {

        abstract fun pengarangDao(): Pengarang.PengarangDao
        abstract fun kategoriDao(): Kategori.KategoriDao
        abstract fun bukuDao(): Buku.BukuDao

        companion object {
            @Volatile
            private var instance: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase {
                return instance ?: synchronized(this) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "library_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { instance = it }
                }
            }
        }
    }
}
