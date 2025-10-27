package com.diiage.edusec.data.source

import com.diiage.edusec.viewmodels.Layer

object SampleLayerDataSource {
    fun getLayers(): List<Layer> = listOf(
        Layer("Domain Layer", "Contains business logic and entities."),
        Layer("Data Layer", "Handles repositories, APIs, and persistence."),
        Layer("Presentation Layer", "Handles UI and user interaction.")
    )
}
