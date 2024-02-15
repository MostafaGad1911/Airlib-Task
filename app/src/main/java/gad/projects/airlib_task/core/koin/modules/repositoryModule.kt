package gad.projects.airlib_task.core.koin.modules


import gad.projects.airlib_task.domain.repository.AirLibRepository
import gad.projects.airlib_task.domain.repository.AirLibRepositoryImpl
import org.koin.dsl.module

/**
 * This property is used to create Use cases and inject needed parameters
 * */
val repositoryModule = module {

    single<AirLibRepository> {
        AirLibRepositoryImpl(
            datasource = get()
        )
    }
}
