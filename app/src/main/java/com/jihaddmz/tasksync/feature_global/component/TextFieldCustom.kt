package com.jihaddmz.tasksync.feature_global.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.ui.theme.lightBlack
import com.jihaddmz.tasksync.ui.theme.lightGrey

@Composable
fun TextFieldFilled(
    modifier: Modifier = Modifier,
    placeholder: String,
    state: MutableState<String> = remember {
        mutableStateOf("")
    },
    required: Boolean = true,
    onValueChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier.clip(RoundedCornerShape(12.dp)),
        value = state.value,
        onValueChange = {
            state.value = it
            onValueChanged(state.value)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = lightBlack,
            unfocusedContainerColor = lightBlack,
        ),
        placeholder = {
            TextBodyMedium(
                text = "$placeholder${if (required) "*" else ""}",
                color = lightGrey
            )
        },
    )
}