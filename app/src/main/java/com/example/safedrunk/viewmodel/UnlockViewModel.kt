package com.example.safedrunk.viewmodel

import androidx.lifecycle.ViewModel
import com.example.safedrunk.model.MathChallenge
import com.example.safedrunk.model.ChallengeGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UnlockViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(UnlockUiState(challenge = ChallengeGenerator.generate()))
    val uiState: StateFlow <UnlockUiState> = _uiState.asStateFlow()

    fun onDigit(digit: Int){
        _uiState.update { state ->
            if (state.input.length >= MathChallenge.MAX_ANSWER_DIGITS){
                state
            }
            else {
                state.copy(input = state.input + digit, hasError = false)
            }
        }
    }

    fun onDelete(){
        _uiState.update { it.copy(input = it.input.dropLast(1), hasError = false) }
    }

    fun onConfirm(){
        val state = _uiState.value
        if(state.input.isEmpty() || state.isSolved){
            return
        }
        if (state.challenge.isCorrect(state.input)){
            _uiState.update { it.copy(isSolved = true, hasError = false) }
        } else {
            _uiState.update { UnlockUiState(
                challenge = ChallengeGenerator.generate(),
                hasError = true,
                failedAttempts = it.failedAttempts + 1
            ) }
        }
    }
}
