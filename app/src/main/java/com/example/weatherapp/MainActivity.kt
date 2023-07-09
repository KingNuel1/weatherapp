package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherappTheme


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
                    WeatherApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun WeatherApp(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.location),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.temperature),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = stringResource(R.string.feels_like),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(R.drawable.weather_icon),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(R.string.Low),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.High),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.Humidity),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(R.string.Pressure),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherappTheme {
        Greeting("Android")
    }
}