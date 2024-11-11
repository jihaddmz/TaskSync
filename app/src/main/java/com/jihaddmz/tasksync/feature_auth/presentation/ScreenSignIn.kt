package com.jihaddmz.tasksync.feature_auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_auth.viewmodel.ViewModelAuth
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextFieldFilled
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall

@Composable
fun ScreenSignIn(
    modifier: Modifier = Modifier, navController: NavController,
    viewModelAuth: ViewModelAuth = hiltViewModel(),
) {

    LaunchedEffect(key1 = viewModelAuth.stateSignIn.value) {
        if (viewModelAuth.stateSignIn.value != null) { // sign in not working
            viewModelAuth.saveDetailsInSharedPref().also {
                navController.navigate("main")
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            painter = painterResource(id = R.drawable.img_signup),
            contentDescription = "signup image"
        )

        TextTitleMedium(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = "Welcome to TaskSync!"
        )
        TextBodyMedium(
            modifier = Modifier
                .padding(top = 5.dp)
                .align(Alignment.CenterHorizontally),
            text = "Boost your productivity with TaskSync"
        )

        TextFieldFilled(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(),
            state = viewModelAuth.stateUsername,
            placeholder = "Username"
        ) {

        }

        TextFieldFilled(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            state = viewModelAuth.statePassword,
            placeholder = "Password"
        ) {

        }

        ButtonFill(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            enabled = viewModelAuth.isSignInButtonEnabled(),
            text = "Sign in"
        ) {
            viewModelAuth.signIn()
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
            TextBodyMedium(modifier = Modifier
                .clickable {
                    navController.navigate("signup")
                }
                .wrapContentWidth(), text = "New user? Sign up")
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            TextTitleSmall(
                text = "Welcome OnBoard!",
                modifier = Modifier.padding(bottom = 50.dp)
            )

        }
    }
}