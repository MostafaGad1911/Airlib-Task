package gad.projects.airlib_task.domain.model


data class AirLibAuthenticationUiState(
    val email:String? = null ,
    val emailError:String? = null ,
    val password:String? = null ,
    val passwordError:String? = null ,
  )
