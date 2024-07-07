package com.anusha.rememberpassword.ui.feature.passwordListScreen.data.repository

import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.PasswordListScreenRepository
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.PasswordDao
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PasswordListScreenRepositoryImpl @Inject constructor(private val passwordDao: PasswordDao):
    PasswordListScreenRepository {

    override fun getAllPasswords(): Flow<List<SavedPassword>> = passwordDao.getAllPasswords().flowOn(IO)

    override fun getPasswordById(id: Int): Flow<SavedPassword?> = passwordDao.getPasswordById(id).flowOn(IO)

    override suspend fun insertPassword(password: SavedPassword) = passwordDao.insertPassword(password)

    override suspend fun updatePassword(password: SavedPassword) = passwordDao.updatePassword(password)

    override suspend fun deletePassword(id: Int) = passwordDao.deletePasswordById(id)
    override suspend fun deletePasswordByAccountName(accountName: String) = passwordDao.deletePasswordByAccountName(accountName)

}