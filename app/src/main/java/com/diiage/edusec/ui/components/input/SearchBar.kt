package com.diiage.edusec.ui.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.theme.EduSecTheme

@Composable
fun SearchBar(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String = "Rechercher...",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = {
            Text(
                placeholder,
            )
        },
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}

// Special composable for showing focused state in previews
@Composable
fun SearchBarFocusedPreview(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String = "Rechercher...",
    modifier: Modifier = Modifier
) {
    // Create a custom interaction source to simulate focused state
    val interactionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = {
            Text(
                placeholder,
            )
        },
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        interactionSource = interactionSource
    )
}

@Preview(showBackground = true, name = "Light - All States")
@Composable
fun SearchBarAllStatesPreview() {
    var searchValue1 by remember { mutableStateOf(TextFieldValue("")) }
    var searchValue2 by remember { mutableStateOf(TextFieldValue("Test focused")) }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Unfocused (empty):", style = MaterialTheme.typography.labelMedium)
                SearchBar(
                    value = searchValue1,
                    onValueChange = { searchValue1 = it },
                    placeholder = "Rechercher..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Unfocused (with text):", style = MaterialTheme.typography.labelMedium)
                SearchBar(
                    value = searchValue2,
                    onValueChange = { searchValue2 = it },
                    placeholder = "Rechercher..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Focused (with text):", style = MaterialTheme.typography.labelMedium)
                SearchBarFocusedPreview(
                    value = searchValue2,
                    onValueChange = { searchValue2 = it },
                    placeholder = "Rechercher..."
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - All States", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchBarAllStatesDarkPreview() {
    var searchValue1 by remember { mutableStateOf(TextFieldValue("")) }
    var searchValue2 by remember { mutableStateOf(TextFieldValue("Test focused")) }

    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Unfocused (empty):", style = MaterialTheme.typography.labelMedium)
                SearchBar(
                    value = searchValue1,
                    onValueChange = { searchValue1 = it },
                    placeholder = "Rechercher..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Unfocused (with text):", style = MaterialTheme.typography.labelMedium)
                SearchBar(
                    value = searchValue2,
                    onValueChange = { searchValue2 = it },
                    placeholder = "Rechercher..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Focused (with text):", style = MaterialTheme.typography.labelMedium)
                SearchBarFocusedPreview(
                    value = searchValue2,
                    onValueChange = { searchValue2 = it },
                    placeholder = "Rechercher..."
                )
            }
        }
    }
}