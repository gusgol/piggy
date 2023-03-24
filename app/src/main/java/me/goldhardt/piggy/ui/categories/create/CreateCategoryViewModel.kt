package me.goldhardt.piggy.ui.categories.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.goldhardt.piggy.data.CategoryRepository
import me.goldhardt.piggy.data.local.database.Category
import me.goldhardt.piggy.data.local.database.CategoryColor
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    fun add(name: String, emoji: String, color: CategoryColor) {
        val category = Category(
            name = name,
            emoji = emoji,
            color = color
        )
        viewModelScope.launch {
            categoryRepository.add(category)
        }
    }
}