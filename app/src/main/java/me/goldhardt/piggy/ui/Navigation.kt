package me.goldhardt.piggy.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.goldhardt.piggy.ui.PiggyScreens.CATEGORIES_SCREEN
import me.goldhardt.piggy.ui.PiggyScreens.CREATE_CATEGORY_SCREEN
import me.goldhardt.piggy.ui.PiggyScreens.CREATE_EXPENSE_SCREEN
import me.goldhardt.piggy.ui.PiggyScreens.EXPENSES_SCREEN
import me.goldhardt.piggy.ui.categories.CategoriesScreen
import me.goldhardt.piggy.ui.categories.create.CreateCategoryScreen
import me.goldhardt.piggy.ui.expenses.ExpensesScreen
import me.goldhardt.piggy.ui.expenses.create.CreateExpenseScreen

object PiggyScreens {
    // Expenses
    const val EXPENSES_SCREEN = "expenses"
    const val CREATE_EXPENSE_SCREEN = "$EXPENSES_SCREEN/create"

    // Categories
    const val CATEGORIES_SCREEN = "categories"
    const val CREATE_CATEGORY_SCREEN = "$CATEGORIES_SCREEN/create"
}

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = EXPENSES_SCREEN) {
        composable(EXPENSES_SCREEN) {
            ExpensesScreen( modifier = Modifier.padding(16.dp))
        }
        composable(CREATE_EXPENSE_SCREEN) {
            CreateExpenseScreen()
        }
        composable(CATEGORIES_SCREEN) {
            CategoriesScreen()
        }
        composable(CREATE_CATEGORY_SCREEN) {
            CreateCategoryScreen()
        }
    }
}