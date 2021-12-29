package com.example.planty

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planty.model.Stats
import com.example.planty.ui.theme.*
import com.example.planty.utils.standardQuadFromTo

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {

    Box(modifier= Modifier
        .background(BackgroundGrey)
        .fillMaxSize()){
        Column{
            Greeting()
            MyPlantyBox()
            PlantStatsSection(myPlants = listOf(
                Stats(
                    title = "Hava Sıcaklığı: \n 29",
                    R.drawable.ic_temp,
                ),
                Stats(
                    title = "Toprak: \n 59 ",
                    R.drawable.ic_soil,
                ),
                Stats(
                    title = "Ciğ noktası: 20.19",
                    R.drawable.ic_cloudy,
                ),
                Stats(
                    title = "Nem: \n 452",
                    R.drawable.ic_wind,
                )
            ))
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Greeting(){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier= Modifier
            .fillMaxWidth()
            .padding(20.dp)){

        Column(
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Merhaba",
                style = MaterialTheme.typography.h5,
                color = Color.DarkGray,

                )
            Text(
                text = "Aysima",
                style = MaterialTheme.typography.h5,
                color = Color.DarkGray,
            )
        }

        ExtendedFloatingActionButton(
            onClick = { },
            icon = {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Favorite",
                    tint= Color.White,
                    modifier = Modifier.size(32.dp)

                )
            },
            text = { Text("CHZ-8266",color= Color.White,fontSize = 16.sp) },
            backgroundColor = MyGreen
        )
    }

}
@Composable
fun MyPlantyBox(){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()){
        Column{
            Text(
                text = "Begonyam",
                style = MaterialTheme.typography.h4,
                color= Color.DarkGray,
            )
            Text(
                text = "Süs bitkisi, ideal ortam sıcaklığı 16-18C",
                style = MaterialTheme.typography.body2,
                color= Color.DarkGray,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Image(painterResource(R.drawable.ic_planty)
            ,"content description"
            ,modifier = Modifier.size(64.dp))
    }
}

@ExperimentalFoundationApi
@Composable
fun PlantStatsSection(myPlants: List<Stats>){
    Column(modifier = Modifier.fillMaxWidth()){
        Text(
            text = "Bitki Durumu",
            style = MaterialTheme.typography.h5,
            modifier=Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(myPlants.size){
               plantItem(plant = myPlants[it])
            }
        }
    }

}
@Composable
fun plantItem(
    plant: Stats
){
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(LightGreen3)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = LightGreen2
            )
            drawPath(
                path = lightColoredPath,
                color = LightGreen1
            )
        }
        Box(modifier= Modifier
            .fillMaxSize()
            .padding(15.dp)){
            Column() {
                Text(
                    text = plant.title,
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )

            }
            Image(painterResource(id = plant.iconId)
                ,"content description"
                ,modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.BottomStart))
        }
    }

}


@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlantyTheme {
    HomeScreen()
    }
}