package gad.projects.airlib_task.ui.composables


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VSpacer(e: Int) {
    Spacer(modifier = Modifier.height(e.dp))
}

@Composable
fun HSpacer(e: Int) {
    Spacer(modifier = Modifier.width(e.dp))
}
