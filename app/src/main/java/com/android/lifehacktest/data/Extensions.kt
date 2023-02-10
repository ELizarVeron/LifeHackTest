package com.android.lifehacktest.data

import com.android.lifehacktest.data.model.CompanyInfoResponse
import com.android.lifehacktest.data.model.CompanyResponse
import com.android.lifehacktest.domain.models.Company
import com.android.lifehacktest.domain.models.CompanyInfo


fun CompanyInfoResponse.toCompanyInfo(): CompanyInfo =
    CompanyInfo(
        id = id.toString(),
        name = name,
        img = img,
        description = description,
        lat = lat,
        lon = lon,
        www = www,
        phone = phone
    )

fun CompanyResponse.toCompany(): Company =
    Company(
        id = id,
        name = name,
        img = img
    )











