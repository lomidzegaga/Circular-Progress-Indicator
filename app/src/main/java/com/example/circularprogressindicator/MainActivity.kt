package com.example.circularprogressindicator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.circularprogressindicator.ui.theme.CircularProgressIndicatorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CircularProgressIndicator(
    modifier: Modifier = Modifier,
    duration: String = "3s",
    startAnimation: Boolean = true,
    circleRadius: Int = 50,
    circleColor: Color = Color.Yellow,
    startAngel: Float = 270f,
    strokeWidth: Int = 100,
    delay: Long = 0,
    onAnimationEnd: (() -> Unit)? = null
) {
    val totalDurationSeconds = remember(duration) { parseDuration(duration) }
    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(startAnimation) {
        if (startAnimation) {
            delay(delay)
            progress = 0f
            progress = 360f
        } else {
            progress = 0f
        }
    }

    val circleProgress = animateFloatAsState(
        targetValue = progress,
        label = "ProgressAnimation",
        animationSpec = tween(
            durationMillis = (totalDurationSeconds * 1000).toInt(),
            easing = LinearEasing
        ),
        finishedListener = {
            if (progress == 360f) onAnimationEnd?.invoke()
        }
    )

    Canvas(
        modifier = modifier
            .size((circleRadius * 2).dp)
            .clip(CircleShape)
    ) {
        drawArc(
            color = circleColor,
            startAngle = startAngel,
            sweepAngle = circleProgress.value,
            useCenter = false,
            style = Stroke(width = strokeWidth.toFloat())
        )
    }
}

fun parseDuration(duration: String): Long {
    val regex = Regex("""(\d+)([smhd])""")
    val match =
        requireNotNull(regex.matchEntire(input = duration.trim())) { "Invalid duration format. Use format like '2s', '1m', '3h', or '4d'." }

    val (value, unit) = match.destructured
    val number = value.toLong()

    return when (unit) {
        "s" -> number
        "m" -> number * 60
        "h" -> number * 3600
        "d" -> number * 86400
        else -> throw IllegalArgumentException("Unknown time unit: $unit")
    }
}