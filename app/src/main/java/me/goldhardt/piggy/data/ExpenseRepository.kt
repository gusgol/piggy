package me.goldhardt.piggy.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.goldhardt.piggy.data.local.database.Expense
import me.goldhardt.piggy.data.local.database.ExpenseDao
import javax.inject.Inject

interface ExpenseRepository {
    val expenses: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    override val expenses: Flow<List<String>> =
        expenseDao.getExpenses().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        expenseDao.insertExpense(Expense(name = name))
    }
}