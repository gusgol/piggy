package me.goldhardt.piggy.ui.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.goldhardt.piggy.data.ExpenseRepository
import me.goldhardt.piggy.ui.expenses.ExpenseUiState.Success
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ViewModel() {

    val uiState: StateFlow<ExpenseUiState> = expenseRepository
        .expenses.map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseUiState.Loading)

    fun addExpense(name: String) {
        viewModelScope.launch {
            expenseRepository.add(name)
        }
    }
}

sealed interface ExpenseUiState {
    object Loading : ExpenseUiState
    data class Error(val throwable: Throwable) : ExpenseUiState
    data class Success(val data: List<String>) : ExpenseUiState
}