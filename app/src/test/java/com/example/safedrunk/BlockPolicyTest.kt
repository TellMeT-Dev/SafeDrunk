package com.example.safedrunk

import com.example.safedrunk.model.BlockPolicy
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BlockPolicyTest {

    private val allowed = setOf("com.example.safedrunk", "com.google.android.apps.nexuslauncher")

    @Test
    fun `una app que no esta permitida se bloquea`() {
        assertTrue(BlockPolicy.isBlocked("com.instagram.android", allowed))
    }

    @Test
    fun `la propia app y el launcher no se bloquean`() {
        assertFalse(BlockPolicy.isBlocked("com.example.safedrunk", allowed))
        assertFalse(BlockPolicy.isBlocked("com.google.android.apps.nexuslauncher", allowed))
    }

    @Test
    fun `los paquetes del sistema no se bloquean`() {
        assertFalse(BlockPolicy.isBlocked("com.android.systemui", allowed))
    }

    @Test
    fun `ajustes se bloquea para que no puedan apagar el servicio`() {
        assertTrue(BlockPolicy.isBlocked("com.android.settings", allowed))
    }
}
