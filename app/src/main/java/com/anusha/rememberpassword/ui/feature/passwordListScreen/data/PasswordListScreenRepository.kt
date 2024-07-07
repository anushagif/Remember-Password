package com.anusha.rememberpassword.ui.feature.passwordListScreen.data

import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword
import kotlinx.coroutines.flow.Flow

interface PasswordListScreenRepository {
    fun getAllPasswords(): Flow<List<SavedPassword>>
    fun getPasswordById(id: Int): Flow<SavedPassword?>
    suspend fun insertPassword(password: SavedPassword)
    suspend fun updatePassword(password: SavedPassword)
    suspend fun deletePassword(id: Int)
    suspend fun deletePasswordByAccountName(accountName: String)
}