package com.example.safedrunk.model

data class LockSession(
    val isActive: Boolean = false,
    val startTimeMillis: Long = 0L,
    val endTimeMillis: Long = 0L
) {

    fun remainingMillis(now: Long): Long =
        if (!isActive) 0L else (endTimeMillis - now).coerceAtLeast(0L)


    fun isExpired(now: Long): Boolean = isActive && now >= endTimeMillis

    companion object {
        fun starting(duration: LockDuration, now: Long): LockSession =
            LockSession(isActive = true, startTimeMillis = now, endTimeMillis = now + duration.millis)
    }
}
