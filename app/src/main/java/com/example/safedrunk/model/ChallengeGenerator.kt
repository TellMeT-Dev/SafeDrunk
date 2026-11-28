package com.example.safedrunk.model

import kotlin.random.Random

object ChallengeGenerator {

    fun generate(random: Random = Random.Default): MathChallenge =
        when (Operator.entries.random(random)) {
            Operator.PLUS -> MathChallenge(random.nextInt(20, 100), random.nextInt(20, 100), Operator.PLUS)

            Operator.MINUS -> {
                val a = random.nextInt(50, 100)
                val b = random.nextInt(10, a)
                MathChallenge(a, b, Operator.MINUS)
            }

            Operator.TIMES -> MathChallenge(random.nextInt(3, 13), random.nextInt(3, 13), Operator.TIMES)
        }
}
