package gad.projects.airlib_task.ui.composables

import android.graphics.Color.BLACK
import android.graphics.Color.GRAY
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gad.projects.airlib_task.R
import gad.projects.airlib_task.ui.theme.Purple40


@Composable
fun BaseTextFields(
    modifier: Modifier = Modifier,
    value: String?,
    placeholder: String,
    passwordComplexityLevel: Int? = 0,
    keyboardType: KeyboardType = KeyboardType.Text,
    startIcon: @Composable() (() -> Unit)? = null,
    endIcon: @Composable() (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    isShowAsPassword: Boolean = false,
    showPasswordConstraints: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    label: String = "",
    labelFontSize: Int = 16,
    isPhone: Boolean = false,
    errorMessage: String? = null,
    maxLength: Int = if (isPhone) 9 else Int.MAX_VALUE,
    outlinedFieldModifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    readOnly: Boolean = false ,
    containerColor : Color ?= null ,
    textColor : Color ?= null
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    var maxFieldLength by remember {
        mutableStateOf(maxLength)
    }
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                ) {
                    val containerColor1 = containerColor ?:  White
                    OutlinedTextField(
                        label = { Text(text = label, color = Gray) },
                        modifier = outlinedFieldModifier
                            .fillMaxWidth(),
                        value = value ?: "",
                        enabled = enabled,
                        singleLine = singleLine,
                        maxLines = maxLines,
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = imeAction
                        ), readOnly = readOnly,
                         visualTransformation = if (!passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        textStyle = MaterialTheme.typography.bodySmall.copy(
                            color = if(value.isNullOrEmpty()) Gray else Color(color = 0xFF000000),
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = containerColor1,
                            unfocusedContainerColor = containerColor1,
                            disabledContainerColor = containerColor1,
                            focusedBorderColor = Purple40,
                            unfocusedBorderColor = Gray,
                            disabledBorderColor = Gray
                        ),
                        placeholder = {

                        } ,
                        onValueChange = {
                            onValueChange.invoke(it)
                        },
                        leadingIcon = startIcon,
                        trailingIcon = {
                            if (keyboardType == KeyboardType.Password) {
                                val image =
                                    Image(
                                        modifier = Modifier.size(15.dp),
                                        painter = if (!passwordVisible) painterResource(
                                            id = R.drawable.ic_password_hide
                                        ) else painterResource(id = R.drawable.ic_password_show),
                                        contentDescription = "password"
                                    )


                                // Please provide localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                IconButton(
                                    modifier = Modifier.size(15.dp),
                                    onClick = { passwordVisible = !passwordVisible }) {
                                    image
                                }
                            } else
                                endIcon?.invoke()

                        }
                    )


                }


            }
            if (errorMessage != null && value.isNullOrEmpty()) {
                Row {
                    if (isPhone)
                        Spacer(modifier = Modifier.width(87.dp))
                    Text(text = errorMessage, color = Color.Red, fontSize = 12.sp)
                }
            }

        }
    }


}

@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String?,
    placeholder: String,
    onValueChange: (String) -> Unit,

    ) {
    var maxFieldLength by remember {
        mutableStateOf(Int.MAX_VALUE)
    }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TextField(
                value = value ?: "",
                onValueChange = {
                    if (it.length <= maxFieldLength) {
                        onValueChange.invoke(it)
                    }
                },
                placeholder = {
                    Text(
                        modifier = Modifier.wrapContentHeight(),
                        text = placeholder,
                        fontSize = 16.sp,
                        color = Gray
                    )
                },
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    color = Black ,
                    fontSize = 18.sp
                ),
                visualTransformation =  VisualTransformation.None,
                        colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent ,

                ),
                maxLines = Int.MAX_VALUE,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}
@Preview
@Composable
fun PreviewLoginFormTextField() {
    BaseTextFields(
        value = "",
        placeholder = "placeholder",
        onValueChange = {},
        passwordComplexityLevel = 0
    )
}


