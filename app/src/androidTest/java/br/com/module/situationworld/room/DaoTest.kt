package br.com.module.situationworld.room

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DaoTest {

    private lateinit var mAppDataBase: AppDataBase

    private lateinit var mWorldDao: MainDao

    @Before
    @Throws(Exception::class)
    fun initDb() {
        mAppDataBase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        mWorldDao = mAppDataBase.worldDao()
    }

    @After
    @Throws(Exception::class)
    fun closeDb(){
        mAppDataBase.close()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun insertWorldAndGetWorld(){
        val world =
            DaoMock.makeWorldEntity()
        mWorldDao.insert(world)

        val getWorld = mAppDataBase.worldDao().getWorld()

        Assert.assertEquals(getWorld.get(0).world.id, 1)
        Assert.assertEquals(getWorld.get(0).historicalCases.get(0).date, "12/04/2020")
    }

}