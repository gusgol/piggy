package me.goldhardt.piggy.ui.categories.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView
import me.goldhardt.piggy.ui.categories.CategoryColor
import me.goldhardt.piggy.ui.categories.ColorPicker
import me.goldhardt.piggy.ui.components.POutlinedBox
import me.goldhardt.piggy.ui.components.POutlinedTextField
import me.goldhardt.piggy.ui.theme.PiggyTheme

internal enum class CreateCategoryStep {
    Form, EmojiPicker, ColorPicker
}

@Composable
fun CreateCategoryScreen() {
    var currentStep: CreateCategoryStep by remember { mutableStateOf(CreateCategoryStep.Form) }
    var categoryName by remember { mutableStateOf("") }
    var emoji: String by remember { mutableStateOf("\uD83D\uDCB0") }
    var color: CategoryColor by remember { mutableStateOf(CategoryColor.values().first()) }

    when (currentStep) {
        CreateCategoryStep.Form -> {
            CreateCategoryForm(categoryName, emoji, color,
                onCategoryNameChanged = {
                    categoryName = it
                },
                onEmojiFieldClicked = {
                    currentStep = CreateCategoryStep.EmojiPicker
                },
                onColorFieldClicked = {
                    currentStep = CreateCategoryStep.ColorPicker
                }
            )
        }
        CreateCategoryStep.EmojiPicker -> EmojiPicker(
            onEmojiSelected = {
                emoji = it
                currentStep = CreateCategoryStep.Form
            }
        )
        CreateCategoryStep.ColorPicker -> {
            ColorPicker(
                defaultColor = color,
                onColorSelected = {
                    color = it
                    currentStep = CreateCategoryStep.Form
                }
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CreateCategoryForm(
    categoryName: String,
    emoji: String,
    color: CategoryColor,
    onCategoryNameChanged: (String) -> Unit,
    onEmojiFieldClicked: () -> Unit,
    onColorFieldClicked: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = "Create a new category",
                style = MaterialTheme.typography.bodyLarge
            )
        })
        POutlinedTextField(
            value = categoryName,
            onValueChange = { onCategoryNameChanged(it) },
            label = "Name",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            POutlinedBox(
                modifier = Modifier.weight(1f),
                onBoxClicked = onEmojiFieldClicked
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = emoji,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            POutlinedBox(
                modifier = Modifier.weight(5f),
                onBoxClicked = onColorFieldClicked
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(android.graphics.Color.parseColor(color.hexCode)))
                ) {
                    Text(
                        text = "Color",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun EmojiPicker(
    onEmojiSelected: (String) -> Unit
) {
    AndroidView(
        modifier = Modifier
            .fillMaxSize(),
        factory = { context ->
            EmojiPickerView(context).apply {
                setOnEmojiPickedListener { selected ->
                    onEmojiSelected(selected.emoji)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    PiggyTheme {
        CreateCategoryScreen()
    }
}