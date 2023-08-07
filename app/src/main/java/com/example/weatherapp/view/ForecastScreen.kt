package com.example.weatherapp.view

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.R
import com.example.weatherapp.models.DayForecast
import com.example.weatherapp.ui.theme.PurpleGrey80
import com.example.weatherapp.viewModels.ForecastViewModel
import java.text.SimpleDateFormat
import java.util.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastList(viewModel: ForecastViewModel = hiltViewModel()) {

    val multi = viewModel.forecast.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.Forecast),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.SpaceBetween) {
            items(items = multi.value?.forecastList ?: listOf()) {
                ForecastItemView(forecast = it)
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
fun ForecastItemView(forecast: DayForecast) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(0.05f)) {WeatherConditionIcon(url = forecast.weatherIconUrl) }
        Text(
            text = formatTimestampToDate(forecast.date),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.size(8.dp))
        Column(modifier = Modifier) {
            Text(
                text = stringResource(R.string.Temp) + "${forecast.daytimeTemp}"+
                        stringResource(R.string.degree),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row(modifier = Modifier) {
                Text(
                    text = stringResource(R.string.High)+"${forecast.maxTemp}" +
                            stringResource(R.string.degree),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.low)
                            +" " + " ${forecast.minTemp}" + stringResource(R.string.degree),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Column(modifier = Modifier) {
            Text(
                text = stringResource(R.string.Sunrise)+": ${formatTimestampToTime(forecast.sunrise)}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.Sunset)+": ${formatTimestampToTime(forecast.sunset)}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp
            )

        }

    }
}

@Composable
private fun formatTimestampToDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("MMM dd", Locale.getDefault())
    val date = Date(timestamp * 1000L)
    return sdf.format(date)
}
@Composable
private fun formatTimestampToTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    val date = Date(timestamp * 1000L)
    return sdf.format(date)
}
