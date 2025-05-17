package com.example.myapplication.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Simulated outfit type frequency data (English labels)
val outfitStats = mapOf(
    "Casual" to 4,   // Relaxed, everyday wear
    "Business" to 2, // Office or meeting wear
    "Sporty" to 1,   // For gym or active days
    "Formal" to 1,   // For events or formal settings
    "Other" to 1     // Mixed or experimental outfits
)

@Composable
fun ReportScreen() {

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Create, contentDescription = "Form") },
                    label = { Text("Log") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "History") },
                    label = { Text("History") },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountBox, contentDescription = "Report") },
                    label = { Text("Report") },
                    selected = selectedIndex == 3,
                    onClick = { selectedIndex = 3 }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Outfit Report",
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Section title
            Text("Outfit Type Frequency (Bar Chart)", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Bar Chart Canvas
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                val barWidth = 80f
                val spacing = 50f
                val maxHeight = size.height
                val maxCount = outfitStats.maxOf { it.value }

                outfitStats.entries.forEachIndexed { index, entry ->
                    val barHeight = (entry.value.toFloat() / maxCount) * maxHeight
                    drawRect(
                        color = Color(0xFF90CAF9),
                        topLeft = Offset(
                            x = index * (barWidth + spacing),
                            y = maxHeight - barHeight
                        ),
                        size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Legend / explanation
            Text("Legend:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            outfitStats.forEach { (type, count) ->
                val description = when (type) {
                    "Casual" -> "Relaxed daily outfit (e.g., T-shirt and jeans)"
                    "Business" -> "Office wear (e.g., blazer and dress pants)"
                    "Sporty" -> "Active wear (e.g., hoodie, sneakers)"
                    "Formal" -> "Elegant wear for formal events"
                    "Other" -> "Mixed or creative combinations"
                    else -> ""
                }
                Text("$type: $count times – $description")
            }
        }
    }

}