package com.jihaddmz.tasksync.feature_task.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.ui.theme.blue
import com.jihaddmz.tasksync.ui.theme.grey
import com.jihaddmz.tasksync.ui.theme.lightBlack
import com.jihaddmz.tasksync.ui.theme.trackNoProgressColor

@Composable
fun CardsHome(modifier: Modifier = Modifier, percentage: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        for (i in 1..3) {
            Card(
                modifier = modifier
                    .width(((LocalConfiguration.current.screenWidthDp - 100) + i * 20).dp)
                    .height(200.dp)
                    .offset(0.dp, -(i * 5 + (i * 2)).dp)
                    .padding(vertical = 30.dp),
                colors = CardDefaults.cardColors(containerColor = lightBlack),
                elevation = CardDefaults.cardElevation(defaultElevation = (i * 5 + i).dp)
            ) {
                if (i == 3) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        TextTitleMedium(modifier = Modifier, text = "Daily Progress")
                        TextBodyMedium(
                            text = "Here you can see your daily task progress",
                            color = grey,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        TextTitleSmall(
                            text = "$percentage%",
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        LinearProgressIndicator(
                            modifier = Modifier.padding(top = 5.dp),
                            progress = { (percentage).toFloat() / 100 },
                            color = blue,
                            drawStopIndicator = {},
                            gapSize = 0.dp,
                            trackColor = trackNoProgressColor,
                            strokeCap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }
}