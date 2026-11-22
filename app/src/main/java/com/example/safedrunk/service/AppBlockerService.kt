package com.example.safedrunk.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.example.safedrunk.model.BlockPolicy
import com.example.safedrunk.model.LockRepository
class AppBlockerService : AccessibilityService() {

    private lateinit var repository: LockRepository
    private var allowedPackages: Set<String> = emptySet()
    private var lastBlockTime = 0L

    override fun onServiceConnected() {
        super.onServiceConnected()
        repository = LockRepository.get(this)
        allowedPackages = AllowedApps.resolve(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        val packageName = event.packageName?.toString() ?: return

        val now = System.currentTimeMillis()
        val session = repository.session.value

        if (session.isExpired(now)) {
            repository.finish()
            return
        }
        if (!session.isActive) return
        if (!BlockPolicy.isBlocked(packageName, allowedPackages)) return

        if (now - lastBlockTime < 800L) return
        lastBlockTime = now

        blockCurrentApp()
    }

    private fun blockCurrentApp() {
        performGlobalAction(GLOBAL_ACTION_HOME)


        try {
            packageManager.getLaunchIntentForPackage(packageName)?.let { intent ->
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
            }
        } catch (_: Exception) {

        }
    }

    override fun onInterrupt() = Unit


}
