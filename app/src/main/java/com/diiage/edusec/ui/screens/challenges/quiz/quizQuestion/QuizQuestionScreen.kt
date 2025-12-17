package com.diiage.edusec.ui.screens.challenges.quiz.quizQuestion

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diiage.edusec.domain.model.QuizAnswer
import com.diiage.edusec.ui.core.components.input.ButtonVariant
import com.diiage.edusec.ui.core.components.input.PrimaryButton
import com.diiage.edusec.ui.core.theme.EduSecTheme

@Composable
fun QuizQuestionScreen(
    questionIndex: Int,
    totalQuestions: Int,
    questionText: String,
    answers: List<QuizAnswer>,
    onAnswerSelected: (String) -> Unit,
    viewModel: QuizQuestionViewModel = viewModel()
) {
    LaunchedEffect(questionIndex, totalQuestions, questionText) {
        viewModel.setQuestion(
            index = questionIndex,
            total = totalQuestions,
            text = questionText
        )
    }

    val state by viewModel.state.collectAsState()

    QuizQuestionContent(
        questionIndex = state.questionIndex,
        totalQuestions = state.totalQuestions,
        questionText = state.questionText,
        answers = answers,
        onAnswerSelected = onAnswerSelected
    )
}

@Composable
fun QuizQuestionContent(
    questionIndex: Int,
    totalQuestions: Int,
    questionText: String,
    answers: List<QuizAnswer>,
    onAnswerSelected: (String) -> Unit
) {
    val a1 = answers.getOrNull(0)
    val a2 = answers.getOrNull(1)

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 190.dp),
            ) {

                if (a1 != null) {
                    PrimaryButton(
                        onClick = { onAnswerSelected(a1.id) },
                        text = a1.answerText,
                        variant = ButtonVariant.SECONDARY
                    )
                }

                if (a1 != null && a2 != null) {
                    Spacer(modifier = Modifier.height(30.dp))
                }

                if (a2 != null) {
                    PrimaryButton(
                        onClick = { onAnswerSelected(a2.id) },
                        text = a2.answerText,
                        variant = ButtonVariant.PRIMARY
                    )
                }
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
                        .graphicsLayer { rotationZ = 45f },
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
fun PreviewQuizQuestionContent() {
    EduSecTheme {
        QuizQuestionContent(
            questionIndex = 1,
            totalQuestions = 2,
            questionText = "Question…",
            answers = listOf(
                QuizAnswer(id = "a1", answerText = "Oui"),
                QuizAnswer(id = "a2", answerText = "Non")
            ),
            onAnswerSelected = {}
        )
    }
}
