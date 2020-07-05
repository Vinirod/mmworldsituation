package br.com.module.situationworld.room

import br.com.module.situationworld.room.entity.World

class WorldMock {

    companion object Mock{
        fun makeWorldEntity(): World{
            return World(1, 200, 300, 50, 100, 50,50, 2,4f,100,5f,215, 100)
        }
    }
}