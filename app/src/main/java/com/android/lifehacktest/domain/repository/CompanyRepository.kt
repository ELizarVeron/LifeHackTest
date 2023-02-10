package com.android.lifehacktest.domain.repository

import com.android.lifehacktest.domain.models.Company
import com.android.lifehacktest.domain.models.CompanyInfo
import com.android.lifehacktest.data.repository.*


interface CompanyRepository {
    suspend fun getCompanies(): Result<List<Company>>?
    suspend fun  getCompanyInfo(id:String): Result <CompanyInfo >
}