package com.diiage.edusec.ui.components.input

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.theme.EduSecTheme

@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                label,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Preview(showBackground = true, name = "Light - Input Types")
@Composable
fun PrimaryTextFieldPreview_InputTypes() {
    var text1 by remember { mutableStateOf("John Doe") }
    var text2 by remember { mutableStateOf("email@example.com") }
    var text3 by remember { mutableStateOf("1234567890") }
    var text4 by remember { mutableStateOf("Mot de passe secret") }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Text:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = "Nom complet"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Email:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = "Adresse email",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Number:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text3,
                    onValueChange = { text3 = it },
                    label = "Numéro de téléphone",
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Password:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text4,
                    onValueChange = { text4 = it },
                    label = "Mot de passe",
                    keyboardType = KeyboardType.Password
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - All States")
@Composable
fun PrimaryTextFieldPreview_AllStates() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("Avec du texte") }
    var text3 by remember { mutableStateOf("Désactivé") }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Empty:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = "Champ vide"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("With text:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = "Avec contenu"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Disabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text3,
                    onValueChange = { text3 = it },
                    label = "Champ désactivé",
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - All States", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrimaryTextFieldDarkPreview_AllStates() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("Avec du texte") }
    var text3 by remember { mutableStateOf("Désactivé") }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Empty:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = "Champ vide"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("With text:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = "Avec contenu"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Disabled:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text3,
                    onValueChange = { text3 = it },
                    label = "Champ désactivé",
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Input Types", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrimaryTextFieldDarkPreview_InputTypes() {
    var text1 by remember { mutableStateOf("John Doe") }
    var text2 by remember { mutableStateOf("email@example.com") }
    var text3 by remember { mutableStateOf("1234567890") }
    var text4 by remember { mutableStateOf("Mot de passe secret") }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Text:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = "Nom complet"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Email:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = "Adresse email",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Number:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text3,
                    onValueChange = { text3 = it },
                    label = "Numéro de téléphone",
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Password:", style = MaterialTheme.typography.labelMedium)
                PrimaryTextField(
                    value = text4,
                    onValueChange = { text4 = it },
                    label = "Mot de passe",
                    keyboardType = KeyboardType.Password
                )
            }
        }
    }
}