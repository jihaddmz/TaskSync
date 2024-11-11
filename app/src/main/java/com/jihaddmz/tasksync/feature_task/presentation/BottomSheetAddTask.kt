package com.jihaddmz.tasksync.feature_task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextFieldFilled
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.feature_global.viewmodel.ViewModelMain
import com.jihaddmz.tasksync.feature_task.component.RadioGroupPriority

@Composable
fun BottomSheetAddTask(
    modifier: Modifier = Modifier,
    viewModelMain: ViewModelMain = hiltViewModel(),
    onAddClick: () -> Unit,
) {

    LaunchedEffect(key1 = viewModelMain.stateAddTask.value) {
        if (viewModelMain.stateAddTask.value != null)
            onAddClick()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        TextTitleMedium(text = "TaskSync", modifier = Modifier.align(Alignment.CenterHorizontally))

        TextTitleSmall(text = "Task", modifier = Modifier.padding(top = 30.dp))
        TextFieldFilled(
            placeholder = "Reply to the latest email",
            state = viewModelMain.taskName,
            modifier = Modifier.padding(top = 5.dp)
        ) { enteredTask ->
        }

        TextTitleSmall(text = "Priority", modifier = Modifier.padding(top = 30.dp))
        RadioGroupPriority(state = viewModelMain.priority) { selectedOption ->
        }

        TextTitleSmall(text = "Category", modifier = Modifier.padding(top = 30.dp))
        TextFieldFilled(
            placeholder = "Email",
            modifier = Modifier.padding(top = 5.dp),
            state = viewModelMain.category
        ) { enteredTask ->
        }

        ButtonFill(
            text = "Add Task", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
        ) {
            viewModelMain.addTask()
        }
    }
}