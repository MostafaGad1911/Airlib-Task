package gad.projects.airlib_task.core.koin.modules

import gad.projects.airlib_task.domain.usecase.GetProblemsUseCase
import gad.projects.airlib_task.domain.usecase.GetProblemsUseCaseImp
import org.koin.dsl.module
val useCasesModule = module {

    factory<GetProblemsUseCase> {
        GetProblemsUseCaseImp(repository = get())
    }

}
