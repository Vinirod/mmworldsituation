package br.com.module.situationworld.room


import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import br.com.module.situationworld.room.dao.WorldDao


import br.com.module.situationworld.room.database.AppDataBase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class WorldDaoTest {

    private lateinit var mAppDataBase: AppDataBase

    @Mock
    private lateinit var context: Context

    @Before
    fun initDb(){
        mAppDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
    }

    @Test
    fun insertWorld(){
        val world = WorldMock.makeWorldEntity()
        mAppDataBase.worldDao().insert(world)

        val getWorld = mAppDataBase.worldDao().getWorld()

        Assert.assertEquals(getWorld.get(0).id, 1)
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        mAppDataBase.close()
    }
}