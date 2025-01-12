package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PauseCircleOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.PauseCircleOutline
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.StopwatchViewModel
import com.shaiful.mynote.presentation.widgets.AppBar
import com.shaiful.mynote.presentation.widgets.LottieAnimationWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(
    navController: NavController,
    stopwatchViewModel: StopwatchViewModel = hiltViewModel()
) {

    val time by stopwatchViewModel.time.collectAsState()
    val isRunning by stopwatchViewModel.isRunning.collectAsState()

    val formattedTime = stopwatchViewModel.formatTime(time)

    var playAnimation by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            AppBar(title = "Stopwatch", navController = navController)
        }
    ) { contentPadding ->


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            // Display the formatted time
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = formattedTime,
                    style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Thin)
                )
                VerticalSpace(height = 16)
                LottieAnimationWidget(
                    animationFileName = "beat.json",
                    isPlaying = playAnimation,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Control buttons
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                CustomIconButton(
                    title = "Start",
                    imageVector = Icons.Default.PlayArrow,
                    modifier = Modifier
                        .clickable {
                            stopwatchViewModel.start()
                            playAnimation = true
                        }
                        .padding(vertical = 16.dp)
                        .weight(1f),
                )
                CustomIconButton(
                    title = "Pause",
                    imageVector = Icons.Default.PauseCircleOutline,
                    modifier = Modifier
                        .clickable {
                            stopwatchViewModel.pause()
                            playAnimation = false
                        }
                        .padding(vertical = 16.dp)
                        .weight(1f),
                )
                CustomIconButton(
                    title = "Restart",
                    imageVector = Icons.Outlined.Restore,
                    modifier = Modifier
                        .clickable {
                            stopwatchViewModel.restart()
                            playAnimation = false
                        }
                        .padding(vertical = 16.dp)
                        .weight(1f),
                )
            }
        }
    }
}

@Composable
private fun CustomIconButton(modifier: Modifier, title: String, imageVector: ImageVector) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = imageVector, contentDescription = "",
                modifier = Modifier.size(32.dp),
            )
            VerticalSpace(height = 16)
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}