package com.diiage.edusec.domain.mock


import com.diiage.edusec.domain.model.QuizAnswer
import com.diiage.edusec.domain.model.QuizQuestion
import kotlin.random.Random

private val cyberQuestionsPool = listOf(
    "Doit-on verrouiller son poste lorsqu’on quitte son bureau ?",
    "Faut-il vérifier l’adresse de l’expéditeur avant de cliquer sur un lien ?",
    "Un collègue vous demande votre mot de passe : doit-on le lui communiquer ?",
    "Est-il recommandé d’utiliser le même mot de passe partout ?",
    "Peut-on brancher une clé USB trouvée dans le parking sur son poste ?",
    "Est-ce que le Wi-Fi public est adapté pour consulter des données sensibles ?",
    "Faut-il signaler un mail suspect à l’équipe sécurité / support ?",
    "Un lien dans un mail vous demande de ressaisir vos identifiants : faut-il cliquer ?",
    "Une application mobile non officielle vous promet un bonus : doit-on l’installer ?",
    "Peut-on envoyer des documents sensibles sur sa boîte mail personnelle ?",
    "Doit-on vérifier l’URL du site avant de saisir ses identifiants ?",
    "Peut-on partager une capture d’écran interne sur les réseaux sociaux ?",
    "Un SMS vous demande un code de validation que vous venez de recevoir : faut-il le donner ?",
    "Faut-il mettre à jour régulièrement son système et ses applications ?",
    "Est-il acceptable de laisser des documents sensibles traîner sur son bureau ?"
)

// Pour chaque challenge, on génère un nombre aléatoire de questions entre 3 et 15
val mockQuestionsByChallenge: Map<String, List<QuizQuestion>> =
    mockChallenges.associate { challenge ->
        val numberOfQuestions = Random.nextInt(from = 3, until = 16) // 3..15 inclus

        val questions = (1..numberOfQuestions).map { index ->
            val questionText = cyberQuestionsPool[(index - 1) % cyberQuestionsPool.size]

            QuizQuestion(
                id = "q-${challenge.id}-$index",
//                challengeId = challenge.id,
                questionText = questionText,
                answers = listOf(
                    QuizAnswer(
                        id = "a-${challenge.id}-${index}-1",
                        answerText = "Oui"
                    ),
                    QuizAnswer(
                        id = "a-${challenge.id}-${index}-2",
                        answerText = "Non"
                    )
                )
            )
        }

        challenge.id to questions
    }