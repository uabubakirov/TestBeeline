package com.example.test.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.data.local.room.AppDataBase
import com.example.test.data.local.sharedpreference.PreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context):AppDataBase{
        return Room.databaseBuilder(context.applicationContext,
        AppDataBase::class.java,
        "database")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDataBase)=db.getCurrency()

    @Singleton
    @Provides
    fun provideMapDao(db: AppDataBase)=db.getMapDao()

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }
}