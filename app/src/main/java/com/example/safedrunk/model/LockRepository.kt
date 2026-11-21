package com.example.safedrunk.model

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LockRepository private constructor(context: Context) {

    private val prefs = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val _session = MutableStateFlow(read())
    val session: StateFlow<LockSession> = _session.asStateFlow()

    fun start(duration: LockDuration, now: Long = System.currentTimeMillis()) {
        save(LockSession.starting(duration, now))
    }

    fun finish() {
        save(LockSession())
    }

    private fun read(): LockSession = LockSession(
        isActive = prefs.getBoolean(KEY_ACTIVE, false),
        startTimeMillis = prefs.getLong(KEY_START, 0L),
        endTimeMillis = prefs.getLong(KEY_END, 0L)
    )

    private fun save(session: LockSession) {
        prefs.edit()
            .putBoolean(KEY_ACTIVE, session.isActive)
            .putLong(KEY_START, session.startTimeMillis)
            .putLong(KEY_END, session.endTimeMillis)
            .apply()
        _session.value = session
    }

    companion object {
        private const val PREFS_NAME = "lock_session"
        private const val KEY_ACTIVE = "active"
        private const val KEY_START = "start"
        private const val KEY_END = "end"

        @Volatile
        private var instance: LockRepository? = null

        fun get(context: Context): LockRepository =
            instance ?: synchronized(this) {
                instance ?: LockRepository(context.applicationContext).also { instance = it }
            }
    }
}
