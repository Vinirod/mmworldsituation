package br.com.module.situationworld.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.module.situationworld.room.entity.World

interface WorldDao {

    @Query("SELECT * FROM "+ World.TABLE_NAME)
    fun getWorld(): List<World>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(world: World)

    @Query("DELETE FROM "+ World.TABLE_NAME)
    fun deleteAll()
}