package com.anusha.password_manager.ui.feature.PasswordListScreen.presentation

import PasswordListItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper.PasswordListUiState

@Composable
fun PasswordListScreen(onArrowIconClick: (Int) -> Unit, uiState: PasswordListUiState) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(uiState.passwords) { savedPassword ->
            PasswordListItem(
                passwordProvider = savedPassword.accountName,
                onArrowIconClick = {onArrowIconClick(savedPassword.id)}
            )
        }
    }
}