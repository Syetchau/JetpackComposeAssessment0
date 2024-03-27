package io.rapidz.training.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dagger.hilt.android.AndroidEntryPoint
import io.rapidz.jetpackcomposetraining_assignment0.R
import io.rapidz.training.models.User
import io.rapidz.training.storage.PreferenceManager
import io.rapidz.training.storage.PreferenceManager.LAST_LOGIN_USERNAME
import io.rapidz.training.theme.Color_Edit_Text_Bg
import io.rapidz.training.theme.Color_Edit_Text_Focus_Bg
import io.rapidz.training.theme.Color_Hint_Text
import io.rapidz.training.theme.Color_Login_Btn
import io.rapidz.training.theme.Color_Login_Btn_Disable
import io.rapidz.training.theme.FONT_SIZE_16
import io.rapidz.training.theme.SPACING_20
import io.rapidz.training.theme.SPACING_36
import io.rapidz.training.theme.SPACING_50
import io.rapidz.training.theme.SPACING_8
import io.rapidz.training.utils.UiUtils.goToNextActivity
import io.rapidz.training.viewModels.UserViewModel

enum class Error {
    PASSWORD_NOT_MET_REQUIREMENT,
    PASSWORD_MISMATCHED
}

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        val lastLoginUsername = PreferenceManager.readFromPreferences<String>(LAST_LOGIN_USERNAME)

        var isShowingErrLbl by remember { mutableStateOf(false) }
        var errType by remember { mutableStateOf(Error.PASSWORD_MISMATCHED) }
        var username by remember { mutableStateOf(lastLoginUsername) }
        var password by remember { mutableStateOf("") }
        var isBtnEnable by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(top = SPACING_20)) {
            EditText(
                hint = getString(R.string.user_username),
                value = username,
                onValueChange = {
                    username = it
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        isBtnEnable = true
                    }
                }
            )
            EditText(
                hint = getString(R.string.user_password),
                isError = isShowingErrLbl,
                isLastTextField = true,
                trailingIcon = {
                    if (isShowingErrLbl) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_error),
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                },
                onValueChange = { 
                    password = it
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        isBtnEnable = true
                    }
                }
            )
            if (isShowingErrLbl) {
                val errMsg = if (errType == Error.PASSWORD_MISMATCHED) {
                    getString(R.string.error_password_mismatch)
                } else {
                    getString(R.string.error_password_invalid)
                }
                ErrorLabel(text = errMsg)
            }
            Spacer(modifier = Modifier.weight(1f))
            LoginButton(
                text = getString(R.string.action_login).uppercase(),
                isEnabled = isBtnEnable ,
                onClickEvent = {
                    validateUserLogin(
                        username = username.trim(),
                        password = password.trim(),
                        onCreateNewUser = {
                            isShowingErrLbl = false
                            viewModel.insertUser(User(username = username.trim(), password = password.trim())) {
                                navToListScreen(username.trim())
                            }
                        },
                        onSuccessLogin = {
                            isShowingErrLbl = false
                            navToListScreen(username.trim())
                        },
                        onPasswordError = {
                            isShowingErrLbl = true
                            errType = it
                        }
                    )
                }
            )
        }
    }

    private fun navToListScreen(username: String) {
        PreferenceManager.writeToPreferences(LAST_LOGIN_USERNAME, username)
        goToNextActivity(Intent(this@LoginActivity, ListActivity::class.java))
    }

    private fun validateUserLogin(username: String,
                                  password: String,
                                  onCreateNewUser: () -> Unit,
                                  onSuccessLogin: () -> Unit,
                                  onPasswordError: (Error) -> Unit) {
        // If user exist at database, password must not be null or empty
        // Can just check if return password is null or empty
        // If return password is null or empty, mean user not exist in database
        // If returned password is not null, further check input password and returned password
        viewModel.getUserPassword(username).observe(this@LoginActivity) { userPassword ->
            if (userPassword.isNullOrEmpty()) {
                // New user here
                if (password.length > 8) onCreateNewUser() else onPasswordError(Error.PASSWORD_NOT_MET_REQUIREMENT)
            } else {
                // Not new user
                // Check password
                if (password == userPassword) onSuccessLogin() else onPasswordError(Error.PASSWORD_MISMATCHED)
            }
        }
    }
}

@Composable
fun EditText(hint: String,
             isError: Boolean = false,
             isLastTextField: Boolean = false,
             trailingIcon: @Composable (() -> Unit)? = null,
             value: String?= null,
             onValueChange: (String)-> Unit,
) {
    val text = remember { mutableStateOf("") }

    val data = if(!value.isNullOrEmpty()) value else text.value
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = data,
        onValueChange = {
            text.value = it
            onValueChange(it)
        },
        textStyle = TextStyle(fontSize = FONT_SIZE_16),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = SPACING_20,
                end = SPACING_20,
                top = SPACING_20,
                bottom = if (isLastTextField) SPACING_8 else SPACING_20
            )
            .background(color = Color_Edit_Text_Bg, shape = CircleShape),
        placeholder = {
            Text(hint, color = Color_Hint_Text)
        },
        shape = CircleShape,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color_Edit_Text_Focus_Bg,
            errorBorderColor = Color.Red,
            errorTextColor = Color.Red,
        ),
        trailingIcon = trailingIcon,
        isError = isError,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = if (isLastTextField) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { focusManager.clearFocus() }
        )
    )
}

@Composable
fun ErrorLabel(text: String) {
    Text(
        text = text,
        fontSize = FONT_SIZE_16,
        color = Color.Red,
        fontWeight = FontWeight(700),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = SPACING_36, end = SPACING_36)
    )
}

@Composable
fun LoginButton(text: String, isEnabled: Boolean = false, onClickEvent: () -> Unit) {

    val bgColor = if (isEnabled) Color_Login_Btn else Color_Login_Btn_Disable

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = SPACING_20, end = SPACING_20, bottom = SPACING_50)
            .background(color = bgColor, shape = CircleShape),
        enabled = isEnabled,
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            disabledContainerColor = Color_Login_Btn_Disable,
            disabledContentColor = Color_Edit_Text_Focus_Bg,
            contentColor = Color_Login_Btn,
            containerColor = Color_Login_Btn
        ),
        content = {
            Text(
                text = text,
                fontWeight = FontWeight(600),
                fontSize = FONT_SIZE_16,
                color = Color.White,
            )
        },
        onClick = onClickEvent
    )
}