package me.goldhardt.piggy.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun POutlinedBox(
    modifier: Modifier = Modifier,
    onBoxClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .height(56.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                onBoxClicked()
            }
            .then(modifier)
    ) {
        content()
    }
}