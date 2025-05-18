package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.OutfitViewModel
import com.example.myapplication.data.entity.Outfit

@Composable
fun History(viewModel: OutfitViewModel = viewModel()) {
    // 实时监听所有穿搭记录
    val outfits by viewModel.allOutfits.collectAsState(initial = emptyList())

    // 用于弹出删除确认对话框的状态
    var outfitToDelete by remember { mutableStateOf<Outfit?>(null) }

    // 页面布局
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 顶部标题
        Text(
            text = "🗂 Outfit History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 无数据提示
        if (outfits.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No outfit records yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            // 列表展示
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(outfits) { outfit ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // 顶部 Row（左侧内容，右侧删除按钮）
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text("📅 ${outfit.date}", fontWeight = FontWeight.SemiBold)
                                    Text("🎽 Style: ${outfit.style}", color = MaterialTheme.colorScheme.primary)
                                }

                                // 删除按钮
                                IconButton(onClick = {
                                    outfitToDelete = outfit // 设置待删除对象，触发对话框
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(outfit.description)
                        }
                    }
                }
            }
        }
    }

    // 删除确认对话框
    if (outfitToDelete != null) {
        AlertDialog(
            onDismissRequest = { outfitToDelete = null },
            title = { Text("Delete this record?") },
            text = { Text("Are you sure you want to delete this outfit entry? This action cannot be undone.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteOutfit(outfitToDelete!!)
                    outfitToDelete = null
                }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { outfitToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
