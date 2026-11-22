package com.example.safedrunk.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.safedrunk.model.LockDuration
import com.example.safedrunk.model.LockRepository
import com.example.safedrunk.model.LockSession
import com.example.safedrunk.service.isBlockerServiceEnabled
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Coordina LockScreen y ActiveLockScreen. No contiene reglas de bloqueo:
 * solo traduce el estado del repositorio a [LockUiState] y reenvía las acciones.
 */
class LockViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LockRepository.get(application)
    private val selectedDuration = MutableStateFlow(LockDuration.H_1)
    private val blockerEnabled = MutableStateFlow(isBlockerServiceEnabled(application))

    private val ticker = flow {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1_000L)
        }
    }

    val uiState: StateFlow<LockUiState> = combine(
        repository.session,
        selectedDuration,
        blockerEnabled,
        ticker
    ) { session, duration, enabled, now ->
        buildState(session, duration, enabled, now)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = buildState(
            repository.session.value,
            selectedDuration.value,
            blockerEnabled.value,
            System.currentTimeMillis()
        )
    )

    init {
        viewModelScope.launch {
            while (true) {
                if (repository.session.value.isExpired(System.currentTimeMillis())) {
                    repository.finish()
                }
                delay(1_000L)
            }
        }
    }

    fun selectDuration(duration: LockDuration) {
        selectedDuration.value = duration
    }

    fun activateLock() {
        if (!blockerEnabled.value) return
        repository.start(selectedDuration.value)
    }

    fun finishLock() {
        repository.finish()
    }

    fun refreshBlockerStatus() {
        blockerEnabled.value = isBlockerServiceEnabled(getApplication<Application>())
    }

    private fun buildState(
        session: LockSession,
        duration: LockDuration,
        blockerEnabled: Boolean,
        now: Long
    ) = LockUiState(
        selectedDuration = duration,
        isLockActive = session.isActive && !session.isExpired(now),
        remainingMillis = session.remainingMillis(now),
        isBlockerEnabled = blockerEnabled
    )
}
