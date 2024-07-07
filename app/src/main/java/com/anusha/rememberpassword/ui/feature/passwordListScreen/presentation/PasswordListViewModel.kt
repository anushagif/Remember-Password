package com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper.PasswordListUiState
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.PasswordListScreenRepository
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordListViewModel @Inject constructor(private val passwordListScreenRepository: PasswordListScreenRepository) : ViewModel() {
    private val _passwordListUiState = MutableStateFlow(PasswordListUiState())
    val passwordListUiState = _passwordListUiState.asStateFlow()

     fun insertPassword(password: SavedPassword) {
         viewModelScope.launch {
             passwordListScreenRepository.insertPassword(password)
         }
    }

    fun deletePassword(accName: String){
        viewModelScope.launch {
            passwordListScreenRepository.deletePasswordByAccountName(accName)
        }
    }
    fun deletePasswordById(id: Int) {
        viewModelScope.launch {
            passwordListScreenRepository.deletePassword(id)
        }
    }

    fun getAllPasswords() {
        viewModelScope.launch {
            passwordListScreenRepository.getAllPasswords().collect { passwords ->
                _passwordListUiState.value = _passwordListUiState.value.copy(passwords = passwords)
            }
        }
    }

    fun getPasswordDetailsById(id: Int){
        viewModelScope.launch {
            passwordListScreenRepository.getPasswordById(id).collect { password ->
                password?.let {
                    _passwordListUiState.value =
                        _passwordListUiState.value.copy(savedPassword = password)
                }
            }
        }
    }

}

