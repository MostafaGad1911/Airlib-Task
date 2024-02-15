package gad.projects.airlib_task.core.koin.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import gad.projects.airlib_task.core.manager.networkconnectivity.NetworkConnectivityManager
import gad.projects.airlib_task.core.manager.networkconnectivity.NetworkConnectivityManagerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {

    single<NetworkConnectivityManager> {
        NetworkConnectivityManagerImpl(
            appContext = androidContext()
        )
    }

    single {
        getSharedPrefs(androidApplication())
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }
}


fun getSharedPrefs(androidApplication: Application): SharedPreferences {
    return androidApplication.getSharedPreferences(
        "airlib-sharedstore",
        Context.MODE_PRIVATE
    )
}
