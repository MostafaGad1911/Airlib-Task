package gad.projects.airlib_task.core.koin.modules


import gad.projects.airlib_task.data.datasource.api.AirLibAPIDataSource
import gad.projects.airlib_task.data.repository.AirLibApiImp
import org.koin.dsl.module

/**
 * This property is used to create Use cases and inject needed parameters
 * */
val dataSourceModule = module {

    single<AirLibAPIDataSource> {
        AirLibApiImp()
    }

}
