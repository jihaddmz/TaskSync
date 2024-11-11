package com.jihaddmz.tasksync.feature_task.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.ui.theme.darkGrey
import com.jihaddmz.tasksync.ui.theme.green
import com.jihaddmz.tasksync.ui.theme.orange

@Composable
fun RadioGroupPriority(modifier: Modifier = Modifier, state: MutableState<String>, onOptionSelected: (String) -> Unit) {
    // State to track selected option
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        // Option 1
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.value == "High",
                onClick = {
                    state.value = "High"
                    onOptionSelected(state.value)
                },
                colors = RadioButtonDefaults.colors(selectedColor = Color.Red)
            )
            TextBodyMedium(Modifier, "High", color = darkGrey)
        }

        // Option 2
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.value == "Medium",
                onClick = {
                    state.value = "Medium"
                    onOptionSelected(state.value)
                },
                colors = RadioButtonDefaults.colors(selectedColor = orange)
            )
            TextBodyMedium(Modifier, "Medium", color = darkGrey)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.value == "Low",
                onClick = {
                    state.value = "Low"
                    onOptionSelected(state.value)
                },
                colors = RadioButtonDefaults.colors(selectedColor = green)
            )
            TextBodyMedium(Modifier, "Low", color = darkGrey)
        }

    }
}