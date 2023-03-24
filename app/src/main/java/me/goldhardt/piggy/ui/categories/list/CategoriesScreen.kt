package me.goldhardt.piggy.ui.categories.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import me.goldhardt.piggy.data.local.database.Category
import me.goldhardt.piggy.data.local.database.CategoryColor
import me.goldhardt.piggy.ui.categories.create.CreateCategoryScreen
import me.goldhardt.piggy.ui.theme.PiggyTheme

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val items by produceState<CategoryUiState>(
        initialValue = CategoryUiState.Loading,
        key1 = lifecycle,
        key2 = viewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            viewModel.uiState.collect { value = it }
        }
    }
    if (items is CategoryUiState.Success) {
        CategoriesScreen(
            (items as CategoryUiState.Success).data
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CategoriesScreen(
    categories: List<Category>,
) {
    Column {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.bodyLarge
            )
        })
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(categories) { item: Category ->
                CategoryScreen(item)
            }
        }
    }
}

@Composable
internal fun CategoryScreen(
    category: Category
) {
    Column {
        Row(
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(android.graphics.Color.parseColor(category.color.hexCode)))
            ) {
                Text(
                    text = category.emoji,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Divider(
            modifier = Modifier.padding(start = 56.dp),
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOne() {
    PiggyTheme {
        CategoryScreen(
            category = Category("Home", "\uD83C\uDFE0", CategoryColor.Apricot)
        )
    }
    PiggyTheme {
        CategoriesScreen(
            categories = listOf(
                Category("Home", "\uD83C\uDFE0", CategoryColor.Apricot),
                Category("Food", "\uD83C\uDFE0", CategoryColor.BabyBlue),
                Category("Sports", "\uD83C\uDFE0", CategoryColor.LightPink)
            )
        )
    }
}