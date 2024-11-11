package com.jihaddmz.tasksync.feature_task.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyLarge

@Composable
fun BottomSheetTaskActions(
    modifier: Modifier = Modifier,
    text: String = "Hello there",
    id: Int,
    onDelete: (Int) -> Unit,
    onDoneClick: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        TextBodyLarge(modifier = Modifier.align(Alignment.CenterHorizontally), text = text)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonFill(
                text = "Delete", modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                onDelete(id)
            }

            ButtonFill(
                text = "Mark as Done",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                onDoneClick(id)
            }
        }
    }
}