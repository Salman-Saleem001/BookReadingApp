package com.example.readerapp.screens.auth

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.readerapp.R
import com.example.readerapp.components.CustomTextField
import com.example.readerapp.components.ReaderLogo
import com.example.readerapp.navigation.ReaderScreens

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 200.dp)
    ) {
        ReaderLogo()
        Spacer(modifier = Modifier.height(50.dp))
        val keyboardController = LocalSoftwareKeyboardController.current
        var email by rememberSaveable {
            mutableStateOf("")
        }
        CustomTextField(
            email = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "Enter your email",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Log.d("LoginScreen", "LoginScreen: $email")
                })

        )
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var isPasswordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        CustomTextField(
            email = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Enter your password",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                if (email.trim().isEmpty() && password.trim().isEmpty()) return@KeyboardActions
                keyboardController?.hide()
                Log.d("LoginScreen", "Password: $password")
            }),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        ) {
            Icon(
                painter = if (!isPasswordVisible) painterResource(R.drawable.outline_visibility_24) else painterResource(
                    R.drawable.outline_visibility_off_24
                ),
                contentDescription = "Password Icon.",
                modifier = Modifier.clickable {
                    isPasswordVisible = !isPasswordVisible
                },
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        var isLogin by rememberSaveable {
            mutableStateOf(true)
        }
        val loading by viewModel.loading.collectAsState()
        Button(
            onClick = {
                Log.d("LoginScreen", "OnTap: $email $password")
                if (isLogin) {
                    viewModel.signInWithEmailAndPassword(email, password) {
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                        email = ""
                        password = ""
                    }
                } else {
                    viewModel.createUserWithEmailAndPassword(email, password) {
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                        email = ""
                        password = ""
                    }
                }
            },
            enabled = !loading && email.isNotEmpty() && password.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp

                )
            } else {
                Text(
                    text = if (isLogin) "Login" else "Create Account",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "New User? ")
            Text(
                text = if (isLogin) "Create Account" else "Login",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable {
                    isLogin = !isLogin
                })
        }

    }
}