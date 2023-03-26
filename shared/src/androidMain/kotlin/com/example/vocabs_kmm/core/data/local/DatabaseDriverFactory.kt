package com.example.vocabs_kmm.core.data.local

import android.content.Context
import com.example.vocabs_kmm.database.FlashcardDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory (private val context: Context) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(FlashcardDatabase.Schema,context, "flashcards.db")
    }
}