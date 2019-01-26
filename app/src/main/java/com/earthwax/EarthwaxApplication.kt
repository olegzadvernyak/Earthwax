package com.earthwax

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class EarthwaxApplication : Application() {

    private val module = module {
        single { Room.databaseBuilder(androidContext(), EarthwaxDatabase::class.java, "wax.db").build() }
        single { WaxRepository(get<EarthwaxDatabase>().waxDao()) }
        viewModel { MainViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EarthwaxApplication)
            modules(module)
        }
    }

}