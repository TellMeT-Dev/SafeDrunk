package com.example.safedrunk.viewmodel

import com.example.safedrunk.model.LockDuration

data class LockUiState(
    val selectedDuration: LockDuration = LockDuration.H_1,
    val isLockActive: Boolean = false,
    val remainingMillis: Long = 0L,
    val isBlockerEnabled: Boolean = false
) {
    val remainingText: String
        get() {
            val totalSeconds = (remainingMillis + 999L) / 1000L
            val hours = totalSeconds / 3600
            val minutes = (totalSeconds % 3600) / 60
            val seconds = totalSeconds % 60
            return if (hours > 0) {
                "%d:%02d:%02d".format(hours, minutes, seconds)
            } else {
                "%02d:%02d".format(minutes, seconds)
            }
        }
}
