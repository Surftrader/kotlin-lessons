package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HelloWorld() {
    Row {
        Text(
            text = "Hello World!",
            fontSize = 25.sp,
            color = Color.Red,
            modifier = Modifier.background(Color.Yellow)
        )
        Text(
            text = "Gendalf",
            fontSize = 20.sp,
            color = Color.Blue
        )
    }
}
