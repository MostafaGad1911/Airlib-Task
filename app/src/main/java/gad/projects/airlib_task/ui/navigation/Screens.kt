package gad.projects.airlib_task.ui.navigation

sealed class Screens(val route: String) {
    data object Login : Screens("login")
    data object Problems : Screens("problems")
    data object ProblemDetails : Screens("problem_details")

}