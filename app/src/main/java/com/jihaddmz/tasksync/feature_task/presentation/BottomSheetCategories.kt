package com.jihaddmz.tasksync.feature_task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_task.component.CardCategory

@Composable
fun BottomSheetCategories(
    modifier: Modifier = Modifier,
    viewModelMain: ViewModelMain = hiltViewModel(),
    onCategoryClick: (String) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModelMain.fetchCategories()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {
        TextTitleMedium(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Categories"
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp),
            userScrollEnabled = true,
            columns = GridCells.Fixed(2), // Set to 2 columns
        ) {
            items(
                viewModelMain.stateListOfCategories.value ?: listOf(),
                key = { it.id ?: 0 }) { dtoCategory ->

                CardCategory(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 8.dp
                    ),
                    categoryTitle = dtoCategory.title!!,
                    totalTasks = dtoCategory.tasks!!.size,
                    doneTasks = dtoCategory.tasks.filter { it!!.done == true }.size,
                    priority = dtoCategory.tasks.filter { it!!.done == false }.groupingBy { it?.priority }.eachCount().maxByOrNull { it.value }?.key ?: "Low"
                )

            }
        }

    }
}