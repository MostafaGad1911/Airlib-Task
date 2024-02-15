package gad.projects.airlib_task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import gad.projects.airlib_task.presentation.LoginScreen
import gad.projects.airlib_task.presentation.ProblemDetailsScreen
import gad.projects.airlib_task.presentation.ProblemsScreen
import gad.projects.airlib_task.presentation.problems.UserProblemsViewModel
import gad.projects.airlib_task.ui.navigation.Screens
import gad.projects.airlib_task.ui.theme.AirlibTaskTheme
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirlibTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
) {
    val navController: NavHostController = rememberAnimatedNavController()

    val problemsViewModel =
        getViewModel<UserProblemsViewModel>() {
            parametersOf(bundleOf())
        }.apply {
            updateNavController(navController = navController)
            parametersOf(navController)
        }

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screens.Login.route
    )
    {


        composable(route = Screens.Login.route) { backStackEntry ->
            LoginScreen(problemsViewModel)
        }

        composable(
            route = "${Screens.Problems.route}/{email}",
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { backStackEntry ->

            val args = backStackEntry.arguments
            val email =
                args?.getString("email") ?: ""

            ProblemsScreen(problemsViewModel, email)
        }

        composable(
            route = "${Screens.ProblemDetails.route}/{email}",
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val args = backStackEntry.arguments
            val email =
                args?.getString("email") ?: ""


            ProblemDetailsScreen(problemsViewModel = problemsViewModel , email = email)

        }
    }
}