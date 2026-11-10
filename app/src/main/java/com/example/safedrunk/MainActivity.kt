package com.example.safedrunk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.safedrunk.ui.theme.SafeDrunkTheme
import com.example.safedrunk.view.ActiveLockScreen
import com.example.safedrunk.view.LockScreen
import com.example.safedrunk.view.UnlockScreen
enum class Screen { LOCK, Active_L, Unlock }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SafeDrunkTheme {
                var screen by rememberSaveable { mutableStateOf(Screen.LOCK) }

                when (screen) {
                    Screen.LOCK -> LockScreen(
                        onActivateClick = { screen = Screen.Active_L }
                    )

                    Screen.Active_L -> {
                        BackHandler(enabled = true) { }   // "atrás" no hace nada
                        ActiveLockScreen(
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