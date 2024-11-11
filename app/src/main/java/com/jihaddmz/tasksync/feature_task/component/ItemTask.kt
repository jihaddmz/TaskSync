package com.jihaddmz.tasksync.feature_task.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.feature_global.component.ButtonRectIcon
import com.jihaddmz.tasksync.feature_global.component.TextTitleSmall
import com.jihaddmz.tasksync.ui.theme.green
import com.jihaddmz.tasksync.ui.theme.lightBlack
import com.jihaddmz.tasksync.ui.theme.orange

@Composable
fun ItemTask(
    modifier: Modifier = Modifier,
    task: String,
    isDone: Boolean,
    priority: String,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = lightBlack),
        border =  if (isDone) BorderStroke(1.dp, color = green) else BorderStroke(0.dp, color = Color.Transparent),
        shape = RoundedCornerShape(
            15.dp
        ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonRectIcon(modifier = Modifier.weight(0.2f), icon = Icons.Default.TaskAlt, color = when (priority.lowercase()) {
                "high" -> Color.Red
                "medium" -> orange
                else -> green
            }) {

            }

            TextTitleSmall(modifier = Modifier.weight(0.6f).padding(start = 20.dp), text = task)

            Box(modifier = Modifier.weight(0.2f).fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(onClick = {
                    onClick()
                }) {
                    Icon(
                        modifier = Modifier,
                        tint = Color.White,
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = ""
                    )
                }

            }
        }
    }

}