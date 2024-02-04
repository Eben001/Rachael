package com.gana.ebenezer.rachael.anim

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gana.ebenezer.rachael.R
import com.gana.ebenezer.rachael.ui.theme.Purple80
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun WavesAnimation(
    onStartRecord: () -> Unit,
    onStopRecord: () -> Unit
) {
    var isAnimating by remember { mutableStateOf(false) }

    val waves = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )

    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(2000, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Restart,
    )

    if (isAnimating) {
        waves.forEachIndexed { index, animatable ->
            LaunchedEffect(animatable) {
                delay(index * 500L)
                animatable.animateTo(
                    targetValue = 1f, animationSpec = animationSpec
                )
            }
        }
    } else {
        waves.forEach { wave ->
            LaunchedEffect(Unit) {
                withContext(Dispatchers.Default) {
                    wave.snapTo(0f)
                }
            }
        }
    }

    val dys = waves.map { it.value }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        // Waves
        dys.forEach { dy ->
            Box(
                Modifier
                    .size(50.dp)
                    .align(Center)
                    .graphicsLayer {
                        scaleX = dy * 7 + 1
                        scaleY = dy * 7 + 1
                        alpha = 1 - dy
                    },
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(color = Color.White, shape = CircleShape)
                )
            }
        }

        // Mic icon
        Box(
            Modifier
                .size(200.dp)
                .align(Center)
                .background(color = Color.White, shape = CircleShape)
                .clickable {
                    isAnimating = !isAnimating
                }
        ) {
            Icon(
                painter = if (isAnimating) painterResource(id = R.drawable.ic_stop)
                else painterResource(id = R.drawable.ic_mic),
                "",
                tint = Color.Black,
                modifier = Modifier
                    .size(128.dp)
                    .align(Center)
            )
        }

        LaunchedEffect(key1 = isAnimating) {
            if (isAnimating) {
                onStartRecord()
            } else {
                onStopRecord()
            }
        }
    }
}


@Preview
@Composable
fun PreviewWaveAnimation() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Purple80
    ) {
        WavesAnimation(onStartRecord = { /*TODO*/ }) {

        }
    }
}
