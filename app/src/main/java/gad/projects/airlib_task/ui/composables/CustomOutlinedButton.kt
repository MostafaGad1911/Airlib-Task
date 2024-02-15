package gad.projects.airlib_task.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gad.projects.airlib_task.domain.model.ButtonState

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> (Unit),
    fontSize: TextUnit? = null,
    cornerRaduis: Dp? = null,
    textColor: Color? = null,
    backgroundColor: Color? = null,
    fontWeight: FontWeight? = null,
    paddings: Int? = null,
    image: Int? = null,
    containerColor : Color?= null,
    buttonState: ButtonState = ButtonState.ENABLED,
    outerPadding:Int = 0

    ) {
    Box(modifier = Modifier.padding(horizontal = outerPadding.dp)) {
        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(cornerRaduis ?: 6.dp),
            contentPadding = PaddingValues(
                top = (paddings ?: 12).dp,
                bottom = (paddings ?: 12).dp,
                start = 4.dp, end = 4.dp
            ),
            enabled = buttonState == ButtonState.ENABLED,
            border = BorderStroke(1.dp, backgroundColor ?: Color(android.graphics.Color.WHITE)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = containerColor ?: Color(android.graphics.Color.TRANSPARENT),
            ),
            modifier = modifier.fillMaxWidth()
        ) {
            if (image != null) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = ""
                )
                HSpacer(4)
            }
            Text(
                text = text,
                style = TextStyle(
                    color = textColor ?: Color(android.graphics.Color.WHITE),
                    fontSize = fontSize ?: 18.sp,
                    fontWeight = fontWeight ?: FontWeight.Normal,
                )
            )

        }

    }
}
