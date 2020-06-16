package br.com.module.situationworld.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Historical (

    @SerializedName("cases")
    val cases: Map<String, Long>,

    @SerializedName("deaths")
    val deaths: Map<String, Long>,

    @SerializedName("recovered")
    val recovered: Map<String, Long>
) : Serializable