package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PauseCircleOutline
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Restore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaiful.mynote.presentation.utility_widgets.VerticalSpace
import com.shaiful.mynote.presentation.viewmodels.StopwatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchScreen(
    stopwatchViewModel: StopwatchViewModel = hiltViewModel()
) {

    val time by stopwatchViewModel.time.collectAsState()
    val isRunning by stopwatchViewModel.isRunning.collectAsState()

    val formattedTime = stopwatchViewModel.formatTime(time)



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Stopwatch")
                }
            )
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
            Box (
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = formattedTime, style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Thin))
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
                    imageVector = Icons.Outlined.PlayArrow,
                    modifier = Modifier
                        .clickable {
                            stopwatchViewModel.start()
                        }
                        .padding(vertical = 16.dp)
                        .weight(1f),
                )
                CustomIconButton(
                    title = "Pause",
                    imageVector = Icons.Outlined.PauseCircleOutline,
                    modifier = Modifier
                        .clickable {
                            stopwatchViewModel.pause()
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
            Icon(imageVector = imageVector, contentDescription = "")
            VerticalSpace(height = 16)
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}