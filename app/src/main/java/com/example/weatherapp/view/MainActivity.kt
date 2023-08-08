package com.example.weatherapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherappTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.viewModels.CurrentConditionsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "CurrentConditions") {
                        composable("CurrentConditions") {
                            CurrentWeather(navController)
                        }
                        composable("ForecastScreen/{zip}") {
                            val zip = it.arguments?.getString("zip")
                            ForecastList(zip = zip)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeather(navController : NavController, viewModel: CurrentConditionsViewModel = hiltViewModel()){
    val currentWeather = viewModel.currentConditions.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.viewAppeared()
    }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)

        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        CurrentZipCode(viewModel)
        Text(
            text = "${currentWeather.value?.location}" ?: "NoLocation",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                Text(
                    text = "${currentWeather.value?.currentTemp?.toInt()}" + stringResource(R.string.degree),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = stringResource(R.string.feels_like) + "${currentWeather.value?.feelsLike?.toInt()}" + stringResource(R.string.degree),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherConditionIcon(url = currentWeather.value?.weatherIconUrl)

            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.Low) + "${currentWeather.value?.minTemp?.toInt()}" + stringResource(R.string.degree),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = stringResource(R.string.High) + "${currentWeather.value?.maxTemp?.toInt()}" + stringResource(R.string.degree),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = stringResource(R.string.Humidity) + "${currentWeather.value?.humidity}" + stringResource(R.string.Percent),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Text(
            text = stringResource(R.string.Pressure) + "${currentWeather.value?.pressure}" + stringResource(R.string.Pressure_unit),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp,
            modifier = Modifier.padding(start =10.dp)
        )
        Spacer(modifier = Modifier.height(35.dp))

        Surface(
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            TextButton(
                onClick = {
                    val navString = "ForecastScreen/" + viewModel.userZip.value.toString()
                    navController.navigate(route = navString)}
            ) {
                Text(
                    text = stringResource(R.string.Forecast),
                    fontSize = 30.sp
                )
            }
        }
    }
}
@Composable
fun WeatherConditionIcon( url: String?) {
    AsyncImage(model = url, contentDescription = "", modifier = Modifier.size(80.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentZipCode(viewModel: CurrentConditionsViewModel) {
    val userInput = viewModel.userZip.observeAsState()
    val showAlert = viewModel.showInvalidZipWarning.observeAsState(initial = false)
    Column {
        OutlinedTextField(
            value = userInput.value.toString(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.label)
                )
            },
            label = {
                Text(
                    text = stringResource(id = R.string.label),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Gray
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.padding(12.dp),
            onValueChange = { viewModel.userZip.value = it}
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Center) {
            Spacer(modifier = Modifier)
            Button(onClick = {
                viewModel.showInvalidZipWarning.value = !viewModel.validateZipAndUpdate()

            }) {
                Text(text = stringResource(id = R.string.get_weather))
            }
            Spacer(modifier = Modifier)
        }
    }
    if (showAlert.value) {
        InvalidZipAlert {
            viewModel.showInvalidZipWarning.value = false
        }
    }
}

@Composable
fun InvalidZipAlert(
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = onDismiss,
        confirmButton = @Composable {
            Button(onClick = onDismiss) {
                Text("Ok")
            }
        },
        title = @Composable {
            Text(stringResource(id = R.string.title))
        },
        text = @Composable {
            Text(stringResource(id = R.string.alert_body))
        }
    )

}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherPreview() {
    val navController = rememberNavController()
    WeatherappTheme {
        CurrentWeather(navController = navController)
    }
}