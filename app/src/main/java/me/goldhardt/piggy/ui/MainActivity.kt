package me.goldhardt.piggy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.goldhardt.piggy.ui.theme.PiggyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PiggyTheme {
                PiggyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PiggyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppBottomBar(navController)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            MainNavigation(navController)
        }
    }
}

@Composable
fun AppBottomBar(
    navController: NavHostController
) {
    BottomAppBar(
        actions = {
            IconButton(onClick = {
                navController.navigate(PiggyScreens.EXPENSES_SCREEN)
            }) {
                Icon(Icons.Filled.Payment, contentDescription = "Expenses")
            }
            IconButton(onClick = {
                navController.navigate(PiggyScreens.CATEGORIES_SCREEN)
            }) {
                Icon(
                    Icons.Filled.Category,
                    contentDescription = "Localized description",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    getDestination(navController.currentDestination?.route)?.let {
                        navController.navigate(it)
                    }
                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    )
}

private fun getDestination(currentRoute: String?): String? {
    return when (currentRoute) {
        PiggyScreens.EXPENSES_SCREEN -> PiggyScreens.CREATE_EXPENSE_SCREEN
        PiggyScreens.CATEGORIES_SCREEN -> PiggyScreens.CREATE_CATEGORY_SCREEN
        else -> {
            null
        }
    }
}


