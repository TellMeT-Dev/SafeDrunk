package com.example.safedrunk.model

/**
 * Sesión de bloqueo. Se guarda la HORA DE FIN (no un contador): así el tiempo
 * restante se calcula siempre bien aunque la app se cierre o se rote la pantalla.
 */
data class LockSession(
    val isActive: Boolean = false,
    val startTimeMillis: Long = 0L,
    val endTimeMillis: Long = 0L
) {
    /** Milisegundos que faltan para terminar (0 si no hay bloqueo activo). */
    fun remainingMillis(now: Long): Long =
        if (!isActive) 0L else (endTimeMillis - now).coerceAtLeast(0L)

    /** ¿Ya se cumplió el tiempo de un bloqueo que seguía marcado como activo? */
    fun isExpired(now: Long): Boolean = isActive && now >= endTimeMillis

    companion object {
        fun starting(duration: LockDuration, now: Long): LockSession =
            LockSession(isActive = true, startTimeMillis = now, endTimeMillis = now + duration.millis)
    }
}
