package com.make.deve.androidtestdev1.local.database

import com.make.deve.androidtestdev1.di.BaseModule
import org.koin.core.module.Module
import org.koin.dsl.module

object DataBaseLocalModule: BaseModule() {
    override val modules: List<Module>
        get() = listOf(mod)

    val mod = module {
        single<IDataBaseLocal> { DataBaseLocal(get()) }
    }
}