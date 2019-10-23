package ru.droidwelt.weathertest.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList


class CityWeatherDataStructure {

    @SerializedName("cod")
    @Expose
    var cod: String? = null

    @SerializedName("cnt")
    @Expose
    var cnt: String? = null


    @SerializedName("city")
    @Expose
    var city: CityStructure? = null

    @SerializedName("list")
    @Expose
    var list = ArrayList<ListStructure>()

    inner class CityStructure {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("timezone")
        @Expose
        var timezone: String? = null

        @SerializedName("country")
        @Expose
        var country: String? = null
    }

    inner class MainStructure {
        @Expose
        var temp: String? = null
    }

    inner class ListStructure {
        @SerializedName("dt_txt")
        @Expose
        var dt: String? = null

        @SerializedName("main")
        @Expose
        var main: MainStructure? = null
    }


}