package com.project.dishnary.screens.searchScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.project.dishnary.model.Items
import com.project.dishnary.ui.theme.Bisque
import com.project.dishnary.ui.theme.Coral
import com.project.dishnary.ui.theme.DishnaryTheme
import com.project.dishnary.ui.theme.Tomato
import com.project.dishnary.ui.theme.White
import com.project.dishnary.ui.theme.WhiteSmoke
import com.project.dishnary.utils.CategoryIcons

@Composable
fun ListComposable(ingredients: List<Items>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(ingredients) { item ->
            ListItemExpandable(item,emptySet(),onToggle = {})
        }
    }
}


@Composable
fun ListItemExpandable(entity: Items,selectedItems:Set<String>,
                       onToggle: (String) -> Unit) {

    var isExpanded by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp))
            .background(Coral)
            .clickable { isExpanded = !isExpanded }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp,10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                painterResource(CategoryIcons.getIconFor(entity.name)),
                contentDescription = entity.name,
                tint = Color.Unspecified,
                modifier = Modifier.size(34.dp)
            )
            Column(modifier = Modifier.padding(start = 12.dp)) {
                Text(
                    entity.name, style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 22.sp
                    ), color = WhiteSmoke
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    "${entity.items.size} items", style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 16.sp
                    ), color = WhiteSmoke
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Bisque).padding(10.dp),
                mainAxisSpacing = 14.dp,
                crossAxisSpacing = 8.dp
            ) {
                entity.items.forEach {
                    val selected = selectedItems.contains(it)
                    AssistChip(
                        onClick = {
                        if (selected) selectedItems - it else selectedItems + it
                        onToggle(it)
                    }, label = { Text(it, fontSize = 20.sp) }, colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selected) Tomato
                        else Color.White.copy(
                            0.4f
                        ),
                            labelColor = if (selected) White else Color.Black
                    )
                    )

                }

            }
        }
    }

}


@Preview
@Composable
fun Preview() {
    DishnaryTheme {
        val sample = listOf(
            Items("Veggies", listOf("Tomato", "Potato", "Onion", "Carrot")),
            Items("Fruits", listOf("Apple", "Banana", "Mango")),
            Items("Dairy", listOf("Milk", "Cheese", "Yogurt"))
        )
        ListComposable(sample)
    }
}



