package ru.droidwelt.weathertest.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class Appl : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        instanse = this
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set

        @SuppressLint("StaticFieldLeak")
        private var instanse: Appl? = null

        private fun geInstanse(): Appl? {
            return instanse
        }


    }


}
