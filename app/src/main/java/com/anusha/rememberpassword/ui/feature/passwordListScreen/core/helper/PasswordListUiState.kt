package com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper

import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword

data class PasswordListUiState(
    val passwords: List<SavedPassword> = emptyList(),
    val accountName: String? = null,
    val isLoading: Boolean = false,
    val password: String? = null,
    val username: String? = null,
    val savedPassword: SavedPassword? = null

)
