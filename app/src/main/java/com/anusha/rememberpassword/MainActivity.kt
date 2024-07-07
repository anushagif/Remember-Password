package com.anusha.rememberpassword

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.anusha.rememberpassword.ui.NavigationScreen
import com.anusha.rememberpassword.ui.theme.RememberPasswordTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)
        setContent {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            RememberPasswordTheme {
                NavigationScreen() // Pass the ViewModel
            }
        }
    }
}