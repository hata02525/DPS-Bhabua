package com.dps_kaimur.restservices

object ApiUtils {

    val BASE_URL = "http://worklime.com"
  //  val BASE_URL = "https://raw.githubusercontent.com"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)

}