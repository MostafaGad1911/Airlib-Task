package gad.projects.airlib_task.presentation.problems

import android.app.Application
import androidx.lifecycle.viewModelScope
import gad.projects.airlib_task.R
import gad.projects.airlib_task.core.base.BaseViewModel
import gad.projects.airlib_task.core.manager.networkconnectivity.NetworkConnectivityManager
import gad.projects.airlib_task.data.datasource.api.entities.AssociatedDrug
import gad.projects.airlib_task.domain.model.AirLibAuthenticationUiState
import gad.projects.airlib_task.domain.usecase.GetProblemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pithsoft.space.core.businessmodel.BusinessError

class UserProblemsViewModel(
    application: Application,
    networkConnectivityManager: NetworkConnectivityManager,
    val getProblemsUseCase: GetProblemsUseCase,
) : BaseViewModel(application, networkConnectivityManager) {


    private var _associatedDrugs: MutableStateFlow<ArrayList<AssociatedDrug>> =
        MutableStateFlow(arrayListOf())
    val associatedDrugs = _associatedDrugs.asStateFlow()


    private var _associatedDrug: MutableStateFlow<AssociatedDrug?> =
        MutableStateFlow(null)
    val associatedDrug = _associatedDrug.asStateFlow()

    private var _airliftAuthUiState = MutableStateFlow(AirLibAuthenticationUiState())
    val airliftAuthUiState = _airliftAuthUiState.asStateFlow()

    suspend fun getProblems() {
        viewModelScope.launch {
            try {
                ensureInternetConnection {
                    val problems = getProblemsUseCase.invoke()
                    for (problem in problems) {
                        val medicationClasses =
                            problem.Diabetes.first().medications.first().medicationsClasses
                        if (_associatedDrugs.value.isEmpty())
                            for (medicationClass in medicationClasses) {
                                _associatedDrugs.value.addAll(
                                    medicationClass.className.first().associatedDrug
                                )
                                _associatedDrugs.value.addAll(
                                    medicationClass.className2.first().associatedDrug
                                )
                            }


                    }
                }
            } catch (exception: Exception) {
                error.value = BusinessError(
                    title = application.getString(R.string.general_error_title),
                    message = exception.message!!
                )
            }
        }
    }


    fun updateEmail(email: String) {
        _airliftAuthUiState.update { userState ->
            userState.copy(email = email)
        }
    }

    fun resetGeneralError() {
        error.value = null
    }

    fun updateEmailError(emailError: String) {
        _airliftAuthUiState.update { userState ->
            userState.copy(emailError = emailError)
        }
    }

    fun updatePassword(password: String) {
        _airliftAuthUiState.update { userState ->
            userState.copy(
                password = password
            )
        }

    }

    fun updatePasswordError(passwordError: String) {
        _airliftAuthUiState.update { userState ->
            userState.copy(passwordError = passwordError)
        }
    }

    fun setAssociatedDrug(associatedDrug: AssociatedDrug) {
        _associatedDrug.value = associatedDrug
    }
}
