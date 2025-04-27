package com.zoroxnekko.textinputvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.zoroxnekko.textinputvalidation.text_input.TextInputScreenRoot
import com.zoroxnekko.textinputvalidation.ui.theme.TextInputValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextInputValidationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    TextInputScreenRoot(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

