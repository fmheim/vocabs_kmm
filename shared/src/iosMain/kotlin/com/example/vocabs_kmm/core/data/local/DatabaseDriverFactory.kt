package com.example.vocabs_kmm.core.data.local

import com.example.vocabs_kmm.database.FlashcardDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(FlashcardDatabase.Schema, "flashcards.db")
    }
}