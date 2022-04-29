package com.example.fmtterminology

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [Terminology::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun terminologyDao(): TerminologyDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE?: kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "term3"
                )
                    .createFromAsset("database/term3.db")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}