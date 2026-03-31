package com.kb.payment

import androidx.compose.runtime.Composable
import com.kb.core.ScreenIds
import com.kb.core_engine.ComponentRenderer
import com.kb.core_engine.SDUIAction
import com.kb.core_engine.SDUIScreen

object PaymentsFeatureEntry {
    const val SCREEN_ID: String = ScreenIds.PAYMENTS
    const val ADD_BENEFICIARY_SCREEN_ID: String = ScreenIds.ADD_BENEFICIARY
}

@Composable
fun PaymentsSduiHost(
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
