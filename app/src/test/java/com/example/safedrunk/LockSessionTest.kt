package com.example.safedrunk

import com.example.safedrunk.model.LockDuration
import com.example.safedrunk.model.LockSession
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LockSessionTest {

    @Test
    fun `sesion nueva termina al cumplirse la duracion`() {
        val session = LockSession.starting(LockDuration.MIN_30, now = 1_000L)
        assertEquals(1_000L + 30 * 60_000L, session.endTimeMillis)
    }

    @Test
    fun `tiempo restante disminuye y nunca es negativo`() {
        val session = LockSession.starting(LockDuration.MIN_30, now = 0L)
        assertEquals(30 * 60_000L, session.remainingMillis(now = 0L))
        assertEquals(29 * 60_000L, session.remainingMillis(now = 60_000L))
        assertEquals(0L, session.remainingMillis(now = 99 * 60_000L))
    }

    @Test
    fun `solo expira una sesion activa`() {
        val active = LockSession.starting(LockDuration.MIN_30, now = 0L)
        assertFalse(active.isExpired(now = 60_000L))
        assertTrue(active.isExpired(now = 30 * 60_000L))
        assertFalse(LockSession().isExpired(now = Long.MAX_VALUE))
    }
}
