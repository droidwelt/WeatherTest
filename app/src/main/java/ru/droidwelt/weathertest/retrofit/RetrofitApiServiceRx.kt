package ru.droidwelt.weathertest.retrofit


import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RetrofitApiServiceRx {


    @GET("forecast")
    fun getCityWeatherJSON(
            @Query("q") CITY: String,
            @Query("units") UNITS: String,
            @Query("APPID") APPID: String): Observable<CityWeatherDataStructure>


}



