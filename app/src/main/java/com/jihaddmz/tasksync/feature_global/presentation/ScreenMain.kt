package com.jihaddmz.tasksync.feature_global.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_global.component.ButtonOvalIcon
import com.jihaddmz.tasksync.feature_settings.presentation.ScreenSettings
import com.jihaddmz.tasksync.feature_global.model.TypeBottomSheet
import com.jihaddmz.tasksync.feature_task.presentation.BottomSheetAddTask
import com.jihaddmz.tasksync.feature_task.presentation.BottomSheetCategories
import com.jihaddmz.tasksync.feature_task.presentation.BottomSheetTaskActions
import com.jihaddmz.tasksync.feature_task.presentation.ScreenHome
import com.jihaddmz.tasksync.feature_task.presentation.ScreenTasks
import com.jihaddmz.tasksync.ui.theme.blue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMain(
    modifier: Modifier = Modifier, navController: NavController = rememberNavController(),
    viewModelMain: ViewModelMain = hiltViewModel()
) {
    val selectedScreen = remember {
        mutableIntStateOf(1)
    }

    val bottomSheetState = rememberStandardBottomSheetState(
        skipHiddenState = false
    )

    val typeBottomSheet: MutableState<TypeBottomSheet> = remember {
        mutableStateOf(TypeBottomSheet.None)
    }
    val scope = rememberCoroutineScope()

    val alpha = animateFloatAsState(targetValue = if (selectedScreen.intValue == 1) 1.0f else 0.0f)
    val alpha1 = animateFloatAsState(targetValue = if (selectedScreen.intValue == 2) 1.0f else 0.0f)
    val alpha2 = animateFloatAsState(targetValue = if (selectedScreen.intValue == 3) 1.0f else 0.0f)

    LaunchedEffect(key1 = viewModelMain.stateUpdateTaskIsDone.value) {
        viewModelMain.fetchTasks()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.9f)
        ) {
            if (selectedScreen.intValue == 1)
                ScreenHome(
                    modifier = Modifier.alpha(alpha.value),
                    navController = navController,
                    onViewAllClick = {
                        typeBottomSheet.value = TypeBottomSheet.Categories

                    }
                ) {
                    selectedScreen.intValue = 3
                }

            if (selectedScreen.intValue == 2)
                ScreenTasks(
                    modifier = Modifier.alpha(alpha1.value),
                    navController = navController,
                    onProfileClick = {
                        selectedScreen.intValue = 3

                    },
                    onItemClick = { id, task->
                        typeBottomSheet.value = TypeBottomSheet.TaskActions(id, task)
                    }
                ) {
                    selectedScreen.intValue = 1
                }

            if (selectedScreen.intValue == 3)
                ScreenSettings(
                    modifier = Modifier.alpha(alpha2.value),
                    navController = navController
                ) { type ->
                    typeBottomSheet.value = type
                }
        }

        AnimatedVisibility(visible = typeBottomSheet.value != TypeBottomSheet.None) {
            ModalBottomSheet(onDismissRequest = {
                typeBottomSheet.value = TypeBottomSheet.None
            }, sheetState = bottomSheetState, containerColor = Color.Black, content = {
                if (typeBottomSheet.value is TypeBottomSheet.Categories) {
                    BottomSheetCategories() { categoryTitle ->
                        scope.launch {
                            bottomSheetState.hide()
                        }
                        typeBottomSheet.value = TypeBottomSheet.None
                    }
                } else if (typeBottomSheet.value is TypeBottomSheet.Add) {
                    BottomSheetAddTask {
                        scope.launch {
                            bottomSheetState.hide()
                        }
                        typeBottomSheet.value = TypeBottomSheet.None

                        if (selectedScreen.intValue == 1)
                            viewModelMain.fetchCategories()
                        else if (selectedScreen.intValue == 2) {
                            viewModelMain.fetchTasks()
                        }
                    }
                } else if (typeBottomSheet.value is TypeBottomSheet.Logout) {
                    BottomSheetConfirmation(
                        title = "Logout",
                        msg = "Are you sure you want to logout?",
                        onNoClick = {
                            scope.launch {
                                bottomSheetState.hide()
                            }
                            typeBottomSheet.value = TypeBottomSheet.None
                        },
                        onYesClick = {

                            viewModelMain.signOut()

                            scope.launch {
                                bottomSheetState.hide()
                            }
                            typeBottomSheet.value = TypeBottomSheet.None

                            navController.navigate("welcome") {
                                // Pop up to the start destination and clear all screens
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                // Avoid multiple copies of the same destination on the stack
                                launchSingleTop = true
                            }
                        })
                } else if (typeBottomSheet.value is TypeBottomSheet.Username) {
                    BottomSheetTextField(
                        msg = "Enter a new username",
                        placeholder = "Username"
                    ) { enteredUsername ->

                    }
                } else if (typeBottomSheet.value is TypeBottomSheet.Password) {
                    BottomSheetTextField(
                        msg = "Enter a new password",
                        placeholder = "Password"
                    ) { enteredPassword ->

                    }
                } else if (typeBottomSheet.value is TypeBottomSheet.TaskActions) {
                    val typeActionsBottomSheet = (typeBottomSheet.value as TypeBottomSheet.TaskActions)
                    BottomSheetTaskActions(
                        id = (typeBottomSheet.value as TypeBottomSheet.TaskActions).id,
                        text = (typeBottomSheet.value as TypeBottomSheet.TaskActions).text,
                        onDelete = {}) {
                        viewModelMain.updateTaskDone(typeActionsBottomSheet.id, true)

                        scope.launch {
                            bottomSheetState.hide()
                        }
                        typeBottomSheet.value = TypeBottomSheet.None
                    }
                }
            })

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.11f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = {
                        selectedScreen.intValue = 1
                    }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    AnimatedVisibility(visible = selectedScreen.intValue == 1) {

                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(5.dp),
                            colors = CardDefaults.cardColors(containerColor = blue)
                        ) {

                        }
                    }
                }

                if (selectedScreen.intValue != 3)
                    ButtonOvalIcon(icon = Icons.Default.Add) {
                        typeBottomSheet.value = TypeBottomSheet.Add
                    }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    IconButton(onClick = {
                        selectedScreen.intValue = 2
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Task,
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }


                    AnimatedVisibility(visible = selectedScreen.intValue == 2) {
                        Card(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(5.dp),
                            colors = CardDefaults.cardColors(containerColor = blue)
                        ) {

                        }
                    }
                }
            }
        }
    }
}