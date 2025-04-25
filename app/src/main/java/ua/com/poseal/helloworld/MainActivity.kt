package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.com.poseal.helloworld.ui.theme.AppTheme
import ua.com.poseal.helloworld.util.PreviewWithInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppScreen() {
    AppTheme {
        PreviewWithInsets {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Button(
                    onClick = { }
                ) {
                    Text(text = "Hello World!")
                }
            }
        }
    }
}
