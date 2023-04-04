package me.goldhardt.piggy.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.goldhardt.piggy.data.local.database.CategoryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    defaultColor: CategoryColor,
    onColorSelected: (CategoryColor) -> Unit
) {
    val pastelColors = CategoryColor.values()

    val selectedColor by remember { mutableStateOf<CategoryColor?>(defaultColor) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Pick a color",
                style = MaterialTheme.typography.bodyLarge
            )
        })

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(pastelColors.size) { index ->
                val color = pastelColors[index]
                PastelColorSquare(
                    color = Color(android.graphics.Color.parseColor(color.hexCode)),
                    isSelected = color == selectedColor,
                    modifier = Modifier.aspectRatio(1f),
                    onClick = { onColorSelected(color) }
                )
            }
        }
    }
}

@Composable
fun PastelColorSquare(
    color: Color,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clickable {
                onClick()
            },
        color = color,
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent
        ),
        shape = RoundedCornerShape(4.dp),
    ) {}
}
