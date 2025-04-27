package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    PreviewWithInsets {
        var counter by rememberSaveable {
            mutableStateOf(0)
        }

        val shouldShowSquare by remember {
            derivedStateOf {
                counter % 6 > 2
            }
        }

        MyFun(value = counter)

        val scope = rememberCoroutineScope()

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (
                counterTextRef,
                incrementButtonRef
            ) = createRefs()

            Text(
                text = counter.toString(),
//                text = "QWERTY",
                fontSize = 32.sp,
                modifier = Modifier.constrainAs(counterTextRef) {
                    centerHorizontallyTo(parent)
                },
            )

            Button(
                onClick = {
                    counter++
                    scope.launch {
                        delay(10000)
                    }
                },
                modifier = Modifier.constrainAs(incrementButtonRef) {
                    centerHorizontallyTo(parent)
                }
            ) {
                Text(text = stringResource(R.string.increment))
            }

            createVerticalChain(
                counterTextRef, incrementButtonRef,
                chainStyle = ChainStyle.Packed,
            )

            if (shouldShowSquare) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Red)
                        .constrainAs(createRef()) {
                            top.linkTo(incrementButtonRef.bottom, margin = 10.dp)
                            centerHorizontallyTo(parent)
                        }
                )
            }
        }
    }
}

@Composable
fun MyFun(value: Int) {
    val valueState by rememberUpdatedState(value)
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            println("AAAA counter = $valueState")
        }
    }
}
