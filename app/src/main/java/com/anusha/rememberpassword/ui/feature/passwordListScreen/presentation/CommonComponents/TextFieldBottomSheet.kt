package com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.CommonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper.PasswordListUiState
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword
import com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.PasswordListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBottomSheetScreen(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    viewModel: PasswordListViewModel,
    uiState: PasswordListUiState
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var accountTextFieldState by remember { mutableStateOf(uiState.savedPassword?.accountName) }
    var passwordTextFieldState by remember { mutableStateOf(uiState.savedPassword?.password) }
    var usernameTextFieldState by remember { mutableStateOf(uiState.savedPassword?.username) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    ModalBottomSheet(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.SheetPeekHeight }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.padding(24.dp)
        ) {

            OutlinedTextField(
                value = accountTextFieldState ?: "",
                onValueChange = { newValue ->
                    accountTextFieldState = newValue
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Account Name") },
                textStyle = MaterialTheme.typography.bodyLarge,
            )

            OutlinedTextField(
                value = usernameTextFieldState ?: "",
                onValueChange = { newValue ->
                    usernameTextFieldState = newValue
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Username/ Email") },
                textStyle = MaterialTheme.typography.bodyLarge,
            )

            OutlinedTextField(
                value = passwordTextFieldState ?: "",
                onValueChange = { newValue ->
                    passwordTextFieldState = newValue
                },
                trailingIcon = {
                    var image = if (passwordVisible)
                        Icons.Filled.Face
                    else Icons.Filled.Lock
                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(imageVector  = image, "")
                    }}
                ,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onDismiss() }),
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                textStyle = MaterialTheme.typography.bodyLarge,
            )
        Button(
            onClick = {
                passwordTextFieldState?.let {
                    accountTextFieldState?.let { it1 ->
                        usernameTextFieldState?.let { it2 ->
                            SavedPassword(
                                accountName = it1,
                                password = it,
                                username = it2
                            )
                        }
                    }
                }
                    ?.let { viewModel.insertPassword(it) }
                onDismiss()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = accountTextFieldState?.isNotEmpty() == true && passwordTextFieldState?.isNotEmpty() == true && usernameTextFieldState?.isNotEmpty() == true,
            colors = ButtonColors(
                containerColor = Color.Black,
                contentColor = Color.White,
                disabledContainerColor = Color.Black,
                disabledContentColor = Color.White
            ),
            content = { Text(text = "Add New Account") }
        )

    }
}
}