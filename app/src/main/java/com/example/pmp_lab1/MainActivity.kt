package com.example.pmp_lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmp_lab1.model.Planet
import com.example.pmp_lab1.ui.theme.Pmp_lab1Theme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.pmp_lab1.dataSource.DataSource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pmp_lab1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlanetsApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetsApp(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Transparent
                ),
                title = {
                    Text(
                        text = "Planets",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp
                    )
                },
                modifier = modifier.background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(53,125,227,225),
                            Color(21,201,252,255)
                        )
                    ))
            )
        }
    )
    { paddingValues ->
        PlanetList(
            planets = DataSource().getData(),
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
fun PlanetList(planets: List<Planet>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .background(Color(112,105,179,255))
    ){
        items(items = planets, itemContent = {planet -> 
            PlanetCard(
                planet = planet,
                modifier = Modifier.padding(8.dp)
            )
        })
    }
}

@Composable
fun PlanetCard(planet: Planet, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {

        Box(
            Modifier
                .padding(start = (planetSize / 2 + 10).dp, end = 20.dp)
                .graphicsLayer(
                    clip = true, // Enable clipping
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 50.0f
                )
                .border(
                    0.dp, Color(134, 132, 229, 255),
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .height((planetSize * 4 / 3).dp)
                .background(Color(134, 132, 229, 255))
        ) {
            Column(Modifier
                .padding(start = (planetSize / 2 + 10).dp)
            ){
                Text(
                    text = planet.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 6.dp)
                )
                Text(
                    text = planet.galaxy,
                    color = Color(158,159,236,255),
                    modifier = Modifier
                        .padding(top = 6.dp, bottom = 6.dp),
                )

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .width(30.dp)
                        .background(Color(82,158,234,255))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 6.dp)
                ){
                    Row {
                        Image(
                            painter = painterResource(R.drawable.geolocation2),
                            contentDescription = planet.name,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .width(12.dp)
                                .height(16.dp)
                        )
                        Text(
                            text = parseDistance(planet.sunDistanceKm),
                            color = Color(158,159,236,255),
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp
                        )
                    }

                    Row(
                       modifier = Modifier
                           .align(Alignment.CenterEnd)
                           .padding(end = 20.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.falling_man),
                            contentDescription = planet.name,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .width(17.dp)
                                .height(17.dp)
                        )
                        Text(
                            text = "${planet.gravityAcceleration}m/s²",
                            color = Color(158,159,236,255),
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Image(
            painter = painterResource(planet.imageResId),
            contentDescription = planet.name,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(planetSize.dp)
                .align(Alignment.CenterStart)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetPreview() {
    Pmp_lab1Theme {
        PlanetCard(
            Planet(
                name ="Earth",
                imageResId = R.drawable.venus_j,
                galaxy = "Milky Way",
                sunDistanceKm = 150.0,
                gravityAcceleration = 9.8
            )
        )
    }
}

private fun parseDistance(distanceKm: Double): String {
    if(distanceKm < 1000.0) return "${String.format("%.1f",distanceKm)} Km"

    val distanceThousandKm = distanceKm / 1000.0
    if(distanceThousandKm < 1000.0) return "${String.format("%.1f",distanceThousandKm)}t Km"

    val distanceMillionKm = distanceThousandKm / 1000.0
    if(distanceMillionKm < 1000.0) return "${String.format("%.1f",distanceMillionKm)}m Km"

    val distanceBillionKm = distanceMillionKm / 1000.0
    return "${String.format("%.1f",distanceBillionKm)}b Km"
}

private const val planetSize: Int = 100