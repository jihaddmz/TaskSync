package com.jihaddmz.tasksync.feature_global.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyLarge
import com.jihaddmz.tasksync.feature_global.component.TextFieldFilled

@Composable
fun BottomSheetTextField(
    modifier: Modifier = Modifier,
    msg: String,
    placeholder: String,
    onSaveClick: (String) -> Unit
) {
    var enteredText = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        TextBodyLarge(text = msg, modifier = Modifier.align(Alignment.CenterHorizontally))

        TextFieldFilled(
            placeholder = placeholder,
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            enteredText = it
        }

        ButtonFill(text = "Save",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            onSaveClick(enteredText)
        }
    }

}