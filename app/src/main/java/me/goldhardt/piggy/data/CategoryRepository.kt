package me.goldhardt.piggy.data

import kotlinx.coroutines.flow.Flow
import me.goldhardt.piggy.data.local.database.Category
import me.goldhardt.piggy.data.local.database.CategoryDao
import javax.inject.Inject

interface CategoryRepository {
    val categories: Flow<List<Category>>

    suspend fun add(category: Category)
}

class DefaultCategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override val categories: Flow<List<Category>> =
        categoryDao.getCategories()

    override suspend fun add(category: Category) {
        categoryDao.insertCategory(category)
    }
}