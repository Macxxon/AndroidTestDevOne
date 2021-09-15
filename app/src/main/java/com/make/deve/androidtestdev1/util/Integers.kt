package com.make.deve.androidtestdev1.util

import androidx.annotation.IntegerRes
import com.make.deve.androidtestdev1.ui.App

object Integers {
    fun get(@IntegerRes stringRes: Int): Int {
        return App.instance.resources.getInteger(stringRes)
    }
}