package br.com.module.situationworld.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CountryInfo (

    @SerializedName("_id")
    val _id: String,

    @SerializedName("iso2")
    val iso2: String,

    @SerializedName("iso3")
    val iso3: String,

    @SerializedName("lat")
    val latitude: Float,

    @SerializedName("long")
    val longitude: Float,

    @SerializedName("flag")
    val flag: String
) : Serializable