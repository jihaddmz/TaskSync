package com.jihaddmz.tasksync.feature_auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.ButtonOutlined
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleLarge
import com.jihaddmz.tasksync.ui.theme.grey

@Composable
fun ScreenWelcome(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                painter = painterResource(id = R.drawable.img_welcome),
                contentDescription = "welcome screen image"
            )

            TextTitleLarge(
                modifier = Modifier,
                maxLines = 2,
                text = "The only productivity app you need"
            )

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column {

                    ButtonFill(
                        modifier = Modifier
                            .fillMaxWidth(), text = "Sign in", paddingValues = 10.dp
                    ) {
                        navController.navigate("signin")

                    }

                    ButtonOutlined(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        text = "Sign Up",
                        paddingValues = 10.dp
                    ) {
                        navController.navigate("signup")
                    }

                    TextBodyMedium(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        text = "By Continuing you agree to the Terms and Conditions",
                        color = grey
                    )
                }
            }
        }
    }
}