package com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_password")
data class SavedPassword(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val accountName: String,
    val username: String,
    val password: String
)
