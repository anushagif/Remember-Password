package com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.PasswordDao
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.SavedPassword

@Database(entities = [SavedPassword::class], version = 1)
abstract class AccountPasswordDatabase : RoomDatabase() {

    abstract fun passwordDao(): PasswordDao

    companion object {
        @Volatile
        private var instance: AccountPasswordDatabase? = null

        fun getInstance(context: Context): AccountPasswordDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context.applicationContext).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AccountPasswordDatabase {
            return Room.databaseBuilder(
                context,
                AccountPasswordDatabase::class.java,
                "saved_passwords"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}
