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
import gad.projects.airlib_task.presentation.problems.UserProblemsViewModel
import gad.projects.airlib_task.ui.composables.HSpacer
import gad.projects.airlib_task.ui.composables.VSpacer
import gad.projects.airlib_task.ui.navigation.Screens

@Composable
fun ProblemDetailsScreen(problemsViewModel: UserProblemsViewModel , email:String) {
    val associatedDrug = problemsViewModel.associatedDrug.collectAsStateWithLifecycle().value


    BackHandler(onBack = {
        problemsViewModel.navigateToSelectedScreen(Screens.Problems.route.plus("/${email}"))
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

            if (associatedDrug != null) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
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
            VSpacer(e = 10)
        }

    }

}
