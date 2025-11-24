package com.diiage.edusec.domain.usecase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.TextFieldValue
import com.diiage.edusec.domain.mock.mockGuilds
import com.diiage.edusec.domain.model.Guild
import java.util.Locale

class GuildService {

    // Liste réactive observable
    var guilds = mutableStateListOf<Guild>()
        private set

    // Valeur du champ de recherche
    var searchQuery by mutableStateOf(TextFieldValue(""))
        private set

    init {
        guilds.addAll(mockGuilds)
    }

    // Met à jour la recherche et filtre la liste
    fun updateSearch(query: TextFieldValue) {
        searchQuery = query
        guilds.clear()
        guilds.addAll(
            if (query.text.isBlank()) {
                mockGuilds
            } else {
                mockGuilds.filter { guild ->
                    guild.name.lowercase(Locale.getDefault())
                        .contains(query.text.lowercase(Locale.getDefault()))
                }
            }
        )
    }
}