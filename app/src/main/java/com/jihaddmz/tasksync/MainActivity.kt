package com.jihaddmz.tasksync

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jihaddmz.tasksync.feature_global.presentation.ScreenMain
import com.jihaddmz.tasksync.feature_auth.presentation.ScreenSignIn
import com.jihaddmz.tasksync.feature_auth.presentation.ScreenSignUp
import com.jihaddmz.tasksync.feature_auth.presentation.ScreenWelcome
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.helper.HelperSharedPreference
import com.jihaddmz.tasksync.ui.theme.TaskSyncTheme
import dagger.hilt.android.AndroidEntryPoint

var screenWidth: Int = 0
var screenHeight: Int = 0
lateinit var sharedPreferences: SharedPreferences
var stateError: MutableState<String?> = mutableStateOf(null)
var stateLoading: MutableState<Boolean> = mutableStateOf(false)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            if (screenWidth == 0) {
                screenHeight = LocalConfiguration.current.screenHeightDp
                screenWidth = LocalConfiguration.current.screenWidthDp
            }

            sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

            val navController = rememberNavController()
            val username = remember {
                mutableStateOf(HelperSharedPreference.get {
                    getString(
                        HelperSharedPreference.KEY_USERNAME,
                        ""
                    )
                })
            }

            TaskSyncTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(
                                top = 30.dp,
                                bottom = 10.dp
                            ), contentAlignment = Alignment.Center
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = if (username.value == "" || username.value == null) "welcome" else "main"
                        ) {
                            composable("welcome") {
                                BackHandler(enabled = true) {

                                }
                                ScreenWelcome(navController = navController)
                            }

                            composable("signup") {
                                BackHandler(enabled = true) {

                                }
                                ScreenSignUp(navController = navController)
                            }

                            composable("signin") {
                                BackHandler(enabled = true) {

                                }
                                ScreenSignIn(navController = navController)
                            }

                            composable("main") {
                                BackHandler(enabled = true) {

                                }
                                ScreenMain(navController = navController)
                            }
                        }

                        if (stateLoading.value)
                            CircularProgressIndicator()

                        if (stateError.value != null) {
                            AlertDialog(
                                containerColor = Color.Black,
                                onDismissRequest = {},
                                properties = DialogProperties(
                                    dismissOnBackPress = false,
                                    dismissOnClickOutside = false
                                ),
                                title = { TextTitleMedium(text = "Warning!") },
                                text = {
                                    TextBodyMedium(
                                        text = stateError.value ?: "",
                                        maxLines = 3
                                    )
                                },
                                dismissButton = {
                                    ButtonFill(text = "OK") {
                                        stateError.value = null
                                    }
                                },
                                confirmButton = {

                                })
                        }
                    }
                }
            }
        }
    }
}