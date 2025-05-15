package com.example.weatheroutfitassistant.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// Data class for outfit record
data class OutfitRecord(
    val date: String,
    val type: String,
    val notes: String
)

// Simulated history data (all notes in English)
val sampleHistory = listOf(
    OutfitRecord("10/04/2025", "Casual", "Wore a T-shirt and jeans on a sunny day."),
    OutfitRecord("11/04/2025", "Business", "Attended a meeting wearing a blazer and dress shoes."),
    OutfitRecord("12/04/2025", "Sporty", "Went for a run wearing a hoodie and sneakers."),
    OutfitRecord("13/04/2025", "Casual", "Wore a light jacket and boots due to rainy weather."),
    OutfitRecord("14/04/2025", "Formal", "Dressed formally for an evening event."),
    OutfitRecord("15/04/2025", "Other", "Experimented with a new style including a trench coat.")
)



@Composable
fun HistoryScreen() {

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
                .padding(16.dp)
        ) {
            Text("Outfit History", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleHistory) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "📅 Date: ${record.date}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "👕 Type: ${record.type}")
                            Text(text = "📝 Notes: ${record.notes}")
                        }
                    }
                }
            }
        }
    }

}