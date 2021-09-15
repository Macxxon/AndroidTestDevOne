package com.make.deve.androidtestdev1.di

import org.koin.core.module.Module

abstract class BaseModule {
    abstract val modules: List<Module>
}