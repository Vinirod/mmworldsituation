package br.com.module.situationworld.room

import androidx.room.*
import br.com.module.situationworld.room.entity.World

@Dao
interface MainDao {

    @Transaction
    @Query("SELECT * FROM "+ World.TABLE_NAME)
    fun getWorld(): List<WorldAndHistoricalCases>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(world: World)

    @Query("DELETE FROM "+ World.TABLE_NAME)
    fun deleteAll()
}