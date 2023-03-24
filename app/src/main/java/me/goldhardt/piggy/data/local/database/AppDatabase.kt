package me.goldhardt.piggy.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Expense::class,
        Category::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
}