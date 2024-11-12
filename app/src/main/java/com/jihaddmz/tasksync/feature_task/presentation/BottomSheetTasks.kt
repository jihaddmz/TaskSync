package com.jihaddmz.tasksync.feature_task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_task.component.CardCategory
import com.jihaddmz.tasksync.feature_task.component.ItemTask

@Composable
fun BottomSheetTasks(
    modifier: Modifier = Modifier,
    listOfTasks: List<DtoCategory.Task?>,
    onItemClick: (Int, String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {
        TextTitleMedium(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Tasks"
        )

        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(
                listOfTasks.sortedBy {
                    listOf(
                        "High",
                        "Medium",
                        "Low"
                    ).indexOf(it?.priority)
                },
                key = { it?.id ?: 0 }) { dtoTask ->
                ItemTask(
                    modifier = Modifier.padding(top = 10.dp),
                    task = dtoTask?.taskName!!,
                    isDone = dtoTask.done!!,
                    priority = dtoTask.priority!!
                ) {
                    onItemClick(dtoTask.id!!, dtoTask.taskName)
                }
            }
        }

    }
}