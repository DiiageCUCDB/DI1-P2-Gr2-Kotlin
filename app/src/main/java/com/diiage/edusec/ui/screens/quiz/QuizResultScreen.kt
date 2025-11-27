package com.diiage.edusec.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diiage.edusec.ui.core.components.input.ButtonVariant
import com.diiage.edusec.ui.core.components.input.PrimaryButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.R
import com.diiage.edusec.ui.core.theme.EduSecTheme

@Composable
fun QuizResultScreen(
    navController: NavController,
    score: Int,
    total: Int,
    pointsEarned: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Row(verticalAlignment = Alignment.Bottom) {

            Text(
                text = "$score",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                ),
                modifier = Modifier.padding(bottom = 65.dp)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Divider(
                modifier = Modifier
                    .height(140.dp)
                    .width(4.dp)
                    .graphicsLayer {
                        rotationZ = 45f
                    },
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(1.dp))

            Text(
                text = "$total",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Bravo vous avez fini",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(62.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "+$pointsEarned",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_trophy_outline),
                contentDescription = "Trophée",
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(200.dp))

        PrimaryButton(
            onClick = {
                navController.navigate("home") {
                    popUpTo("quiz") { inclusive = true }
                }
            },
            text = "Retour à l'accueil",
            variant = ButtonVariant.SECONDARY,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "Preview – QuizResultScreen")
@Composable
fun PreviewQuizResultScreen() {
    EduSecTheme {
        val navController = rememberNavController()  // Fake navController pour la preview

        QuizResultScreen(
            navController = navController,
            score = 12,
            total = 80,
            pointsEarned = 200
        )
    }
}