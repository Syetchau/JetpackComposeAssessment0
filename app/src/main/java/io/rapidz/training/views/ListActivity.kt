package io.rapidz.training.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dagger.hilt.android.AndroidEntryPoint
import io.rapidz.jetpackcomposetraining_assignment0.R
import io.rapidz.training.models.ActionItem
import io.rapidz.training.theme.Color_List_Bg
import io.rapidz.training.theme.Color_Login_Btn
import io.rapidz.training.theme.FONT_SIZE_14
import io.rapidz.training.theme.FONT_SIZE_16
import io.rapidz.training.theme.FONT_SIZE_20
import io.rapidz.training.theme.FONT_WEIGHT_600
import io.rapidz.training.theme.HEIGHT_50
import io.rapidz.training.theme.SPACING_20
import io.rapidz.training.theme.SPACING_32
import io.rapidz.training.theme.SPACING_4
import io.rapidz.training.theme.SPACING_8
import io.rapidz.training.utils.UiUtils.goToNextActivity

enum class Type {
    Random,
    ASC,
    DES,
    NO,
    TXT,
    NONE
}

@AndroidEntryPoint
class ListActivity:  ComponentActivity() {

    companion object {
        private val ACTION_LIST = listOf(
            ActionItem(Type.Random, "Random"),
            ActionItem(Type.ASC, "Ascending Order"),
            ActionItem(Type.DES, "Descending Order"),
            ActionItem(Type.NO, "Number Only"),
            ActionItem(Type.TXT, "Word Only")
        )

        private var dataList = listOf(
            "9384",
            "Android",
            "Good",
            "To",
            "3032",
            "Image",
            "9091",
            "Programming",
            "Coding",
            "1298",
            "9947",
            "8732",
            "iOS",
            "Mobile"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListScreen()
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun ListScreen() {

        var selectedChipIndex by remember { mutableIntStateOf(-1) }
        var type by remember { mutableStateOf(Type.NONE) }

        Column(modifier = Modifier.padding(SPACING_20)) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(SPACING_8),
                verticalArrangement = Arrangement.spacedBy(SPACING_4),
            ) {
                ACTION_LIST.forEachIndexed { index, item ->
                    Chip(
                        label = item.text,
                        isSelected = index == selectedChipIndex,
                        onClickEvent = {
                            selectedChipIndex = index
                            type = item.key
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(SPACING_20))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(SPACING_8)
            ) {
                val sortedList = performSort(type)
                items(sortedList.size) { index ->
                    Card(label = sortedList[index])
                }
            }
            ImageButton(label = getString(R.string.action_image), onClickEvent = {
                goToNextActivity(Intent(this@ListActivity, ImageActivity::class.java))
            })
        }
    }

    private fun performSort(type: Type): List<String> {
        return when (type) {
            Type.Random -> dataList.shuffled()
            Type.ASC -> dataList.sortedBy { if (it.toIntOrNull() != null) it.toInt() else Int.MAX_VALUE }
            Type.DES -> dataList.sortedByDescending { if (it.toIntOrNull() != null) it.toInt() else Int.MIN_VALUE }
            Type.NO -> dataList.filter { it.toIntOrNull() != null }.sorted()
            Type.TXT -> dataList.filter { it.toIntOrNull() == null }.sorted()
            else -> dataList
        }
    }
}

@Composable
fun Chip(label: String, isSelected: Boolean = false, onClickEvent: () -> Unit) {
    FilterChip(
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = Color.LightGray,
            selectedBorderColor = Color_List_Bg
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.LightGray,
            labelColor = Color.Black,
            selectedContainerColor = Color_List_Bg,
            selectedLabelColor = Color.White
        ),
        shape = CircleShape,
        selected = isSelected,
        onClick = onClickEvent,
        label = {
            Text(text = label, fontSize = FONT_SIZE_14)
        })
}

@Composable
fun Card(label: String) {
    Card(
        modifier = Modifier
            .height(HEIGHT_50)
            .fillMaxWidth(),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color_List_Bg,
            contentColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = SPACING_32),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = label, fontSize = FONT_SIZE_20, textAlign = TextAlign.Start)
        }
    }
}

@Composable
fun ImageButton(label:String, onClickEvent: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = SPACING_8)
    ) {
        Button(
            modifier = Modifier.padding(horizontal = SPACING_32),
            shape = RoundedCornerShape(SPACING_8),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color_Login_Btn,
                containerColor = Color_Login_Btn
            ),
            content = {
                Text(
                    text = label,
                    fontWeight = FontWeight(FONT_WEIGHT_600),
                    fontSize = FONT_SIZE_16,
                    color = Color.White,
                )
            },
            onClick = onClickEvent
        )
    }
}