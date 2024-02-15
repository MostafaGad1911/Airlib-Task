package gad.projects.airlib_task.core.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import gad.projects.airlib_task.core.exception.device.NoInternetConnectionException
import gad.projects.airlib_task.core.manager.networkconnectivity.NetworkConnectivityManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pithsoft.space.core.businessmodel.BusinessError

open class BaseViewModel(
    protected val application: Application,
    private val networkConnectivityManager: NetworkConnectivityManager,
) : ViewModel() {
    private var navController: NavController? = null

    private val showLoadingIndicator = MutableStateFlow(false)
    val showLoading = showLoadingIndicator.asStateFlow()


    protected val error = MutableStateFlow<BusinessError?>(null)
    val generalError = error.asStateFlow()

    fun navigateToSelectedScreen(route: String) {
        navController?.navigate(route = route)
    }

    open fun updateNavController(navController: NavController) {
        this.navController = navController
    }

    protected suspend fun ensureInternetConnection(action: suspend () -> Unit) {
        showLoadingIndicator.value = true
        if (networkConnectivityManager.isNetworkOnline) {
            action()
        } else {
            showLoadingIndicator.value = false
            throw NoInternetConnectionException()
        }
        showLoadingIndicator.value = false
    }


}