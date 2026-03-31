package com.kb.kbsduibankapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kb.account.AccountsFeatureEntry
import com.kb.account.AccountsSduiHost
import com.kb.auth.AuthFeatureEntry
import com.kb.auth.AuthSduiHost
import com.kb.core_engine.SDUIAction
import com.kb.core_engine.SDUIScreen
import com.kb.home.HomeFeatureEntry
import com.kb.home.HomeSduiHost
import com.kb.payment.PaymentsFeatureEntry
import com.kb.payment.PaymentsSduiHost
import com.kb.profile.ProfileFeatureEntry
import com.kb.profile.ProfileSduiHost

private typealias ScreenHost = @Composable (
    screen: SDUIScreen,
    formData: Map<String, String>,
    onAction: (SDUIAction) -> Unit,
    onFieldChanged: (String, String) -> Unit
) -> Unit

class ScreenRoute(
    private val routes: Map<String, ScreenHost>
) {
    @Composable
    fun Render(
        screen: SDUIScreen,
        formData: Map<String, String>,
        onAction: (SDUIAction) -> Unit,
        onFieldChanged: (String, String) -> Unit,
        unsupportedModifier: Modifier
    ) {
        val host = routes[screen.id]
        if (host != null) {
            host(screen, formData, onAction, onFieldChanged)
        } else {
            Text(
                text = "Unsupported screen: ${screen.id}",
                color = MaterialTheme.colorScheme.error,
                modifier = unsupportedModifier
            )
        }
    }

    companion object {
        fun default(): ScreenRoute {
            return ScreenRoute(
                routes = mapOf(
                    AuthFeatureEntry.SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        AuthSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    },
                    HomeFeatureEntry.SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        HomeSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    },
                    PaymentsFeatureEntry.SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        PaymentsSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    },
                    PaymentsFeatureEntry.ADD_BENEFICIARY_SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        PaymentsSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    },
                    AccountsFeatureEntry.SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        AccountsSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    },
                    ProfileFeatureEntry.SCREEN_ID to { screen, formData, onAction, onFieldChanged ->
                        ProfileSduiHost(
                            screen = screen,
                            formData = formData,
                            onAction = onAction,
                            onFieldChanged = onFieldChanged
                        )
                    }
                )
            )
        }
    }
}

