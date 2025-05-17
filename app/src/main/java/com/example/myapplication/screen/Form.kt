package com.example.myapplication.screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var dateText by remember { mutableStateOf("Select a date") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            dateText = "$d/${m + 1}/$y"
        },
        year, month, day
    )

    val outfitOptions = listOf("Casual", "Business", "Sporty", "Formal", "Other")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("Choose outfit type") }
    var noteText by remember { mutableStateOf("") }
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
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Log Today's Outfit", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { datePickerDialog.show() }) {
                Text(text = dateText)
            }

            Spacer(modifier = Modifier.height(24.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedOptionText,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Outfit Type") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    outfitOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = noteText,
                onValueChange = { noteText = it },
                label = { Text("Additional Notes") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {/*submit function*/
            }) {
                Text("Submit")
            }
        }
    }
}