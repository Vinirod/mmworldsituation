package br.com.module.situationworld.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.module.situationworld.room.dao.WorldDao
import br.com.module.situationworld.room.entity.World

@Database(entities = arrayOf(World::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun worldDao(): WorldDao

    companion object {

        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, "MMWorldSituation.db")
                .build()
    }
}