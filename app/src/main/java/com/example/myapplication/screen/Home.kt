package com.example.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.WeatherViewModel
import com.example.myapplication.util.weather.getWeatherIcon
import com.example.myapplication.util.weather.getClothingSuggestion

@Composable
fun Home(viewModel: WeatherViewModel = viewModel()) {
    val weather by viewModel.weather.collectAsState()

    // 页面首次加载时自动触发天气请求
    LaunchedEffect(Unit) {
        viewModel.fetchMelbourneWeather()
    }

    // 使用 Box 包裹整个内容居中显示
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (weather != null) {
            // 提取天气相关信息
            val city = weather!!.name
            val temperature = weather!!.main.temp
            val feelsLike = weather!!.main.feels_like
            val pressure = weather!!.main.pressure
            val humidity = weather!!.main.humidity
            val windSpeed = weather!!.wind.speed
            val windDeg = weather!!.wind.deg
            val visibility = weather!!.visibility
            val description = weather!!.weather.first().description
            val iconResId = getWeatherIcon(description)
            val clothingSuggestion = getClothingSuggestion(temperature, description)

            // 主体布局
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 天气卡片
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = city,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Image(
                            painter = painterResource(id = iconResId),
                            contentDescription = description.replaceFirstChar { it.uppercase() },
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${temperature}°C",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            text = description.replaceFirstChar { it.uppercase() },
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        } else {
            // 加载中显示转圈
            CircularProgressIndicator()
        }
    }
}