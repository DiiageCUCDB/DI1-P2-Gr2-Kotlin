package com.diiage.edusec.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diiage.edusec.ui.components.input.PrimaryButton
import com.diiage.edusec.ui.components.input.PrimaryTextField
import com.diiage.edusec.ui.components.layout.*
import com.diiage.edusec.ui.theme.YellowDiiage

@Composable
fun LoginScreen(navController: NavController) {
    var identifier by remember { mutableStateOf("") }

    CenteredBox {
        CenteredColumn {
            // App Name
            Text(
                text = "EduSec",
                color = YellowDiiage,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            LargeSpacer()

            // Title
            Text(
                text = "Créer un compte",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            SmallSpacer()

            // Subtitle
            Text(
                text = "Entrez votre identifiant pour vous inscrire",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LargeSpacer()

            // Input Field
            PrimaryTextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = "Identifiant"
            )

            LargeSpacer()

            // Continue Button
            PrimaryButton(
                onClick = {
                    if (identifier.isNotBlank()) {
                        navController.navigate("home")
                    }
                },
                text = "Continuer",
                enabled = identifier.isNotBlank()
            )

            MediumSpacer()

            // Disclaimer
            Text(
                text = "En cliquant sur Continuer, vous acceptez nos Conditions d'utilisation et notre Politique de confidentialité.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}