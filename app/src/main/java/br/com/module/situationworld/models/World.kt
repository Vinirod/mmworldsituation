package br.com.module.situationworld.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class World(

    @SerializedName("cases")
    val cases: Long,

    @SerializedName("todayCases")
    val todayCases: Long,

    @field:SerializedName("todayDeaths")
    val todayDeaths: Long,

    @field:SerializedName("recovered")
    val recovered: Long,

    @field:SerializedName("active")
    val active: Long,

    @field:SerializedName("critical")
    val critical: Long,

    @field:SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Long,

    @field:SerializedName("deathsPerOneMillion")
    val deathsPerMillion: Float,

    @field:SerializedName("tests")
    val tests: Long,

    @field:SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Float,

    @SerializedName("affectedCountries")
    val affectedCountries: Long,

    @SerializedName("deaths")
    val deaths: Long

) : Serializable