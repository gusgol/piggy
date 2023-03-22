package me.goldhardt.piggy.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.goldhardt.piggy.data.ExpenseRepository
import me.goldhardt.piggy.data.DefaultExpenseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsExpenseRepository(
        expenseRepository: DefaultExpenseRepository
    ): ExpenseRepository
}

class FakeExpenseRepository @Inject constructor() : ExpenseRepository {
    override val expenses: Flow<List<String>> = flowOf(fakeExpenses)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeExpenses = listOf("One", "Two", "Three")