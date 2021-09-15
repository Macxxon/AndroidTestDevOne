package com.make.deve.androidtestdev1.ui.menu

import com.make.deve.androidtestdev1.di.BaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MenuModule : BaseModule() {
    override val modules: List<Module>
        get() = listOf(mod)

    private val mod = module {
        viewModel { MenuViewModel() }
    }
}