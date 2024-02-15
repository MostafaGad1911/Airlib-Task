package gad.projects.airlib_task.core.koin

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import gad.projects.airlib_task.core.koin.modules.appModule
import gad.projects.airlib_task.core.koin.modules.dataSourceModule
import gad.projects.airlib_task.core.koin.modules.repositoryModule
import gad.projects.airlib_task.core.koin.modules.useCasesModule
import gad.projects.airlib_task.core.koin.modules.viewModelModule

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context)
            modules(
                appModule, viewModelModule, useCasesModule, repositoryModule, dataSourceModule
            )
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
