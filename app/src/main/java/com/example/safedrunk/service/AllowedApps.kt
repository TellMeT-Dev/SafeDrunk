package com.example.safedrunk.service

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.telecom.TelecomManager
import android.view.inputmethod.InputMethodManager

object AllowedApps {

    @Suppress("DEPRECATION")
    fun resolve(context: Context): Set<String> {
        val allowed = mutableSetOf(context.packageName)

        val homeIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
        context.packageManager
            .resolveActivity(homeIntent, PackageManager.MATCH_DEFAULT_ONLY)
            ?.activityInfo?.packageName
            ?.let { allowed += it }

        context.getSystemService(TelecomManager::class.java)
            ?.defaultDialerPackage
            ?.let { allowed += it }

        context.getSystemService(InputMethodManager::class.java)
            ?.enabledInputMethodList
            ?.forEach { allowed += it.packageName }

        return allowed
    }
}
