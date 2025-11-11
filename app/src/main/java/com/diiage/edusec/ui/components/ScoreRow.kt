package com.diiage.edusec.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.theme.EduSecTheme

@Composable
fun ScoreRow(
    rank: Int,
    name: String,
    score: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ) {
            Text(
                text = "$rank",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$score",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(com.diiage.edusec.R.drawable.trophy),
                contentDescription = "Score",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
            )
        }
    }
}

@Preview(showBackground = true, name = "Light - Complete Leaderboard")
@Composable
fun ScoreRowPreview_CompleteLeaderboard() {
    EduSecTheme {
        Surface {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Classement Complet:", style = MaterialTheme.typography.titleMedium)
                ScoreRow(rank = 1, name = "Alice Martin", score = 1500)
                ScoreRow(rank = 2, name = "Bob Wilson", score = 1420)
                ScoreRow(rank = 3, name = "Charlie Brown", score = 1380)
                ScoreRow(rank = 4, name = "Diana Prince", score = 1250)
                ScoreRow(rank = 5, name = "Emma Stone", score = 1180)
                ScoreRow(rank = 6, name = "Frank Ocean", score = 1100)
                ScoreRow(rank = 7, name = "Grace Hopper", score = 1050)
                ScoreRow(rank = 8, name = "Henry Ford", score = 980)
                ScoreRow(rank = 9, name = "Irene Joliot", score = 920)
                ScoreRow(rank = 10, name = "Jack Sparrow", score = 880)
            }
        }
    }
}

@Preview(showBackground = true, name = "Dark - Complete Leaderboard", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ScoreRowDarkPreview_CompleteLeaderboard() {
    EduSecTheme {
        Surface {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Classement Complet:", style = MaterialTheme.typography.titleMedium)
                ScoreRow(rank = 1, name = "Alice Martin", score = 1500)
                ScoreRow(rank = 2, name = "Bob Wilson", score = 1420)
                ScoreRow(rank = 3, name = "Charlie Brown", score = 1380)
                ScoreRow(rank = 4, name = "Diana Prince", score = 1250)
                ScoreRow(rank = 5, name = "Emma Stone", score = 1180)
                ScoreRow(rank = 6, name = "Frank Ocean", score = 1100)
                ScoreRow(rank = 7, name = "Grace Hopper", score = 1050)
                ScoreRow(rank = 8, name = "Henry Ford", score = 980)
                ScoreRow(rank = 9, name = "Irene Joliot", score = 920)
                ScoreRow(rank = 10, name = "Jack Sparrow", score = 880)
            }
        }
    }
}