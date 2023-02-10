package com.android.lifehacktest.data.retrofit
import com.android.lifehacktest.data.model.CompanyInfoResponse
import com.android.lifehacktest.data.model.CompanyResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitServices {

    @Headers("Content-Type:application/json")
    @GET(".")
    suspend fun fetchCompanies():  List<CompanyResponse>

    @Headers("Content-Type:application/json")
    @GET(".")

   /* suspend fun fetchCompanyInfo(
        @Query("id") id :String
    ):  List<CompanyInfoResponse>
*/
    suspend fun fetchCompanyInfo(
        @Query("id") id :String
    ):String
}