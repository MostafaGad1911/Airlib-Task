package gad.projects.airlib_task.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gad.projects.airlib_task.R
import gad.projects.airlib_task.core.helpers.getGreetingMessage
import gad.projects.airlib_task.data.datasource.api.entities.AssociatedDrug
import gad.projects.airlib_task.presentation.problems.UserProblemsViewModel
import gad.projects.airlib_task.ui.composables.AirLibLoadingView
import gad.projects.airlib_task.ui.composables.HSpacer
import gad.projects.airlib_task.ui.composables.VSpacer
import gad.projects.airlib_task.ui.navigation.Screens
import pithsoft.space.core.composable.dialog.ErrorDialog

@Composable
fun ProblemsScreen(problemsViewModel: UserProblemsViewModel, email: String) {

    val showLoading = problemsViewModel.showLoading.collectAsStateWithLifecycle().value
    val associatedDrugs = problemsViewModel.associatedDrugs.collectAsStateWithLifecycle().value


    BackHandler(onBack = {
        problemsViewModel.navigateToSelectedScreen(Screens.Login.route)
    })

    LaunchedEffect(Unit, block = {
        problemsViewModel.getProblems()
    })

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(text = getGreetingMessage() + " $email")

            VSpacer(e = 10)
            LazyColumn(content = {
                associatedDrugs.forEach { associatedDrug ->
                    item {
                        AssociatedDrugRow(associatedDrug = associatedDrug) {
                            problemsViewModel.setAssociatedDrug(associatedDrug = it)
                            problemsViewModel.navigateToSelectedScreen("${Screens.ProblemDetails.route}/$email")
                        }
                    }
                }
            }, verticalArrangement = Arrangement.spacedBy(10.dp))
        }
        if (showLoading) AirLibLoadingView()

        ErrorDialog(error = problemsViewModel.generalError.collectAsStateWithLifecycle().value,
            onConfirm = { problemsViewModel.resetGeneralError() },
            onDismiss = {})
    }

}

@Composable
fun AssociatedDrugRow(
    associatedDrug: AssociatedDrug,
    onAssociatedDrugClick: ((associatedDrug: AssociatedDrug) -> Unit)? = null
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            onAssociatedDrugClick?.invoke(associatedDrug)
        }
        .clip(RoundedCornerShape(15.dp))
        .border(shape = RoundedCornerShape(15.dp), width = 1.dp, color = Color.LightGray)
        .padding(all = 5.dp), verticalArrangement = Arrangement.Center) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.associated_drug),
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(600)
            )
            HSpacer(e = 8)
            Text(
                text = associatedDrug.name,
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight(400)
            )
        }
        VSpacer(e = 4)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.strength),
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight(600)
            )
            HSpacer(e = 8)
            Text(
                text = associatedDrug.strength,
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight(400)
            )
        }
    }
}