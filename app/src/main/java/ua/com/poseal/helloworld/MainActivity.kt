package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
    PreviewWithInsets() {
        Scaffold(
            topBar = { Text(text = "Top bar", fontSize = 30.sp) },
            bottomBar = { Text(text = "Bottom bar", fontSize = 30.sp) },
            floatingActionButton = { Text(text = "Floating button", fontSize = 30.sp) },
            floatingActionButtonPosition = FabPosition.Center,
            snackbarHost = { }
        ) { paddingValues ->
            // main content:
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                // play here
            }
        }
    }
}
