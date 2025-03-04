package ua.com.poseal.helloworld.simple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class SimpleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SimpleAppScreen() {
    var textValue: String by remember {
        mutableStateOf("")
    }
    var outLinedTextValue: String by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            TextField(
                value = textValue,
                onValueChange = { newTextValue ->
                    textValue = newTextValue
                }
            )
            OutlinedTextField(
                value = outLinedTextValue,
                onValueChange = { newTextValue ->
                    outLinedTextValue = newTextValue
                }
            )
        }
    }
}
