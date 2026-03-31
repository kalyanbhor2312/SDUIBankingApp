package com.kb.core_engine

import kotlinx.serialization.json.Json

object SDUIJsonParser {
    val instance: Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
        classDiscriminator = "type"
    }
}
