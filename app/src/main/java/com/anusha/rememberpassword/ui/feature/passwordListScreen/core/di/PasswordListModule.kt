package com.anusha.rememberpassword.ui.feature.passwordListScreen.core.di


import android.content.Context
import com.anusha.rememberpassword.ui.feature.passwordListScreen.core.helper.AccountPasswordDatabase
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.PasswordListScreenRepository
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.local.PasswordDao
import com.anusha.rememberpassword.ui.feature.passwordListScreen.data.repository.PasswordListScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PasswordListModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AccountPasswordDatabase {
        return AccountPasswordDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providePasswordDao(database: AccountPasswordDatabase): PasswordDao {
        return database.passwordDao()
    }
    @Provides
    @Singleton
    fun providePasswordRepository(passwordDao: PasswordDao): PasswordListScreenRepository {
        return PasswordListScreenRepositoryImpl(passwordDao)
    }

}