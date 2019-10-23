package ru.droidwelt.weathertest.main_UI

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.ArrayList

import ru.droidwelt.weathertest.R
import ru.droidwelt.weathertest.retrofit.CityWeatherDataStructure
import ru.droidwelt.weathertest.retrofit.CityWeatherPresenter


class MainActivity : AppCompatActivity() {

    private var et_: EditText? = null
    private var bt_: Button? = null

    internal lateinit var ds_Adapter: MainRecyclerAdapter
    internal lateinit var ds_RecyclerView: RecyclerView

    private var list_main: MutableList<CityWeatherDataStructure.ListStructure>? = null

    val listMain: List<CityWeatherDataStructure.ListStructure>?
        get() = list_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_main = ArrayList<CityWeatherDataStructure.ListStructure>()

        et_ = findViewById(R.id.et_)
        bt_ = findViewById(R.id.bt_)

        bt_!!.setOnClickListener {
            val city = et_!!.text.toString().trim { it <= ' ' }
            if (!city.isEmpty()) {
                requestWeather(city)
            }
        }

        et_!!.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val city = et_!!.text.toString().trim { it <= ' ' }
                if (!city.isEmpty()) {
                    requestWeather(city)
                }
                return@OnKeyListener true
            }
            false
        })

        ds_RecyclerView = findViewById(R.id.rv_)
        ds_RecyclerView.isHapticFeedbackEnabled = true
        val dsLayoutmanager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        ds_RecyclerView.layoutManager = dsLayoutmanager

        ds_Adapter = MainRecyclerAdapter()
        ds_Adapter.setMainActivity(this)
        ds_RecyclerView.adapter = ds_Adapter
    }

    //  создание Presenter и запрос данных
    private fun requestWeather(city: String) {
        val testpresenter = CityWeatherPresenter()
        testpresenter.attachView(this)
        testpresenter.getCityWeatherData(city)
    }

    // вызывается из Presenter по получении ответа о записи
    fun displayCityWeather(ds: CityWeatherDataStructure) {
        list_main!!.clear()
        val idt = ds.list.size
        for (i in 0 until idt) {
            ds.list[i].dt = ds.list[i].dt!!.replace(":00:00", ":00")
            list_main!!.add(ds.list[i])
            Log.e("CityWeatherLoader", "list.dt=" + ds.list[i].dt!!)
            Log.e("CityWeatherLoader", "list.temp=" + ds.list[i].main!!.temp!!)
        }
        ds_Adapter.notifyDataSetChanged()
    }
}
