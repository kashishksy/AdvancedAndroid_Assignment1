package com.kashish.advancedandroid_assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kashish.advancedandroid_assignment1.ui.theme.AdvancedAndroid_assignment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdvancedAndroid_assignment1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    StudentInfoBox()
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AdvancedAndroid_assignment1Theme {
//        Greeting("Android")
//    }
//}

@Composable
fun MyCard2() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp) // Add margin around the entire Row
    ) {
        Column {
            Text(
                text = "Kashish Yadav",
                modifier = Modifier.padding(bottom = 8.dp) // Add bottom margin between the two Text components
            )
            Text(
                text = "Student ID: 301485842",
                modifier = Modifier.padding(top = 8.dp) // Add top margin for spacing consistency
            )
        }
    }
}

@Composable
fun StudentInfoBox(modifier: Modifier = Modifier) {
    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.White) }

    //val fuchsia = Color(0xFFFF00FF) // Vibrant fuchsia
    val lightFuchsia = Color(0xFFF694D0) // Muted fuchsia for softer contrast
    val clickHandler = {
        colorState = !colorState
        bgColor = if (colorState) Color.White else lightFuchsia
    }
    Box(
        modifier = Modifier
            .fillMaxSize() // Make the parent Box fill the screen
    ) {
        Box(
            Modifier
                .fillMaxWidth() // Make the Box take the full width
                .height(150.dp) // Adjust the height as needed
                .padding(16.dp) // Add margin around the Box (outer space)
                .align(Alignment.BottomCenter) // Align it at the bottom center of the screen
                .clickable { clickHandler() }
                .background(bgColor, shape = RoundedCornerShape(15.dp)) // Add rounded corners to the background
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Add a rounded border
            //.background(bgColor)
        ) {
            MyCard2()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentInfoBoxPreview() {
    AdvancedAndroid_assignment1Theme {
        StudentInfoBox()
    }
}