package com.example.myapplication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Weather & Outfit Assistant",
                fontSize = 22.sp,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Simulated weather information
            Text(text = "📍 Melbourne", fontSize = 18.sp)
            Text(text = "🌤 18°C, Partly Cloudy", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(24.dp))

            Divider()

            Spacer(modifier = Modifier.height(24.dp))

            // Simulated outfit suggestions
            Text(
                text = "👕 Outfit Suggestion",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "- Light jacket", fontSize = 16.sp)
            Text(text = "- Long jeans", fontSize = 16.sp)
            Text(text = "- Umbrella (optional)", fontSize = 16.sp)

            Spacer(modifier = Modifier.height(48.dp))

            Button(onClick = { /* future: refresh weather */ }) {
                Text("Refresh")
            }
        }
    }

}