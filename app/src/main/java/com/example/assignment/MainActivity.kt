package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.ui.theme.AssignmentTheme

data class Stop(val id: Int, val name: String, val distanceInKm: Double, val distanceInMiles: Double)

data class Route(val stops: List<Stop>, val startPoint: Stop, val endPoint: Stop)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    main(0)
                }
            }
        }
    }
}

fun getStops(): List<Stop> {
    return listOf(
        Stop(1, "Bangalore", 0.0, 0.0),
        Stop(2, "Hyderabad", 1.0, 0.62),
        Stop(3, "Ahmedabad", 2.0, 2.0*0.62),
        Stop(4, "Kolkata", 3.0, 3.0*0.62),
        Stop(5, "Surat", 4.0, 4.0*0.62),
        Stop(6, "Pune", 5.0, 5.0*0.62),
        Stop(7, "Jaipur", 6.0, 6.0*0.62),
        Stop(8, "Lucknow", 7.0, 7.0*0.62),
        Stop(9, "Kanpur", 8.0, 8.0*0.62),
        Stop(10, "Nagpur", 9.0, 9.0*0.62),
        Stop(11, "Indore", 10.0, 10.0*0.62),
        Stop(12, "Thane", 11.0, 11.0*0.62),
        Stop(13, "Patna", 12.0, 12.0*0.62),
        Stop(14, "Mumbai", 13.0, 13.0*0.62),
        Stop(15, "Delhi", 14.0, 14.0*0.62),
        Stop(16, "Chennai", 15.0, 15.0*0.62),
    )
}

data class Progress(var currentStop: Stop, val totalDistanceInKm: Double, val totalDistanceInMiles: Double, var distanceLeftInKm: Double, var distanceLeftInMiles: Double, var flag1: Int)

fun moveToNextStop(route: Route, progress: Progress, flag: Int): Progress {
    val currentStopIndex = route.stops.indexOf(progress.currentStop)
    var f = flag
    if (currentStopIndex >= route.stops.size - 1) {
        return progress
    }
    f++
    val nextStop = route.stops[currentStopIndex + 1]

    val newDistanceLeftInKm = progress.totalDistanceInKm - progress.currentStop.distanceInKm
    f--
    val newDistanceLeftInMiles = progress.totalDistanceInMiles - progress.currentStop.distanceInMiles
    return Progress(
        distanceLeftInKm = newDistanceLeftInKm,
        distanceLeftInMiles = newDistanceLeftInMiles,
        flag1 = f,
        totalDistanceInKm = progress.totalDistanceInKm,
        totalDistanceInMiles = progress.totalDistanceInMiles,
        currentStop = nextStop
    )
}

