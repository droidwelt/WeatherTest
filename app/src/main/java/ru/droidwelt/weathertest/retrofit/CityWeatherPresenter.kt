package ru.droidwelt.weathertest.retrofit


import ru.droidwelt.weathertest.main_UI.MainActivity

class CityWeatherPresenter {

    private var view: MainActivity? = null

    fun attachView(usersActivity: MainActivity) {
        view = usersActivity
    }

    fun detachView() {
        view = null
    }

    fun getCityWeatherData(city: String) {
        CityWeatherLoader().getCityWeather(city, this)
    }


    fun isReadyCityWeatherData(ds: CityWeatherDataStructure) {
        if (view != null) {
            view!!.displayCityWeather(ds)
        }
    }


}
