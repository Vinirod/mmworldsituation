package br.com.module.situationworld.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

public object NumberFormatCustomUtil {

    @JvmStatic
    fun longToStringNoDecimal(d: Long): String? {
        return String.format("%,d", d)
    }

    @JvmStatic
    fun floatToStringNoDecimal(d: Float): String? {
        val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###,###,###")
        return formatter.format(d)
    }
}