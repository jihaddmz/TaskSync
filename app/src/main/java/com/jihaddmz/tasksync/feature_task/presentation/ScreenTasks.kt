package com.jihaddmz.tasksync.feature_task.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_global.component.ButtonRectIcon
import com.jihaddmz.tasksync.feature_global.component.TextFieldFilled
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_task.component.ItemTask

@Composable
fun ScreenTasks(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModelMain: ViewModelMain = hiltViewModel(),
    onItemClick: (Int, String) -> Unit,
    onProfileClick: () -> Unit,
    onBackClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModelMain.fetchTasks()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonRectIcon(icon = Icons.AutoMirrored.Default.KeyboardArrowLeft) {
                onBackClick()
            }
            TextTitleSmall(text = "Daily Tasks")
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .clickable {
                        onProfileClick()
                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.vector_profile),
                contentDescription = ""
            )
        }

        TextFieldFilled(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), placeholder = "Search"
        ) { enteredText ->
            if (enteredText.isBlank()) {
                viewModelMain.fetchTasks()
            } else
                viewModelMain.filterTaskList(enteredText)
        }

        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(
                viewModelMain.stateListOfTasks.value?.sortedBy {
                    listOf(
                        "High",
                        "Medium",
                        "Low"
                    ).indexOf(it.priority)
                } ?: listOf(),
                key = { it.id ?: 0 }) { dtoTask ->
                ItemTask(
                    modifier = Modifier.padding(top = 10.dp),
                    task = dtoTask.taskName!!,
                    isDone = dtoTask.done!!,
                    priority = dtoTask.priority!!
                ) {
                    onItemClick(dtoTask.id!!, dtoTask.taskName)
                }
            }
        }
    }
}