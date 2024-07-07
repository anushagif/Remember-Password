package com.anusha.rememberpassword.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.anusha.password_manager.ui.feature.PasswordListScreen.presentation.PasswordListScreen
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword
import com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.CommonComponents.PasswordItemEditBottomSheet
import com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.CommonComponents.TextFieldBottomSheetScreen
import com.anusha.rememberpassword.ui.feature.passwordListScreen.presentation.PasswordListViewModel
import kotlinx.serialization.Serializable

@Composable
fun NavigationScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: PasswordListViewModel = hiltViewModel()
    val uiState by viewModel.passwordListUiState.collectAsState()

    Scaffold(
        modifier = modifier.imePadding(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
               navController.navigate(TextFieldBottomSheet(-1))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = PasswordListing,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable<PasswordListing> {
                LaunchedEffect(Unit) {
                    viewModel.getAllPasswords()
                }
                PasswordListScreen(onArrowIconClick = {
                    navController.navigate(PasswordItemEditBottomSheet(passwordId = it))
                },uiState = uiState)
            }
            composable<TextFieldBottomSheet> {
                val args = it.toRoute<TextFieldBottomSheet>()
                LaunchedEffect(args.id != -1) {
                    viewModel.getPasswordDetailsById(args.id)

                }
                Box {
                    TextFieldBottomSheetScreen(onDismiss = {
                        navController.popBackStack()
                    },
                        viewModel = viewModel,
                        uiState = uiState
                    )
                }
            }
            composable<PasswordItemEditBottomSheet>{
                val args = it.toRoute<PasswordItemEditBottomSheet>()
                LaunchedEffect(key1 = Unit) {
                    viewModel.getPasswordDetailsById(args.passwordId)
                }
                PasswordItemEditBottomSheet(onDeleteDismiss = {

                        if (it != null) {
                            viewModel.deletePasswordById(it)
                        }
                    navController.popBackStack()
                }, onEditClick = {
                    navController.navigate(TextFieldBottomSheet(id = it))
                }
                    ,uiState = uiState)
            }

        }
    }
    }



@Serializable
object PasswordListing

@Serializable
data class TextFieldBottomSheet(val id:Int)

@Serializable
data class PasswordItemEditBottomSheet(val passwordId: Int)