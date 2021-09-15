package com.make.deve.androidtestdev1.di

import com.make.deve.androidtestdev1.local.database.DataBaseLocalModule
import com.make.deve.androidtestdev1.ui.main.MainModule
import org.koin.core.module.Module

object Modules {
    fun getAllModules(): List<Module> {
        val allModules = ArrayList<Module>()

        //region Service Modules
        allModules.addAll(DataBaseLocalModule)
        //endregion
        //region UI Modules
        allModules.addAll(MainModule)
        //endregion



        return allModules
    }
}

fun ArrayList<Module>.addAll(mod: BaseModule) {
    addAll(mod.modules)
}