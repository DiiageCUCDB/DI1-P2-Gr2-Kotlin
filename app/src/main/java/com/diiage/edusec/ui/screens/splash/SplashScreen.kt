package com.diiage.edusec.ui.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.diiage.edusec.ui.core.components.Screen
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.core.theme.YellowDiiage
import androidx.compose.runtime.Composable

@Composable
fun SplashScreen(navController: NavController) {
    Screen(
        viewModel = viewModel<SplashViewModel>(),
        navController = navController
    ) { _, _ ->
        Content()
    }
}

@Composable
private fun Content() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "EduSec",
            color = YellowDiiage,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

// Preview-only version without navigation
@Composable
private fun SplashScreenPreviewContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "EduSec",
            color = YellowDiiage,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, name = "Light - Splash Screen")
@Composable
fun SplashScreenPreview() {
    EduSecTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SplashScreenPreviewContent()
        }
    }
}

@Preview(showBackground = true, name = "Dark - Splash Screen", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    EduSecTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SplashScreenPreviewContent()
        }
    }
}