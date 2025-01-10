package com.shaiful.mynote.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(contentPadding)
        ) {
                      // Display the formatted time
            Text(text = formattedTime)

            // Control buttons
            Row {
                Button(onClick = { stopwatchViewModel.start() }) {
                    Text("Start")
                }
                Button(onClick = { stopwatchViewModel.pause() }) {
                    Text("Pause")
                }
                Button(onClick = { stopwatchViewModel.resume() }) {
                    Text("Resume")
                }
                Button(onClick = { stopwatchViewModel.restart() }) {
                    Text("Restart")
                }
            }
        }
    }
}