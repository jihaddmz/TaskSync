package com.jihaddmz.tasksync.feature_global.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jihaddmz.tasksync.ui.theme.blue
import com.jihaddmz.tasksync.ui.theme.darkGrey

@Composable
fun ButtonsChooser(
    modifier: Modifier = Modifier, list: List<String>,
    onSelectedOption: (String) -> Unit
) {
    val selectedOption = remember {
        mutableStateOf(list[0])
    }
    Row(modifier = modifier.fillMaxWidth()) {
        list.forEach {
            ButtonFill(
                modifier = Modifier.weight(1f),
                text = it,
                color = if (selectedOption.value == it) blue else Color.Black,
                textColor = if (selectedOption.value == it) Color.White else darkGrey
            ) {
                selectedOption.value = it
                onSelectedOption(selectedOption.value)
            }
        }
    }
}