package com.example.safedrunk.service

import android.content.ComponentName
import android.content.Context
import android.provider.Settings

fun isBlockerServiceEnabled(context: Context): Boolean {
    val expected = ComponentName(context, AppBlockerService::class.java)
    val enabledServices = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
    ) ?: return false

    return enabledServices.split(':').any { entry ->
        ComponentName.unflattenFromString(entry) == expected
    }
}
