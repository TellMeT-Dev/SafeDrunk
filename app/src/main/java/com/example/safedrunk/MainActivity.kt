package com.example.safedrunk

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safedrunk.ui.theme.SafeDrunkTheme
import com.example.safedrunk.view.ActiveLockScreen
import com.example.safedrunk.view.LockScreen
import com.example.safedrunk.view.UnlockScreen
import com.example.safedrunk.viewmodel.LockViewModel

enum class Screen { LOCK, Active_L, Unlock }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeDrunkTheme {
                val context = LocalContext.current
                val lockViewModel: LockViewModel = viewModel()
                val uiState by lockViewModel.uiState.collectAsStateWithLifecycle()
                var screen by rememberSaveable { mutableStateOf(Screen.LOCK) }

                LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
                    lockViewModel.refreshBlockerStatus()
                }


                LaunchedEffect(uiState.isLockActive) {
                    if (uiState.isLockActive && screen == Screen.LOCK) {
                        screen = Screen.Active_L
                    } else if (!uiState.isLockActive && screen != Screen.LOCK) {
                        screen = Screen.LOCK
                    }
                }

                when (screen) {
                    Screen.LOCK -> LockScreen(
                        selectedDuration = uiState.selectedDuration,
                        onDurationSelected = lockViewModel::selectDuration,
                        isBlockerEnabled = uiState.isBlockerEnabled,
                        onOpenAccessibilitySettings = {
                            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                        },
                        onActivateClick = lockViewModel::activateLock
                    )

                    Screen.Active_L -> {
                        BackHandler(enabled = true) { }   // "atrás" no hace nada
                        ActiveLockScreen(
                            remainingText = uiState.remainingText,
                            onActivateClick = { screen = Screen.Unlock }
                        )
                    }

                    Screen.Unlock -> {
                        BackHandler(enabled = true) { screen = Screen.Active_L }
                        UnlockScreen()
                    }
                }
            }
        }
    }
}
