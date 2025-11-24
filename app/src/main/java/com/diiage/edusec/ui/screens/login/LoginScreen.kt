package com.diiage.edusec.ui.screens.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.domain.usecase.LoginService
import com.diiage.edusec.ui.core.components.input.PrimaryButton
import com.diiage.edusec.ui.core.components.input.PrimaryTextField
import com.diiage.edusec.ui.core.components.layout.CenteredBox
import com.diiage.edusec.ui.core.components.layout.CenteredColumn
import com.diiage.edusec.ui.core.components.layout.LargeSpacer
import com.diiage.edusec.ui.core.components.layout.MediumSpacer
import com.diiage.edusec.ui.core.components.layout.SmallSpacer
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.core.theme.YellowDiiage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var identifier by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val loginService = remember {
        LoginService()
    }

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

            // Error message
            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                SmallSpacer()
            }

            // Input Field
            PrimaryTextField(
                value = identifier,
                onValueChange = {
                    identifier = it
                    errorMessage = null // Clear error when user types
                },
                label = "Identifiant",
                enabled = !isLoading
            )

            LargeSpacer()

            // Continue Button
            PrimaryButton(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        errorMessage = null

                        // Validate locally first
                        val validationError = loginService.getErrorMessage(identifier)
                        if (validationError != null) {
                            errorMessage = validationError
                            isLoading = false
                            return@launch
                        }

                        // Call API
                        val result = loginService.login(identifier)
                        isLoading = false

                        result.onSuccess { response ->
                            navController.navigate("home")
                        }.onFailure { error ->
                            errorMessage = error.message ?: "Erreur de connexion"
                        }
                    }
                },
                text = if (isLoading) "Connexion..." else "Continuer",
                enabled = loginService.validateIdentifier(identifier) && !isLoading,
                isLoading = isLoading
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

@Preview(showBackground = true, name = "Light - Empty Form")
@Composable
fun LoginScreenPreview_Empty() {
    val navController = rememberNavController()

    EduSecTheme {
        Surface {
            LoginScreen(navController)
        }
    }
}

@Preview(showBackground = true, name = "Dark - Button Enabled", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenDarkPreview_ButtonEnabled() {
    val navController = rememberNavController()

    EduSecTheme {
        Surface {
            LoginScreen(navController)
        }
    }
}