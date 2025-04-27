package com.zoroxnekko.textinputvalidation.text_input

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TextInputScreenRoot(
    modifier: Modifier = Modifier,
) {
    val textInputViewModel = viewModel<TextInputViewModel>()
    val state by textInputViewModel.state.collectAsStateWithLifecycle()
    TextInputScreen(
        modifier = modifier,
        state = state,
        onValueChange = textInputViewModel::onInputChanged,
    )

}

@Composable
private fun TextInputScreen(
    modifier: Modifier = Modifier,
    state: TextInputState,
    onValueChange: (String) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = state.text,
            onValueChange = onValueChange,
            label = { Text("Email") },
            isError = state.hasError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            supportingText = {
                if (state.hasError && state.text.isEmpty()) {
                    Text("Enter an email")
                }

                if (state.hasError && state.text.isNotEmpty()) {
                    Text("Invalid email")
                }
            }
        )

    }
}