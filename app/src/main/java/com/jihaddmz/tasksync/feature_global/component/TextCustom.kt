package com.jihaddmz.tasksync.feature_global.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.jihaddmz.tasksync.ui.theme.Typography

@Composable
fun TextTitleLarge(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.headlineLarge, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextTitle(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.titleLarge, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextTitleMedium(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.titleMedium, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextTitleSmall(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.titleSmall, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextBodyLarge(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.bodyLarge, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextBodyMedium(modifier: Modifier = Modifier, text: String, color: Color = Color.White, maxLines: Int = 1) {
    Text(modifier = modifier, text = text, style = Typography.bodyMedium, color = color, maxLines = maxLines, overflow = TextOverflow.Ellipsis)
}