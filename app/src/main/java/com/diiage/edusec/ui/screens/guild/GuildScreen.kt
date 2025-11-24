package com.diiage.edusec.ui.screens.guild

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diiage.edusec.R
import com.diiage.edusec.domain.usecase.GuildService
import com.diiage.edusec.ui.core.components.CustomTitle
import com.diiage.edusec.ui.core.components.GroupItem
import com.diiage.edusec.ui.core.components.input.ButtonVariant
import com.diiage.edusec.ui.core.components.input.PrimaryButton
import com.diiage.edusec.ui.core.components.input.PrimaryTextField
import com.diiage.edusec.ui.core.components.input.SearchBar
import com.diiage.edusec.ui.core.components.layout.MainScaffold
import com.diiage.edusec.ui.core.theme.White

@Composable
fun GuildScreen(navController: NavController) {
    val guildService = remember { GuildService() }
    val scrollState = rememberScrollState()
    var showPopup by remember { mutableStateOf(false) }
    var teamName by remember { mutableStateOf("") }

    MainScaffold(navController = navController) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                CustomTitle("Équipes")


                // Forcer la pop up
//                if (LocalInspectionMode.current) {
//                    showPopup = true // Forcer la popup en mode Preview
//                }

                // Search bar
                SearchBar(
                    value = guildService.searchQuery,
                    onValueChange = { guildService.updateSearch(it) },
                    placeholder = "Rechercher une équipe",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                PrimaryButton(
                    onClick = { showPopup = true },
                    text = "Créer une équipe",
                    variant = ButtonVariant.SECONDARY,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Affichage des guildes
                guildService.guilds.forEach { guild ->
                    GroupItem(
                        onClick = { /* TODO: action ouvrir la guilde */ },
                        name = guild.name,
                        score = guild.score,
                        members = guild.members
                    )
                }
            }

            // Pop up group creation
            if (showPopup) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f)) // Overlay semi-transparent
                        .clickable(enabled = false) {} // Désactive clics sur le reste
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .windowInsetsPadding(WindowInsets.ime) // Ajoute un padding selon la hauteur du clavier
                        .consumeWindowInsets(WindowInsets.ime) // Indique que l’inset du clavier est déjà géré
                        .fillMaxWidth(0.95f)
                        .background(White, shape = RoundedCornerShape(8.dp))
                        .padding(16.dp),
                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Entrez le nom de votre équipe :")

                        Icon(
                            painter = painterResource(R.drawable.ic_close_black),
                            contentDescription = "Fermer",
                            modifier = Modifier.clickable(onClick = { showPopup = false })
                        )
                    }

                    PrimaryTextField(
                        value = teamName,
                        onValueChange = { teamName = it },
                        label = "Nom"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PrimaryButton(
                        onClick = { showPopup = false },
                        text = "Créer une équipe",
                        variant = ButtonVariant.SECONDARY,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GuildScreenPreview() {
    GuildScreen(navController = rememberNavController())
}