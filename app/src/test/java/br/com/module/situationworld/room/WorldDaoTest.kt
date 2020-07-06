package br.com.module.situationworld.room

import androidx.room.Insert
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import br.com.module.situationworld.room.database.AppDataBase
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WorldDaoTest {

    private lateinit var mAppDataBase: AppDataBase

    @Before
    fun initDb(){
        mAppDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDataBase::class.java).build()
    }

    @Insert
    fun insertWorld(){
        val world = WorldMock.makeWorldEntity()
        mAppDataBase.worldDao().insert(world)

        val getWorld = mAppDataBase.worldDao().getWorld()

        Assert.assertEquals(getWorld.get(0).id, 1)
    }

    @After
    fun closeDb(){
        mAppDataBase.close()
    }
}