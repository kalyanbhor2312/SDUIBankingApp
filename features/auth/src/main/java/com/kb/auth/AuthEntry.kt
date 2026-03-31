package com.kb.auth

import androidx.compose.runtime.Composable
import com.kb.core.ScreenIds
import com.kb.core_engine.ComponentRenderer
import com.kb.core_engine.SDUIAction
import com.kb.core_engine.SDUIScreen

object AuthFeatureEntry {
    const val SCREEN_ID: String = ScreenIds.LOGIN
}

@Composable
fun AuthSduiHost(
    screen: SDUIScreen,
    formData: Map<String, String>,
    onAction: (SDUIAction) -> Unit,
    onFieldChanged: (String, String) -> Unit
) {
    ComponentRenderer(
        components = screen.components,
        onAction = onAction,
        formData = formData,
        onFieldChanged = onFieldChanged
    )
}
