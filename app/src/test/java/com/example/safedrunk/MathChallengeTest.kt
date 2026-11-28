package com.example.safedrunk

import com.example.safedrunk.model.ChallengeGenerator
import com.example.safedrunk.model.MathChallenge
import com.example.safedrunk.model.Operator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class MathChallengeTest {

    @Test
    fun `calcula la respuesta de cada operacion`() {
        assertEquals(66, MathChallenge(50, 16, Operator.PLUS).answer)
        assertEquals(34, MathChallenge(50, 16, Operator.MINUS).answer)
        assertEquals(48, MathChallenge(6, 8, Operator.TIMES).answer)
    }

    @Test
    fun `muestra el ejercicio como texto`() {
        assertEquals("50 + 16", MathChallenge(50, 16, Operator.PLUS).text)
    }

    @Test
    fun `valida respuestas correctas e incorrectas`() {
        val challenge = MathChallenge(50, 16, Operator.PLUS)
        assertTrue(challenge.isCorrect("66"))
        assertFalse(challenge.isCorrect("65"))
        assertFalse(challenge.isCorrect(""))
        assertFalse(challenge.isCorrect("abc"))
    }

    @Test
    fun `el generador siempre da respuestas positivas de maximo 3 digitos`() {
        val random = Random(42)
        repeat(1_000) {
            val answer = ChallengeGenerator.generate(random).answer
            assertTrue("respuesta fuera de rango: $answer", answer in 1..999)
        }
    }
}
