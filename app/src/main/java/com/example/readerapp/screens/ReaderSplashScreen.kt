package com.example.readerapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.readerapp.components.ReaderLogo
import com.example.readerapp.navigation.ReaderScreens
import kotlinx.coroutines.delay

@Composable
fun ReaderSplashScreen(navController: NavHostController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(1000)
        navController.navigate(ReaderScreens.LoginScreen.name)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .scale(scale.value)
                .size(400.dp)
                .padding(20.dp)
                .border(shape = CircleShape, width = 2.dp, color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ReaderLogo()
            Spacer(modifier = Modifier.padding(15.dp))
            Text(
                text = "Read. Change. Yourself",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.LightGray)
            )
        }
    }
//    DisposableEffect(keys = ){}

}