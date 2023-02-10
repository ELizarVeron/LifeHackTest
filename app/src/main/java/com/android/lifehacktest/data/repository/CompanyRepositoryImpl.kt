package com.android.lifehacktest.data.repository

import android.util.Log
import com.android.lifehacktest.data.retrofit.RetrofitServices
import com.android.lifehacktest.data.toCompany
import com.android.lifehacktest.domain.models.Company
import com.android.lifehacktest.domain.models.CompanyInfo
import com.android.lifehacktest.domain.repository.CompanyRepository


class CompanyRepositoryImpl(private val rService: RetrofitServices) : CompanyRepository {
    override suspend fun getCompanies(): Result<List<Company>>? =
        try {
            Result.Success(rService.fetchCompanies().map { it.toCompany() }
            )
        } catch (e: Exception) {
            Result.Error(e)
        }


    override suspend fun getCompanyInfo(id: String): Result<CompanyInfo> =
        try {
            Result.Success(parsing(rService.fetchCompanyInfo(id)))
            // Result.Success(rService.fetchCompanyInfo(id).first().toCompanyInfo())
        } catch (e: Exception) {
            Result.Error(e)
        }

    fun parsing(s: String): CompanyInfo {
        Log.d("OLOLO1",s)
        val tempList = mutableListOf<String>()
        val str = s
        var s1=""
        var s2=""
        var s3=""

        s1 = str.substringAfter("id\":\"")
        s2 = s1.substringBefore("\",")
        val id = s2.toInt()
        Log.d("OLOLO1",id.toString())

        s1 = s1.substringAfter("name\":\"")
        s2 = s1.substringBefore("\",")
        val name = s2
        Log.d("OLOLO1",name)

        s1 = s1.substringAfter("img\":\"")
        s2 = s1.substringBefore("\",")
        val img = s2
        Log.d("OLOLO1",img)

        s1 = s1.substringAfter("description\":\"")
        s2 = s1.substringBefore("\",")
        val description = s2
        Log.d("OLOLO1",description)


        s1 = s1.substringAfter("lat\":")
        s2 = s1.substringBefore(",")
        val lat = s2.toDouble()
        Log.d("OLOLO1",lat.toString())


        s1 = s1.substringAfter("lon\":")
        s2 = s1.substringBefore(",")
        val lon = s2.toDouble()
        Log.d("OLOLO1",lon.toString())


        s1 = s1.substringAfter("www\":\"")
        s2 = s1.substringBefore("\",")
        val www = s2
        Log.d("OLOLO1",www)


        s1 = s1.substringAfter("phone\":\"")
        s2 = s1.substringBefore("\"")
        val phone = s2
        Log.d("OLOLO1",phone)


        return CompanyInfo(id.toString(), name, img, description, lat, lon, www, phone)
    }

}