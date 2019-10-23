package ru.droidwelt.weathertest.retrofit

import android.annotation.SuppressLint
import android.util.Log

import android.widget.Toast

import ru.droidwelt.weathertest.R
import ru.droidwelt.weathertest.common.Appl
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class CityWeatherLoader {

    fun getCityWeather(city: String, cip: CityWeatherPresenter) {
        RetrofitClientRx.getInstance()
                .getCityWeatherJSON(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CityWeatherDataStructure> {
                    override fun onCompleted() {}

                    @SuppressLint("ShowToast")
                    override fun onError(e: Throwable) {
                        val s: String
                        val sMes = e.message
                        if (sMes?.contains("404")!!) {
                            s = Appl.context?.getString(R.string.s_city_not_found) + "\n" + city
                        } else {
                            s = Appl.context?.getString(R.string.s_error_api) + "\n" + e.message
                        }
                        Toast.makeText(Appl.context, s, Toast.LENGTH_LONG).show()
                        Log.e("CityWeatherLoader", e.message)
                    }

                    override fun onNext(ds: CityWeatherDataStructure) {

                        if (ds.list.size > 0)
                            cip.isReadyCityWeatherData(ds)
                    }
                })
    }

}
