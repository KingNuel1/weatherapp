package com.example.weatherapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.example.weatherapp.data.DayForecast
import com.example.weatherapp.data.ForecastTemp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ForecastScreen(navController: NavController) {
    val forecastItems = listOf(
        DayForecast(
            date = 1688675471L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(50F, 25F, 70F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1688761871L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(52F, 27F, 72F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1688848271L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(54F, 28F, 73F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1688934671L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(56F, 29F, 74F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689021071L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(56F, 30F, 75F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689107471L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(58F, 31F, 76F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689193871L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(60F, 32F, 77F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689280271L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(62F, 33F, 78F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689366671L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(64F, 34F, 79F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689453071L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(68F, 35F, 80F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689539471L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(70F, 36F, 81F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689625871L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(72F, 37F, 82F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689712271L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(74F, 38F, 83F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689798671L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(76F, 39F, 84F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689885071L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(78F, 40F, 85F),
            pressure = 1000f,
            humidity = 50
        ),
        DayForecast(
            date = 1689971471L,
            sunrise = 1688727611L,
            sunset = 1688783411L,
            temp = ForecastTemp(80F, 41F, 86F),
            pressure = 1000f,
            humidity = 50
        ),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = stringResource(R.string.Forecast),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier.weight(0.5f),
            contentAlignment = Alignment.Center
        ) {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(forecastItems) { dayForecast ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Column {
                            Image(
                                painter = painterResource(R.drawable.weather_icon),
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Column {
                            Text(
                                text = formatTimestampToDate(dayForecast.date),
                                style = MaterialTheme.typography.headlineLarge,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column {
                            Text(
                                text = "Temp: ${dayForecast.temp.day}º",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "High: ${dayForecast.temp.max}º   Low: ${dayForecast.temp.min}º",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column {
                            Text(
                                text = "Sunrise: ${formatTimestampToTime(dayForecast.sunrise)}",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Sunset: ${formatTimestampToTime(dayForecast.sunset)}",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
        Surface(
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = { navController.navigate("WeatherScreen") }
            ) {
                Text(
                    text = "Back",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

private fun formatTimestampToDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd", Locale.getDefault())
    val date = Date(timestamp * 1000L)
    return sdf.format(date)
}

private fun formatTimestampToTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    val date = Date(timestamp * 1000L)
    return sdf.format(date)
}

@Preview(showBackground = true)
@Composable
fun ForecastScreenPreview() {
    val navController = rememberNavController()
    ForecastScreen(navController = navController)
}