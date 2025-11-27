package com.diiage.edusec.data.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiResponse<T>(
    val result: T,
    val generationTime_ms: Double,
    val success: Boolean,
    val message: String
) {
    // Helper properties to distinguish between single object and list
    val isList: Boolean get() = result is List<*>

    // Safe casting helpers
    fun asList(): List<Any>? = result as? List<*> as List<Any>?

    @Suppress("UNCHECKED_CAST")
    fun <R> asListOf(): List<R>? = result as? List<R>

    fun asSingle(): T = result
}