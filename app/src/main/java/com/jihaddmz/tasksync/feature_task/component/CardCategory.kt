package com.jihaddmz.tasksync.feature_task.component

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.ButtonRectIcon
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.ui.theme.blue
import com.jihaddmz.tasksync.ui.theme.darkGrey
import com.jihaddmz.tasksync.ui.theme.green
import com.jihaddmz.tasksync.ui.theme.grey
import com.jihaddmz.tasksync.ui.theme.lightBlack
import com.jihaddmz.tasksync.ui.theme.orange

@Composable
fun CardCategory(
    modifier: Modifier = Modifier,
    categoryTitle: String,
    totalTasks: Int,
    doneTasks: Int,
    priority: String
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(containerColor = lightBlack),
        elevation = CardDefaults.cardElevation()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            ButtonRectIcon(
                icon = Icons.Default.Checklist, color = when (priority.lowercase()) {
                    "high" -> Color.Red
                    "medium" -> orange
                    else -> green
                }
            ) {

            }

            TextBodyMedium(
                modifier = Modifier.padding(top = 5.dp),
                text = "${totalTasks - doneTasks} left",
                color = grey
            )

            TextTitleSmall(text = categoryTitle, modifier = Modifier.padding(top = 5.dp))
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        strokeCap = StrokeCap.Round,
                        trackColor = darkGrey,
                        color = blue,
                        gapSize = 0.dp,
                        drawStopIndicator = {},
                        progress = { (doneTasks.toFloat() * 1.0 / totalTasks.toFloat()).toFloat() },
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .weight(0.5f)
                    )

                    ButtonFill(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(start = 10.dp),
                        text = "$doneTasks/$totalTasks"
                    ) {

                    }

                }
            }
        }
    }
}