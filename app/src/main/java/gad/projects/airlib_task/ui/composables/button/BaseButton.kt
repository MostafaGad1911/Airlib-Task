package pithsoft.space.core.composable.button

import android.graphics.Color.GRAY
import android.graphics.Color.WHITE
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gad.projects.airlib_task.domain.model.ButtonState
import gad.projects.airlib_task.ui.theme.Purple80

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: ButtonState = ButtonState.ENABLED,
    fontSize: Int = 15,
    defaultEnabledTextColor: Color = Color.White,
    disabledTextColor: Color = Color.Gray,
    defaultBgColor: Color = Purple80,
    shape: Shape = MaterialTheme.shapes.medium,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    textAlign: TextAlign = TextAlign.Start,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .clip(shape = shape)
            .border(shape = shape, width = 1.dp, color = Color.Transparent)
    ) {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = defaultBgColor
                ),
            onClick = onClick,
            enabled = buttonState == ButtonState.ENABLED,
            contentPadding = PaddingValues(0.dp),
            shape = shape,
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color.Transparent,
            ),
        ) {
            Box(modifier = Modifier.wrapContentSize()) {
                if (buttonState == ButtonState.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Purple80
                    )
                }
                Row(
                    modifier = modifier
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    startIcon?.invoke()
                    Text(
                        modifier = Modifier
                            .wrapContentSize(),
                        text = text,
                        fontSize = fontSize.sp,
                        color = if (buttonState == ButtonState.ENABLED)
                            defaultEnabledTextColor
                        else
                            disabledTextColor,
                        fontWeight = FontWeight.W800,
                        textAlign = TextAlign.Center
                    )
                    endIcon?.invoke()
                }
            }
        }
    }
}









