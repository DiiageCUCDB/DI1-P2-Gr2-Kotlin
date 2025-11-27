package com.diiage.edusec.ui.core.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.R
import com.diiage.edusec.ui.core.theme.EduSecTheme
import com.diiage.edusec.ui.core.theme.Facile
import com.diiage.edusec.ui.core.theme.Moyen
import com.diiage.edusec.ui.core.theme.Difficile
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.diiage.edusec.ui.core.components.layout.LargeSpacer
import com.diiage.edusec.ui.core.components.layout.MediumSpacer
import com.diiage.edusec.ui.core.components.layout.SmallSpacer

// Classe utilitaire pour retourner le couple texte/icône
private data class ModeInfo(
    val text: String,
    val iconRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengeCard(
    title: String,
    description: String,
    date: LocalDate,
    difficulty: String,
    isEquipe: Boolean,
    onClick: () -> Unit
) {
    val difficultyColor = when (difficulty.lowercase()) {
        "facile" -> Facile
        "normal" -> Moyen
        "difficile" -> Difficile
        else -> MaterialTheme.colorScheme.outline
    }

    // Format date to DD/MM/YYYY
    val formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    // Determine mode text and icon based on isEquipe boolean
    val modeInfo = if (isEquipe) {
        ModeInfo("Équipe", R.drawable.ic_users_outline)
    } else {
        ModeInfo("Solo", R.drawable.ic_user)
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .padding(top = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(RoundedCornerShape(50))
                            .background(difficultyColor)
                    )
                }
            }

            MediumSpacer()
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            LargeSpacer()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = modeInfo.iconRes),
                        contentDescription = null, // Décoratif seulement, déjà décrit par le texte
                        modifier = Modifier
                            .size(16.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                    )
                    SmallSpacer()
                    Text(
                        text = modeInfo.text,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Same Line Alignment")
@Composable
fun ChallengeCardPreview_SameLineAlignment() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Challenge Normal",
                    description = "Test d'alignement visuel entre le titre et l'indicateur",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Normal",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Very Long Title")
@Composable
fun ChallengeCardPreview_VeryLongTitle() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Challenge avec un titre extrêmement long qui pourrait dépasser la largeur de l'écran et cacher l'indicateur de difficulté",
                    description = "Cette description est également très longue pour tester comment le texte se comporte lorsqu'il occupe plusieurs lignes dans la carte de challenge.",
                    date = LocalDate.of(2024, 12, 19),
                    difficulty = "Normal",
                    isEquipe = true,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Solo Mode")
@Composable
fun ChallengeCardPreview_Solo() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Challenge Sécurité Réseau",
                    description = "Testez vos connaissances en sécurité réseau et protocoles",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Facile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Team Mode")
@Composable
fun ChallengeCardPreview_Team() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Cryptographie Avancée",
                    description = "Déchiffrez des messages cryptés avec différents algorithmes",
                    date = LocalDate.of(2024, 12, 16),
                    difficulty = "Normal",
                    isEquipe = true,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Hard Difficulty")
@Composable
fun ChallengeCardPreview_Hard() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Pentest Avancé",
                    description = "Simulation d'intrusion sur un réseau sécurisé",
                    date = LocalDate.of(2024, 12, 17),
                    difficulty = "Difficile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - Short Title")
@Composable
fun ChallengeCardPreview_ShortTitle() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Test",
                    description = "Challenge avec un titre très court",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Facile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light - All Difficulties")
@Composable
fun ChallengeCardPreview_AllDifficulties() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Easy:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Basique",
                    description = "Introduction aux concepts de sécurité",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Facile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Normal:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Intermédiaire",
                    description = "Concepts avancés de sécurité",
                    date = LocalDate.of(2024, 12, 16),
                    difficulty = "Normal",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Hard:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Avancée",
                    description = "Défis complexes de sécurité",
                    date = LocalDate.of(2024, 12, 17),
                    difficulty = "Difficile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Same Line Alignment", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChallengeCardDarkPreview_SameLineAlignment() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Challenge Normal",
                    description = "Test d'alignement visuel entre le titre et l'indicateur",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Normal",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Very Long Title", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChallengeCardDarkPreview_VeryLongTitle() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Challenge avec un titre extrêmement long qui pourrait dépasser la largeur de l'écran et cacher l'indicateur de difficulté",
                    description = "Cette description est également très longue pour tester comment le texte se comporte lorsqu'il occupe plusieurs lignes dans la carte de challenge.",
                    date = LocalDate.of(2024, 12, 19),
                    difficulty = "Normal",
                    isEquipe = true,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Short Title", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChallengeCardDarkPreview_ShortTitle() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                ChallengeCard(
                    title = "Test",
                    description = "Challenge avec un titre très court",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Facile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - All Difficulties", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ChallengeCardDarkPreview_AllDifficulties() {
    EduSecTheme {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Easy:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Basique",
                    description = "Introduction aux concepts de sécurité",
                    date = LocalDate.of(2024, 12, 15),
                    difficulty = "Facile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Normal:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Intermédiaire",
                    description = "Concepts avancés de sécurité",
                    date = LocalDate.of(2024, 12, 16),
                    difficulty = "Normal",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Hard:", style = MaterialTheme.typography.labelMedium)
                ChallengeCard(
                    title = "Sécurité Avancée",
                    description = "Défis complexes de sécurité",
                    date = LocalDate.of(2024, 12, 17),
                    difficulty = "Difficile",
                    isEquipe = false,
                    onClick = { /* Do nothing in preview */ }
                )
            }
        }
    }
}