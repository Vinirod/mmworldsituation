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

    companion object{
        var TEST_MODE = false

        private val dataBaseName ="MM_WORLD_SITUATION"

        private lateinit var db: AppDataBase
        private lateinit var dbInstance: WorldDao

        fun getInstance(context: Context): WorldDao {
            if(TEST_MODE == null){
                db = Room.databaseBuilder(context, AppDataBase::class.java, dataBaseName)
                    .allowMainThreadQueries()
                    .build()

                dbInstance = db.worldDao()
            }
            return dbInstance
        }

        private fun close(){
            db.close()
        }
    }
}