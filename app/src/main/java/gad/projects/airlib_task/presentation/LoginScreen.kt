package gad.projects.airlib_task.presentation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gad.projects.airlib_task.R
import gad.projects.airlib_task.presentation.problems.UserProblemsViewModel
import gad.projects.airlib_task.ui.composables.BaseTextFields
import gad.projects.airlib_task.ui.composables.CustomOutlinedButton
import gad.projects.airlib_task.ui.composables.VSpacer
import gad.projects.airlib_task.ui.navigation.Screens
import gad.projects.airlib_task.ui.theme.Purple40

@Composable
fun LoginScreen(airLibViewModel: UserProblemsViewModel) {
    val context = LocalContext.current
    val activity = context as? Activity

    val loginUiState = airLibViewModel.airliftAuthUiState.collectAsStateWithLifecycle().value

    // Handle phone back click to exit app
    BackHandler(onBack = {
        activity?.finish()
    })

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    VSpacer(60)
                    Image(
                        modifier = Modifier
                            .wrapContentSize(),
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "choose splash",
                    )

                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                VSpacer(15)
                BaseTextFields(
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.email),
                    value = loginUiState.email ?: "",
                    keyboardType = KeyboardType.Email,
                    onValueChange = {
                        airLibViewModel.updateEmail(email = it)
                    },
                    imeAction = ImeAction.Next,
                    errorMessage = loginUiState.emailError,
                )

                VSpacer(15)
                BaseTextFields(
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.password),
                    value = loginUiState.password ?: "",
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(FocusRequester()),
                    onValueChange = {
                        airLibViewModel.updatePassword(password = it)
                    },

                    errorMessage = loginUiState.passwordError,
                    imeAction = ImeAction.Done,
                )
                VSpacer(15)
                Spacer(modifier = Modifier.weight(1f))

                VSpacer(24)
                CustomOutlinedButton(
                    text = stringResource(id = R.string.login),
                    onClick = {
                        if (loginUiState.email.isNullOrEmpty()) {
                            airLibViewModel.updateEmailError(emailError = context.getString(R.string.error_email))
                        } else if (loginUiState.password.isNullOrEmpty()) {
                            airLibViewModel.updatePasswordError(passwordError = context.getString(R.string.error_password))
                        } else {
                                  airLibViewModel.navigateToSelectedScreen(Screens.Problems.route.plus("/${loginUiState.email}"))
                        }
                    }, modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = Purple40,
                    containerColor = Purple40,
                    textColor = Color(android.graphics.Color.WHITE),
                    cornerRaduis = 29.dp
                )

                VSpacer(16)

            }
        }


    }
}