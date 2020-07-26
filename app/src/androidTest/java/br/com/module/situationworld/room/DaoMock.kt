package br.com.module.situationworld.room

import br.com.module.situationworld.room.entity.World

class DaoMock {

    companion object Mock{
        fun makeWorldEntity(): World{
            //val historicalCase = HistoricalCases(20, "12/04/2020")
//            val listHistoricalCase = ArrayList<HistoricalCases>()
//            listHistoricalCase.add(historicalCase)
            val world =  World(200, 300, 50, 100, 50,50, 2,4f,100,5f,215, 100)
            return world
        }
    }
}