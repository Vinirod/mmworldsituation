package br.com.module.situationworld.room

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.module.situationworld.room.entity.World

@Database(entities = arrayOf(World::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun worldDao(): MainDao

}