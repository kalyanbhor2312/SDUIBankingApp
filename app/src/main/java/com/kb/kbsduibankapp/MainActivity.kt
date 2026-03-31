 package com.kb.kbsduibankapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kb.core_engine.SDUIIntent
import com.kb.core_engine.SDUIViewModel
import com.kb.core_engine.TopBarLeading
import com.kb.designsystem.components.BottomNavItem
import com.kb.designsystem.components.BottomNavigation
import com.kb.designsystem.components.TopBar
import com.kb.designsystem.ui.theme.KBSDUIBankAppTheme

 class MainActivity : ComponentActivity() {
     private val viewModel: SDUIViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KBSDUIBankAppTheme {
                val uiState by viewModel.uiState.collectAsState()
                val context = LocalContext.current
                val screenRouter = remember { ScreenRoute.default() }

                LaunchedEffect(Unit) {
                    viewModel.processIntent(SDUIIntent.LoadInitialScreen)
                }

                LaunchedEffect(Unit) {
                    viewModel.sideEffect.collect { message ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val topBar = uiState.screen?.topBar
                        if (topBar?.show != false) {
                            TopBar(
                                modifier = Modifier.statusBarsPadding(),
                                title = topBar?.title ?: "Architect",
                                leadingIconName = if (topBar?.leading == TopBarLeading.ICON) {
                                    topBar.leadingIcon
                                } else {
                                    null
                                },
                                showNotifications = topBar?.showNotifications ?: true,
                                showBackButton = topBar?.leading == TopBarLeading.BACK,
                                onBackClick = {
                                    topBar?.backAction?.let { action ->
                                        viewModel.processIntent(SDUIIntent.HandleAction(action))
                                    }
                                },
                                showProfileIcon = topBar?.trailingProfile ?: false,
                                showLeadingProfileIcon = topBar?.leading == TopBarLeading.PROFILE,
                                showSearchIcon = topBar?.showSearch ?: false
                            )
                        }
                    },
                    bottomBar = {
                        uiState.screen?.bottomNavigation?.let { nav ->
                            BottomNavigation(
                                items = nav.items.map {
                                    BottomNavItem(label = it.label, iconName = it.icon)
                                },
                                selectedIndex = nav.selectedIndex,
                                onItemSelected = { index ->
                                    viewModel.processIntent(SDUIIntent.HandleAction(nav.items[index].action))
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }

                        uiState.screen?.let { screen ->
                            screenRouter.Render(
                                screen = screen,
                                formData = uiState.formData,
                                onAction = { viewModel.processIntent(SDUIIntent.HandleAction(it)) },
                                onFieldChanged = { key, value ->
                                    viewModel.processIntent(SDUIIntent.UpdateField(key, value))
                                },
                                unsupportedModifier = Modifier.align(Alignment.Center)
                            )
                        }

                        uiState.error?.let { error ->
                            Text(
                                text = "Error: $error",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}


