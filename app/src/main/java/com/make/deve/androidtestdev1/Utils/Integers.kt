package com.make.deve.androidtestdev1.Utils

import androidx.annotation.IntegerRes

object Integers {
    fun get(@IntegerRes stringRes: Int): Int {
        return App.instance.resources.getInteger(stringRes)
    }
}