package com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.CommonComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper.PasswordListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordItemEditBottomSheet(modifier: Modifier = Modifier,
                                onDeleteDismiss: (id: Int?) -> Unit,
                                onEditClick: (id: Int) -> Unit,
                                uiState: PasswordListUiState) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        containerColor = Color.White,
        modifier = modifier
            .fillMaxSize(),
        onDismissRequest = {onDeleteDismiss(null)  },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.SheetPeekHeight }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .imePadding()
        ) {
            Text(text = "Account Details",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Blue)
            uiState.savedPassword?.let {
                TextField(
                    label = { Text("Account Type") },
                    value = it.accountName,
                    readOnly = true,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Account Name") },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        ),
                )
            }
            uiState.savedPassword?.let {
                TextField(
                    label = { Text("Username/ Email") },
                    value = it.username,
                    readOnly = true,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Account Name") },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }
            TextField(
                label = { Text("Password") },
                value = "********",
                readOnly = true,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Account Name") },
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                    )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        onEditClick(uiState.savedPassword?.id ?: 0)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Black,
                        disabledContentColor = Color.White
                    ),
                    content = { Text(text = "Edit") }
                )
                Button(
                    onClick = {
                        uiState.savedPassword?.let { onDeleteDismiss(it.id) }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Red,
                        disabledContentColor = Color.White
                    ),
                    content = { Text(text = "Delete") }
                )
            }

        }
    }
    }
