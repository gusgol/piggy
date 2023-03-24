package me.goldhardt.piggy.ui.categories.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.goldhardt.piggy.data.CategoryRepository
import me.goldhardt.piggy.data.local.database.Category
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val uiState: StateFlow<CategoryUiState> = categoryRepository
        .categories.map(CategoryUiState::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoryUiState.Loading)
}

sealed interface CategoryUiState {
    object Loading : CategoryUiState
    data class Error(val throwable: Throwable) : CategoryUiState
    data class Success(val data: List<Category>) : CategoryUiState
}