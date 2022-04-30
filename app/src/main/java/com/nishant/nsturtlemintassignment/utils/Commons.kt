package com.nishant.nsturtlemintassignment.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

object Commons {

    fun formattedDate(inputMillis: Long) : String{
        val outputFormat: DateFormat = SimpleDateFormat("MM-dd-yyyy")
        return outputFormat.format(inputMillis)
    }

    fun getMillis(inputDate: String) : Long{
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = format.parse(inputDate)
        return date.time
    }

}