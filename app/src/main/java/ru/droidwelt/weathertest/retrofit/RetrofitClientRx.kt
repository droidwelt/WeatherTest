package ru.droidwelt.weathertest.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.droidwelt.weathertest.common.Const
import rx.Observable


class RetrofitClientRx private constructor() {
    private val apiService: RetrofitApiServiceRx

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl(Const.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        apiService = retrofit.create(RetrofitApiServiceRx::class.java)
    }


    fun getCityWeatherJSON(CITY: String): Observable<CityWeatherDataStructure> {
        return apiService.getCityWeatherJSON(CITY, Const.UNITS, Const.APPID)
    }

    companion object {

        private var instance: RetrofitClientRx? = null

        fun getInstance(): RetrofitClientRx {
            if (instance == null) {
                instance = RetrofitClientRx()
            }
            return instance as RetrofitClientRx
        }

        fun clearInstance() {
            instance = null
        }
    }


}


