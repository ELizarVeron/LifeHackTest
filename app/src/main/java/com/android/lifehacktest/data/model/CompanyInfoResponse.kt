package com.android.lifehacktest.data.model

import com.google.gson.annotations.SerializedName

data class CompanyInfoResponse(
    val id:Int,
    val name: String?,
    val img:String?,
    val description:String?,
    val lat:Double?,
    val lon:Double?,
    val www:String?,
    val phone:String?
)

