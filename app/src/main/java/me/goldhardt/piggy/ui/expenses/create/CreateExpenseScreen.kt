package me.goldhardt.piggy.ui.expenses.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpenseScreen() {
    var nameExpense by remember { mutableStateOf("") }
    Column {
        CenterAlignedTopAppBar(title = {
            Text(text = "Create new expense")
        })
        TextField(
            value = nameExpense,
            onValueChange = {
                nameExpense = it
            }
        )
    }
}