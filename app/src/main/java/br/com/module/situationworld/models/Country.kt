package br.com.module.situationworld.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Country(

    @SerializedName("updated")
    val updated: Long,

    @SerializedName("country")
    val country: String,

    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,

    @SerializedName("cases")
    val cases: Long,

    @SerializedName("todayCases")
    val todayCases: Long,

    @SerializedName("deaths")
    val deaths: Long,

    @SerializedName("todayDeaths")
    val todayDeaths: Long,

    @SerializedName("population")
    val population: Long,

    @SerializedName("recovered")
    val recovered: Long,

    @SerializedName("active")
    val active: Long,

    @SerializedName("critical")
    val critical: Long,

    @SerializedName("criticalPerOneMillion")
    val criticalPerOneMillion: Float,

    @SerializedName("activePerOneMillion")
    val activePerOneMillion: Float,

    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Float,

    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Float,

    @SerializedName("tests")
    val tests: Long,

    @SerializedName("testsPerOneMillion")
    val testsPerOneMillion: Long,

    @SerializedName("continent")
    val continents: String
) : Serializable


