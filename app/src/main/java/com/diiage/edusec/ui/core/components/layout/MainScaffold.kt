package com.diiage.edusec.ui.core.components.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.ui.core.components.navigation.BottomNavBar
import com.diiage.edusec.ui.core.theme.EduSecTheme

@Composable
fun MainScaffold(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                content(innerPadding)
            }
        }
    )
}

// Preview function for MainScaffold
@Composable
fun MainScaffoldPreviewContent() {
    val mockNavController = rememberNavController()

    MainScaffold(navController = mockNavController) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contenu de l'écran",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(showBackground = true, name = "Light - MainScaffold")
@Composable
fun MainScaffoldPreview_Light() {
    EduSecTheme {
        Surface {
            MainScaffoldPreviewContent()
        }
    }
}

@Preview(showBackground = true, name = "Dark - MainScaffold", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScaffoldPreview_Dark() {
    EduSecTheme {
        Surface {
            MainScaffoldPreviewContent()
        }
    }
}