package com.diiage.edusec.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import com.diiage.edusec.ui.theme.Facile
import com.diiage.edusec.ui.theme.Moyen
import com.diiage.edusec.ui.theme.Difficile
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.theme.EduSecTheme

enum class DifficultyLevel(val displayName: String, val color: Color) {
    EASY("Facile", Facile),
    MEDIUM("Normal", Moyen),
    HARD("Difficile", Difficile)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyFilter(
    selectedDifficulties: Set<DifficultyLevel>,
    onDifficultySelected: (DifficultyLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DifficultyLevel.values().forEach { difficulty ->
            FilterChip(
                selected = selectedDifficulties.contains(difficulty),
                onClick = { onDifficultySelected(difficulty) },
                label = {
                    Text(
                        text = difficulty.displayName,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                leadingIcon = {
                    DifficultyDot(color = difficulty.color)
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = difficulty.color.copy(alpha = 0.1f),
                    selectedLabelColor = difficulty.color,
                    selectedLeadingIconColor = difficulty.color
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (selectedDifficulties.contains(difficulty)) difficulty.color
                    else MaterialTheme.colorScheme.outline,
                    selectedBorderColor = difficulty.color
                )
            )
        }
    }
}

@Composable
fun DifficultyDot(
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .background(color, CircleShape)
    )
}

// Previews pour la sélection multiple
@Preview(showBackground = true, name = "Multiple Selection")
@Composable
fun DifficultyFilterMultipleSelectionPreview() {
    EduSecTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Aucune sélection
            Column {
                Text(
                    text = "Aucune difficulté sélectionnée",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                DifficultyFilter(
                    selectedDifficulties = emptySet(),
                    onDifficultySelected = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Une seule sélection
            Column {
                Text(
                    text = "Facile sélectionné",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                DifficultyFilter(
                    selectedDifficulties = setOf(DifficultyLevel.EASY),
                    onDifficultySelected = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Sélection multiple
            Column {
                Text(
                    text = "Facile et Normal sélectionnés",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                DifficultyFilter(
                    selectedDifficulties = setOf(DifficultyLevel.EASY, DifficultyLevel.MEDIUM),
                    onDifficultySelected = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Toutes sélectionnées
            Column {
                Text(
                    text = "Toutes les difficultés sélectionnées",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                DifficultyFilter(
                    selectedDifficulties = setOf(DifficultyLevel.EASY, DifficultyLevel.MEDIUM, DifficultyLevel.HARD),
                    onDifficultySelected = { },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}