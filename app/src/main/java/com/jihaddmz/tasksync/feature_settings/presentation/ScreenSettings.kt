package com.jihaddmz.tasksync.feature_settings.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jihaddmz.tasksync.R
import com.jihaddmz.tasksync.feature_global.component.ButtonFill
import com.jihaddmz.tasksync.feature_global.component.TextBodyLarge
import com.jihaddmz.tasksync.feature_global.component.TextBodyMedium
import com.jihaddmz.tasksync.feature_global.component.TextTitleLarge
import com.jihaddmz.tasksync.feature_global.model.TypeBottomSheet
import com.jihaddmz.tasksync.ui.theme.darkGrey
import com.jihaddmz.tasksync.ui.theme.lightBlack

@Composable
fun ScreenSettings(modifier: Modifier = Modifier, navController: NavController, onButtonClick: (TypeBottomSheet) -> Unit) {

    val (imageUri, setImageUri) = remember { mutableStateOf<String?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            // Handle the selected image URI here
            setImageUri(uri?.toString())
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable {
                    imagePickerLauncher.launch("image/*")
                }
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            painter = if (imageUri == null) painterResource(id = R.drawable.vector_profile) else rememberAsyncImagePainter(
                model = imageUri
            ),
            contentDescription = "",
        )

        TextTitleLarge(
            text = "Jihad Mahfouz",
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

                TextBodyMedium(modifier = Modifier, text = "Username", color = darkGrey)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = lightBlack),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextBodyLarge(text = "Change username")
                        ButtonFill(text = "Change") {
                            onButtonClick(TypeBottomSheet.Username)
                        }
                    }
                }

                TextBodyMedium(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Password",
                    color = darkGrey
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    colors = CardDefaults.cardColors(containerColor = lightBlack),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextBodyLarge(text = "Change password")
                        ButtonFill(text = "Change") {
                            onButtonClick(TypeBottomSheet.Password)
                        }
                    }
                }

                ButtonFill(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    text = "Log out",
                    color = Color.Black,
                    textColor = darkGrey
                ) {
                    onButtonClick(TypeBottomSheet.Logout)
                }
            }
        }
    }
}