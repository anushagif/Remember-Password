package com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Query("SELECT * FROM saved_password") // Capitalize table name
    fun getAllPasswords(): Flow<List<SavedPassword>> // Fetches all passwords

    @Query("SELECT * FROM saved_password WHERE id = :id")
    fun getPasswordById(id: Int): Flow<SavedPassword?> // Fetches a password by ID

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(password: SavedPassword) // Inserts a new password

    @Update
    suspend fun updatePassword(password: SavedPassword) // Updates an existing password

    @Query("DELETE FROM saved_password WHERE id = :id")
    suspend fun deletePasswordById(id: Int)

    @Query("Delete from saved_password where accountName = :accountName")
    suspend fun deletePasswordByAccountName(accountName: String)
}