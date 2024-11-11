package com.jihaddmz.tasksync.feature_global.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium

@Composable
fun BottomSheetConfirmation(
    modifier: Modifier = Modifier,
    title: String,
    msg: String,
    onNoClick: () -> Unit,
    onYesClick: () -> Unit
) {

    Column(modifier = modifier.fillMaxSize().padding(horizontal = 20.dp)) {
        TextTitleMedium(text = title, modifier = Modifier.align(Alignment.CenterHorizontally))

        TextBodyMedium(text = msg, modifier = Modifier
            .padding(top = 20.dp)
            .align(Alignment.CenterHorizontally))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp), horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
            ButtonFill(text = "No", color = Color.Black, modifier = Modifier.weight(0.3f)) {
                onNoClick()
            }

            Spacer(modifier = Modifier.width(50.dp))

            ButtonFill(text = "Yes", modifier = Modifier.weight(0.3f)) {
                onYesClick()
            }
        }
    }

}