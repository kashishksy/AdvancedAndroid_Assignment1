package com.kashish.advancedandroid_assignment1

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kashish.advancedandroid_assignment1.datastore.DataStoreManager
import com.kashish.advancedandroid_assignment1.ui.theme.AdvancedAndroid_assignment1Theme
import com.kashish.advancedandroid_assignment1.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    // Define gradient colors
    val gradientColors = listOf(
        Color(0xFF6A11CB), // Deep purple
        Color(0xFF2575FC)  // Bright blue
    )
    // Collect StateFlow values
    val id = viewModel.id.collectAsState()
    val username = viewModel.username.collectAsState()
    val courseName = viewModel.courseName.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradientColors,
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Input Fields
            OutlinedTextField(
                value = id.value,
                onValueChange = { viewModel.updateId(it) },
                label = { Text("ID", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White.copy(alpha = 0.8f),
                    unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.6f)
                )
            )

            OutlinedTextField(
                value = username.value,
                onValueChange = { viewModel.updateUsername(it) },
                label = { Text("Username", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White.copy(alpha = 0.8f),
                    unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.6f)
                )
            )

            OutlinedTextField(
                value = courseName.value,
                onValueChange = { viewModel.updateCourseName(it) },
                label = { Text("Course Name", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedLabelColor = Color.White.copy(alpha = 0.8f),
                    unfocusedLabelColor = Color.White.copy(alpha = 0.6f),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White.copy(alpha = 0.6f)
                )
            )

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { viewModel.loadData() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF6A11CB)
                    )
                ) {
                    Text("Load")
                }

                Button(
                    onClick = { viewModel.storeData() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF6A11CB)
                    )
                ) {
                    Text("Store")
                }

                Button(
                    onClick = { viewModel.resetData() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF6A11CB)
                    )
                ) {
                    Text("Reset")
                }
            }
        }

        // Student Info Box at bottom
        StudentInfoBox(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

//@Composable
//fun MyCard2() {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Column {
//            Text(
//                text = "Kashish Yadav",
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = "Student ID: 301485842",
//                modifier = Modifier.padding(top = 8.dp)
//            )
//        }
//    }
//}

@Composable
fun StudentInfoBox(modifier: Modifier = Modifier) {
    var colorState by remember { mutableStateOf(true) }
    val bgColor = if (colorState) Color.White else Color(0xFFF694D0)

    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { colorState = !colorState },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = bgColor.copy(alpha = 0.9f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Kashish Yadav",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Student ID: 301485842",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AdvancedAndroid_assignment1Theme {
        val context = LocalContext.current
        MainScreen(
            viewModel = MainViewModel(
                DataStoreManager(context.applicationContext)
            )
        )
    }
}