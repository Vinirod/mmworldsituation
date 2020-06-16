package br.com.module.situationworld.apis

import br.com.module.situationworld.models.Historical
import br.com.module.situationworld.models.World
import retrofit2.Call
import retrofit2.http.GET

interface WorldApi{

    @GET("all")
    fun getWorld(): Call<World>

    @GET("historical/all?lastdays=365")
    fun getHistorical() : Call<Historical>
}