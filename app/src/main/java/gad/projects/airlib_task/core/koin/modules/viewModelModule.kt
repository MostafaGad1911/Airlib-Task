package gad.projects.airlib_task.core.koin.modules

import gad.projects.airlib_task.presentation.problems.UserProblemsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        UserProblemsViewModel(
            getProblemsUseCase = get(),
            networkConnectivityManager = get(),
            application = androidApplication(),
        )
    }

}
