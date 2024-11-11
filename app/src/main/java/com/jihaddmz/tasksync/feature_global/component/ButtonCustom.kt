package com.jihaddmz.tasksync.feature_global.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jihaddmz.tasksync.ui.theme.Typography
import com.jihaddmz.tasksync.ui.theme.blue
import com.jihaddmz.tasksync.ui.theme.darkGrey

@Composable
fun ButtonFill(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = blue,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    paddingValues: Dp = 0.dp,
    isLoading: Boolean = false,
    onclick: () -> Unit
) {
    require(paddingValues in 0.dp..10.dp)

    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = { onclick() }) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(20.dp),
            )
        } else
            Text(
                modifier = Modifier.padding(vertical = paddingValues),
                text = text,
                style = Typography.titleSmall,
                color = textColor
            )
    }
}

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    text: String,
    paddingValues: Dp = 0.dp,
    onclick: () -> Unit
) {
    require(paddingValues in 0.dp..10.dp)

    OutlinedButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
        ),
        border = BorderStroke(width = 1.dp, color = darkGrey),
        onClick = { onclick() }) {
        Text(
            modifier = Modifier.padding(vertical = paddingValues),
            text = text,
            style = Typography.titleSmall,
            color = Color.White
        )
    }
}

@Composable
fun ButtonOvalIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color = blue,
    onclick: () -> Unit
) {
    IconButton(modifier = modifier, colors = IconButtonDefaults.iconButtonColors(
        containerColor = color,
    ), onClick = { onclick() }) {
        Icon(imageVector = icon, tint = Color.White, contentDescription = "")
    }
}

@Composable
fun ButtonRectIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    bitmap: ImageBitmap? = null,
    color: Color = blue,
    onclick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = { onclick() }) {
        if (bitmap != null)
            Icon(bitmap = bitmap, contentDescription = "", tint = Color.White)
        else
            Icon(
                modifier = Modifier,
                imageVector = icon!!,
                tint = Color.White,
                contentDescription = ""
            )
    }
}