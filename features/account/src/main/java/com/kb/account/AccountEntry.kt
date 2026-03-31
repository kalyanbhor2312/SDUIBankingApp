package com.kb.account

import androidx.compose.runtime.Composable
import com.kb.core.ScreenIds
import com.kb.core_engine.ComponentRenderer
import com.kb.core_engine.SDUIAction
import com.kb.core_engine.SDUIScreen

object AccountsFeatureEntry {
    const val SCREEN_ID: String = ScreenIds.ACCOUNTS
}

@Composable
fun AccountsSduiHost(
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
