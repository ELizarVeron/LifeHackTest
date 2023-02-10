package com.android.lifehacktest.data.retrofit

import ru.cft.shift2023winter.data.retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "https://lifehack.studio/test_task/test.php/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}