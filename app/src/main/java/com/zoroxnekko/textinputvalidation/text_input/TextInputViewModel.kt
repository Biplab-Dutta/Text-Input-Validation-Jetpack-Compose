package com.zoroxnekko.textinputvalidation.text_input

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TextInputViewModel : ViewModel() {
    private val _state = MutableStateFlow(TextInputState())
    val state = _state
        .onStart { observeTextInput() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            TextInputState()
        )

    fun onInputChanged(input: String) {
        _state.update {
            it.copy(text = input)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeTextInput() {
        state
            .mapLatest { it.text }
            .drop(1)
            .onEach {
                val isInputValid = validateInput(it)
                _state.update {
                    it.copy(hasError = !isInputValid)
                }
            }.launchIn(viewModelScope)
    }

    private fun validateInput(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}
