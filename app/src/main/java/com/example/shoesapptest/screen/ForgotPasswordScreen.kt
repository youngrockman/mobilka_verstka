package com.example.shoesapptest.screen

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoesapp.ui.theme.MatuleTheme
import com.example.shoesapptest.R


@Composable
fun ForgotPassScreen() {
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 35.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        ForgotPassContent(paddingValues, showDialog)
    }


    if (showDialog.value) {
        EmailSentDialog(onDismiss = { showDialog.value = false })
    }
}

@Composable
fun ForgotPassContent(paddingValues: PaddingValues, showDialog: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitleWithSubtitleText(
            title = "Забыл пароль",
            subText = "Введите свою учетную запись\nдля сброса"
        )

        val email = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(35.dp))
        ForgotPassField(
            value = email.value,
            onChangeValue = { email.value = it },
            placeHolderText = "xyz@gmail.com"
        )
        CommonButton(
            modifier = Modifier.padding(top = 50.dp),
            buttonLabel = "Отправить"
        ) { showDialog.value = true }
    }
}

@Composable
fun CommonButton(modifier: Modifier,buttonLabel:String, onClick: () -> Unit){
    Button(modifier = modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(MatuleTheme.colors.accent),
        colors = ButtonColors(
            contentColor = MatuleTheme.colors.accent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = MatuleTheme.colors.accent,
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonLabel,
            style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.background),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TitleWithSubtitleText(title:String, subText:String){
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text= title,
            style = MatuleTheme.typography.headingBold32.copy(color = MatuleTheme.colors.text),
            textAlign = TextAlign.Center
        )
        Text(
            text = subText,
            style = MatuleTheme.typography.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassField(
    value: String,
    onChangeValue: (String) -> Unit,
    placeHolderText: String? = null,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = value,
            onValueChange = { onChangeValue(it) },
            modifier = Modifier
                .fillMaxWidth()
                .background(MatuleTheme.colors.background)
                .clip(RoundedCornerShape(14.dp))
                .padding(16.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                singleLine = true,
                innerTextField = innerTextField,
                enabled = true,
                interactionSource = interaction,
                visualTransformation = VisualTransformation.None,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MatuleTheme.colors.background,
                    disabledContainerColor = MatuleTheme.colors.background,
                    unfocusedContainerColor = MatuleTheme.colors.background,
                    errorContainerColor = MatuleTheme.colors.background,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    if (placeHolderText != null) {
                        Text(
                            text = placeHolderText,
                            style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint)
                        )
                    }
                }
            )
        }
    }
}



@Composable
fun EmailSentDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.email_image),
                        contentDescription = null,
                        tint = MatuleTheme.colors.accent,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text(
                    text = "Проверьте Ваш Email",
                    style = MatuleTheme.typography.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Text(
                text = "Мы отправили код восстановления пароля на вашу электронную почту.",
                textAlign = TextAlign.Center,
                style = MatuleTheme.typography.bodyRegular14.copy(color = MatuleTheme.colors.hint)
            )
        },
        modifier = Modifier.clip(RoundedCornerShape(14.dp)),
        containerColor = Color.White
    )
}
















