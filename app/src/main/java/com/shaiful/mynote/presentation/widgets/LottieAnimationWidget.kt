package com.shaiful.mynote.presentation.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationWidget(modifier: Modifier = Modifier, animationFileName: String, isPlaying: Boolean = true) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(animationFileName))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
}