@Composable
fun main(flag: Int): Int {
    val route = Route(getStops(), getStops().first(), getStops().last())
    var f = flag
    var progress by remember { mutableStateOf(Progress(route.stops.first(), route.stops.last().distanceInKm, route.stops.last().distanceInMiles, route.stops.last().distanceInKm, route.stops.last().distanceInMiles, 0)) }
    var isKm by remember { mutableStateOf(true) }
    f++
    var currentStop by remember { mutableStateOf(route.stops.first()) }
    val check: Boolean = route.stops.size <= 10
    Column {
        f++
        Row( horizontalArrangement = Arrangement.SpaceBetween ) {
            if(!check) {
                f--
                Column(modifier = Modifier
                    .fillMaxHeight(.85f)
                    .fillMaxWidth(0.5f)
                    .padding(
                        start = 16.dp,
                        top = 20.dp,
                        bottom = 20.dp
                    )){
                    f++
                    LazyColumn(content = {
                        items(items = route.stops, key = {stop -> stop.id}) { stop ->
                            Row {
                                f--
                                if (stop == currentStop) {
                                    f++
                                    Image(
                                        painterResource(id = R.drawable.green_circle),
                                        modifier = Modifier.size(40.dp),
                                        contentDescription = "Circle Indicator"
                                    )
                                    f++
                                } else {
                                    f--
                                    Image(
                                        painterResource(id = R.drawable.red_triangle),
                                        modifier = Modifier.size(40.dp),
                                        contentDescription = "Circle Indicator"
                                    )
                                    f--
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                f++
                                Column {
                                    if (stop == currentStop) {
                                        f++
                                        Text(
                                            text = "  ${stop.name}  ",
                                            modifier = Modifier.background(
                                                shape = RoundedCornerShape(40.dp),
                                                color = Color.Cyan
                                            ),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.SansSerif
                                            )
                                        )
                                        f--
                                    }
                                    else {
                                        f++
                                        Text(
                                            text = "  ${stop.name}  ",
                                            modifier = Modifier.background(
                                                shape = RoundedCornerShape(40.dp),
                                                color = Color.Yellow
                                            ),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.SansSerif
                                            )
                                        )
                                        f--
                                    }
                                    f--
                                    if (stop != route.endPoint) {
                                        f++
                                        if (!isKm) {
                                            Text(
                                                text = "${
                                                    String.format(
                                                        "%.3f",
                                                        route.stops[route.stops.indexOf(stop) + 1].distanceInMiles - stop.distanceInMiles
                                                    )
                                                } miles",
                                                modifier = Modifier.padding(
                                                    top = 10.dp,
                                                    bottom = 5.dp
                                                ),
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 18.sp,
                                                    fontFamily = FontFamily.SansSerif
                                                )
                                            )
                                            f--
                                        } else {
                                            f++
                                            Text(
                                                text = "${route.stops[route.stops.indexOf(stop) + 1].distanceInKm - stop.distanceInKm} km",
                                                modifier = Modifier.padding(
                                                    top = 10.dp,
                                                    bottom = 5.dp
                                                ),
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 18.sp,
                                                    fontFamily = FontFamily.SansSerif
                                                )
                                            )
                                            f--
                                        }
                                    }
                                }
                            }
                        }
                    }, modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .fillMaxWidth(1f))
                }
            }else {
                f++
                Column(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 20.dp,
                            bottom = 20.dp
                        )
                        .fillMaxWidth(0.5f)
                ) {
                    route.stops.forEach { stop ->
                        Row {
                            f--
                            if (stop == currentStop) {
                                f++
                                Image(
                                    painterResource(id = R.drawable.green_circle),
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "Circle Indicator"
                                )
                                f++
                            } else {
                                f--
                                Image(
                                    painterResource(id = R.drawable.red_triangle),
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "Circle Indicator"
                                )
                                f--
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                if (stop == currentStop) {
                                    f++
                                    Text(
                                        text = "  ${stop.name}  ",
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(40.dp),
                                            color = Color.Cyan
                                        ),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    )
                                    f--
                                }
                                else {
                                    f++
                                    Text(
                                        text = "  ${stop.name}  ",
                                        modifier = Modifier.background(
                                            shape = RoundedCornerShape(40.dp),
                                            color = Color.Yellow
                                        ),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 18.sp,
                                            fontFamily = FontFamily.SansSerif
                                        )
                                    )
                                    f--
                                }
                                f--
                                if (stop != route.endPoint) {
                                    f++
                                    if (!isKm) {
                                        Text(
                                            text = "${
                                                String.format(
                                                    "%.3f",
                                                    route.stops[route.stops.indexOf(stop) + 1].distanceInMiles - stop.distanceInMiles
                                                )
                                            } miles",
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                bottom = 5.dp
                                            ),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.SansSerif
                                            )
                                        )
                                        f--
                                    } else {
                                        f++
                                        Text(
                                            text = "${route.stops[route.stops.indexOf(stop) + 1].distanceInKm - stop.distanceInKm} km",
                                            modifier = Modifier.padding(
                                                top = 10.dp,
                                                bottom = 5.dp
                                            ),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 18.sp,
                                                fontFamily = FontFamily.SansSerif
                                            )
                                        )
                                        f--
                                    }
                                }
                            }
                        }
                    }
                }
            }
            f++
            println("distance left: ${progress.totalDistanceInKm - progress.currentStop.distanceInKm}")
            f--
            distanceProgressBar(distanceLeft = if(isKm) progress.totalDistanceInKm - progress.currentStop.distanceInKm else progress.totalDistanceInMiles - progress.currentStop.distanceInMiles, totalDistance = if(isKm) progress.totalDistanceInKm else progress.totalDistanceInMiles, units = if(isKm) "km" else "miles", 1)
        }
        Row(modifier = Modifier.padding(top = 40.dp)) {
            Button(
                onClick = { isKm = !isKm },
                modifier = Modifier.padding(start = 20.dp)
            ) {
                f++
                f++
                Text(
                    text = if (isKm) "Change to Miles" else "Change to KM",
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                )
                f--
                f--
            }
            f++
            Button(
                onClick = {
                    progress = moveToNextStop(route, progress, 0)
                    currentStop = progress.currentStop
                },
                modifier = Modifier.padding(start = 60.dp)
            ) {
                f--
                f++
                Text(
                    text = "Next Stop",
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                )
                f++
                f--
            }
        }
    }
    return f
}

//@Composable
//fun DisplayStopWithoutLazyList()


@Composable
fun distanceProgressBar(distanceLeft: Double, totalDistance: Double, units: String, flag: Int): Int{
    val distanceInFloat = distanceLeft.toFloat()
    var f = flag
    val totalDistanceInFloat = totalDistance.toFloat()
    val progress = (1 - (distanceInFloat / totalDistanceInFloat)) * 100
    f++
    println("Progress: $progress")
    f++
    Column(modifier = Modifier.fillMaxWidth()) {
        f--
        LinearProgressIndicator(
            progress = progress / 100,
            color = Color.Blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(top = 330.dp, end = 20.dp, bottom = 10.dp)

        )
        f++
        Text(
            text = if(units == "km") "Dist. Left: ${String.format("%.2f", distanceLeft)} km" else if(units == "miles") "Distance Left: ${String.format("%.2f", distanceLeft)} miles" else "",
            modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
        )
        f--
        Text(
            text = if(units == "km") "Dist. Covered: ${String.format("%.2f", totalDistance-distanceLeft)} km" else if(units == "miles") "Total Distance Covered: ${String.format("%.2f", totalDistance-distanceLeft)} miles" else "",
            modifier = Modifier.padding(start = 0.dp, bottom = 10.dp)
        )
        f++
        Text(
            text = "Progress: ${String.format("%.2f", progress)}%",
            modifier = Modifier.padding(start = 25.dp, bottom = 10.dp)
        )
    }
    return f
}
