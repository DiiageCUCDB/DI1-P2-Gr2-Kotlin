package com.diiage.edusec.ui.screens.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diiage.edusec.ui.core.components.input.ButtonVariant
import com.diiage.edusec.ui.core.components.input.PrimaryButton
import com.diiage.edusec.ui.core.theme.EduSecTheme

@Composable
fun QuizScreen(
    questionIndex: Int,
    totalQuestions: Int,
    questionText: String,
    onAnswerSelected: (Boolean) -> Unit
) {
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 190.dp),
            ) {
                PrimaryButton(
                    onClick = { onAnswerSelected(true) },
                    text = "Oui",
                    variant = ButtonVariant.SECONDARY
                )

                Spacer(modifier = Modifier.height(30.dp))

                PrimaryButton(
                    onClick = { onAnswerSelected(false) },
                    text = "Non",
                    variant = ButtonVariant.PRIMARY
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
            ) {

                Text(
                    text = "$questionIndex",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(bottom = 25.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Divider(
                    modifier = Modifier
                        .height(60.dp)
                        .width(3.dp)
                        .graphicsLayer {
                            rotationZ = 45f
                        },
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "$totalQuestions",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "CyberQuiz — Teste ta vigilance",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = questionText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    EduSecTheme {
        QuizScreen(
            questionIndex = 13,
            totalQuestions = 20,
            questionText = "Doit-on verrouiller notre poste lorsqu’on quitte son bureau ?",
            onAnswerSelected = {}
        )
    }
}