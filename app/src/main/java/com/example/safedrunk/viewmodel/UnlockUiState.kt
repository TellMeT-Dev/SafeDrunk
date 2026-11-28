package com.example.safedrunk.viewmodel
import com.example.safedrunk.model.MathChallenge

data class UnlockUiState(
    val challenge: MathChallenge,
    val input: String="",
    val hasError: Boolean=false,
    val failedAttempts: Int=0,
    val isSolved: Boolean=false
)