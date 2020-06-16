package br.com.module.situationworld.apis
import br.com.module.situationworld.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface FilteringApi {

    @GET("countries?sort=cases")
    fun getCountries() : Call<MutableList<Country>>

}