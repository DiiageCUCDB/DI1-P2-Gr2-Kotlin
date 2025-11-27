package com.diiage.edusec.ui.core.components.input

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.core.theme.EduSecTheme

/**
 * Variantes de couleur pour le bouton primaire
 */
enum class ButtonVariant {
    PRIMARY,    // Couleur primaire par défaut
    SECONDARY,  // Couleur secondaire
    SUCCESS,    // Couleur de succès
    ERROR       // Couleur d'erreur
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    fontWeight: FontWeight = FontWeight.Medium,
    variant: ButtonVariant = ButtonVariant.PRIMARY
) {
    val (containerColor, contentColor, disabledContainerColor, disabledContentColor) = when (variant) {
        ButtonVariant.PRIMARY -> {
            Quadruple(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.onTertiary
            )
        }
        ButtonVariant.SECONDARY -> {
            Quadruple(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.onSecondary,
                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
        }
        ButtonVariant.SUCCESS -> {
            Quadruple(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.onPrimaryContainer,
                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
        }
        ButtonVariant.ERROR -> {
            Quadruple(
                MaterialTheme.colorScheme.error,
                MaterialTheme.colorScheme.onError,
                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
            )
        }
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
        shape = RoundedCornerShape(8.dp),
        enabled = enabled && !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(text, fontWeight = fontWeight)
        }
    }
}

// Classe utilitaire pour retourner 4 valeurs
private data class Quadruple<T>(
    val first: T,
    val second: T,
    val third: T,
    val fourth: T
)

@Preview(showBackground = true, name = "Light - All Variants")
@Composable
fun PrimaryButtonPreview_AllVariants() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Primary (default):", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Primaire",
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Secondary:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Secondaire",
                    variant = ButtonVariant.SECONDARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Success:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Succès",
                    variant = ButtonVariant.SUCCESS
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Error:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Erreur",
                    variant = ButtonVariant.ERROR
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Loading States")
@Composable
fun PrimaryButtonPreview_LoadingStates() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Primary - Loading:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Connexion...",
                    isLoading = true,
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Success - Loading:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Enregistrement...",
                    isLoading = true,
                    variant = ButtonVariant.SUCCESS
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Error - Loading:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Suppression...",
                    isLoading = true,
                    variant = ButtonVariant.ERROR
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Disabled while Loading:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Chargement...",
                    enabled = false,
                    isLoading = true,
                    variant = ButtonVariant.PRIMARY
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Variants with States")
@Composable
fun PrimaryButtonPreview_VariantsWithStates() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                // Primary variants
                Text("Primary - Enabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Primaire Actif",
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Primary - Disabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Primaire Désactivé",
                    enabled = false,
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Error variants
                Text("Error - Enabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Erreur Actif",
                    variant = ButtonVariant.ERROR
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Error - Disabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Erreur Désactivé",
                    enabled = false,
                    variant = ButtonVariant.ERROR
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - All Variants", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrimaryButtonDarkPreview_AllVariants() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Primary (default):", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Primaire",
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Secondary:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Secondaire",
                    variant = ButtonVariant.SECONDARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Success:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Succès",
                    variant = ButtonVariant.SUCCESS
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Error:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bouton Erreur",
                    variant = ButtonVariant.ERROR
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Font Weights with Variants")
@Composable
fun PrimaryButtonPreview_FontWeightsWithVariants() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Primary - Normal:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Normal Weight",
                    fontWeight = FontWeight.Normal,
                    variant = ButtonVariant.PRIMARY
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Error - Bold:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "Bold Weight",
                    fontWeight = FontWeight.Bold,
                    variant = ButtonVariant.ERROR
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Success - SemiBold:", style = MaterialTheme.typography.labelMedium)
                PrimaryButton(
                    onClick = { /* Do nothing in preview */ },
                    text = "SemiBold Weight",
                    fontWeight = FontWeight.SemiBold,
                    variant = ButtonVariant.SUCCESS
                )
            }
        }
    }
}