package com.diiage.edusec.ui.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.diiage.edusec.ui.components.layout.CenteredBox
import com.diiage.edusec.ui.theme.YellowDiiage

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(true) {
        delay(2000) // 2 seconds splash duration
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    CenteredBox(horizontalPadding = 0.dp) {
        Text(
            text = "EduSec",
            color = YellowDiiage,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}