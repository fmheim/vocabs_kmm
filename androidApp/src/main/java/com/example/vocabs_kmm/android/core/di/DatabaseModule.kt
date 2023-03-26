package com.example.vocabs_kmm.android.core.di

import android.app.Application
import com.example.vocabs_kmm.core.data.flashcard.SqlDelightFlashCardDataSource
import com.example.vocabs_kmm.core.data.local.DatabaseDriverFactory
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.database.FlashcardDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): FlashcardDataSource {
        return SqlDelightFlashCardDataSource(FlashcardDatabase(driver))
    }
}