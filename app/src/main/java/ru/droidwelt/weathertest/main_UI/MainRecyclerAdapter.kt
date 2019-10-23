package ru.droidwelt.weathertest.main_UI

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import ru.droidwelt.weathertest.R
import ru.droidwelt.weathertest.retrofit.CityWeatherDataStructure

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    private var act: MainActivity? = null

    fun setMainActivity(a: MainActivity) {
        act = a
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(itemLayoutView)
    }

    override fun getItemCount(): Int {
        return act!!.listMain!!.size
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val ds = act!!.listMain!![position]
        viewHolder.dt_date.text = ds.dt
        viewHolder.dt_temp.text = ds.main!!.temp
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

        val dt_date: TextView
        val dt_temp: TextView

        init {
            dt_temp = itemLayoutView.findViewById(R.id.dt_temp)
            dt_date = itemLayoutView.findViewById(R.id.dt_date)

        }
    }
}