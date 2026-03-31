package com.kb.core_engine

import com.kb.core.ScreenIds
import kotlinx.coroutines.delay
import java.util.UUID

data class ScreenRequest(
    val screenId: String,
    val params: Map<String, String> = emptyMap(),
    val requestId: String = UUID.randomUUID().toString()
)

data class ScreenResponse(
    val requestId: String,
    val screenId: String,
    val payload: String
)

interface ScreenRepository {
    suspend fun fetchInitialScreenId(): String
    suspend fun fetchScreen(request: ScreenRequest): ScreenResponse
}

class MockScreenRepository : ScreenRepository {
    override suspend fun fetchInitialScreenId(): String {
        delay(100)
        return ScreenIds.LOGIN
    }

    override suspend fun fetchScreen(request: ScreenRequest): ScreenResponse {
        delay(200)
        val payload = MockJsonApi.screenPayloads[request.screenId]
            ?: throw IllegalArgumentException("Unknown screen id: ${request.screenId}")

        return ScreenResponse(
            requestId = request.requestId,
            screenId = request.screenId,
            payload = payload
        )
    }
}

