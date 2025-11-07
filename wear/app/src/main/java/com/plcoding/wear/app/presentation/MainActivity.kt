package com.plcoding.wear.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.plcoding.core.notification.ActiveRunService
import com.plcoding.core.presentation.designsystem_wear.RuniqueTheme
import com.plcoding.wear.run.presentation.TrackerScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            RuniqueTheme {
                TrackerScreenRoot(
                    onServiceToggle = { shouldStartRunning ->
                        if(shouldStartRunning) {
                            startService(
                                ActiveRunService.createStartIntent(
                                    applicationContext, this::class.java
                                )
                            )
                        } else {
                            startService(
                                ActiveRunService.createStopIntent(applicationContext)
                            )
                        }
                    }
                )
            }
        }
    }
}