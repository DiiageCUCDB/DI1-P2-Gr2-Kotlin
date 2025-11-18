package com.diiage.edusec.ui.login

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
import com.diiage.edusec.data.api.LoginApiService
import com.diiage.edusec.data.repository.LoginRepository
import com.diiage.edusec.domain.model.LoginRequest
import com.diiage.edusec.domain.model.LoginResponse
import com.diiage.edusec.service.bl.LoginService
import com.diiage.edusec.ui.components.input.PrimaryButton
import com.diiage.edusec.ui.components.input.PrimaryTextField
import com.diiage.edusec.ui.components.layout.*
import com.diiage.edusec.ui.theme.EduSecTheme
import com.diiage.edusec.ui.theme.YellowDiiage
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var identifier by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // In a real app, you'd get this from dependency injection
    val loginService = remember {
        // For demo purposes - in real app use dependency injection
        val mockApiService = createMockApiService()
        val repository = LoginRepository(mockApiService)
        LoginService(repository)
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

// Mock API service for preview/demo
private fun createMockApiService(): LoginApiService {
    return object : LoginApiService {
        override suspend fun login(loginRequest: LoginRequest): LoginResponse {
            // Simulate API call delay
            kotlinx.coroutines.delay(1000)
            return if (loginRequest.identifier == "error") {
                LoginResponse(success = false, error = "Identifiant invalide")
            } else {
                LoginResponse(success = true, userId = "user_${loginRequest.identifier}")
            }
        }
    }
}

// Preview-only version without navigation
@Composable
fun LoginScreenPreviewContent() {
    var identifier by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val loginService = remember {
        val mockApiService = createMockApiService()
        val repository = LoginRepository(mockApiService)
        LoginService(repository)
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
                onValueChange = { identifier = it },
                label = "Identifiant"
            )

            LargeSpacer()

            // Continue Button
            PrimaryButton(
                onClick = { /* Do nothing in preview */ },
                text = "Continuer",
                enabled = loginService.validateIdentifier(identifier)
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
    EduSecTheme {
        Surface {
            LoginScreenPreviewContent()
        }
    }
}

@Preview(showBackground = true, name = "Dark - Button Enabled", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenDarkPreview_ButtonEnabled() {
    EduSecTheme {
        Surface {
            LoginScreenPreviewContent()
        }
    }
}