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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_global.component.ButtonsChooser
import com.jihaddmz.tasksync.feature_global.component.TextBodyLarge
import com.jihaddmz.tasksync.feature_global.component.TextTitleLarge
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.feature_global.helper.HelperSharedPreference
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.DtoTask
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_task.component.CardCategory
import com.jihaddmz.tasksync.feature_task.component.CardsHome

@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModelMain: ViewModelMain = hiltViewModel(),
    onItemClick: (List<DtoCategory.Task?>) -> Unit,
    onViewAllClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModelMain.fetchCategories()
    }

    Column {
        Column(
            modifier = modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextTitleSmall(text = "Home")
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

            TextTitleLarge(
                modifier = Modifier.padding(top = 20.dp),
                text = "Hello \n${viewModelMain.username.value}",
                maxLines = 2
            )

//            ButtonsChooser(
//                modifier = Modifier.padding(top = 20.dp),
//                listOf("Overview", "Favorites")
//            ) {
//
//            }

            CardsHome(modifier = Modifier.padding(top = 5.dp), percentage = viewModelMain.calculatePercentage())

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextTitleMedium(text = "Categories")
                TextBodyLarge(modifier = Modifier.clickable {
                    onViewAllClick()
                }, text = "View All")
            }
            LazyVerticalGrid(
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
                        ).clickable {
                            onItemClick(dtoCategory.tasks ?: listOf())
                        },
                        categoryTitle = dtoCategory.title!!,
                        totalTasks = dtoCategory.tasks!!.size,
                        doneTasks = dtoCategory.tasks.filter { it!!.done == true }.size,
                        priority = dtoCategory.tasks.filter { it!!.done == false }.groupingBy { it?.priority }.eachCount().maxByOrNull { it.value }?.key ?: "Low"
                    )

                }
            }

        }

    }
}