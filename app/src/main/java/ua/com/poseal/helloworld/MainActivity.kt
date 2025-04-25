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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import ua.com.poseal.helloworld.util.PreviewWithInsets
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.Random

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

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (
                counterTextRef,
                incrementButtonRef
            ) = createRefs()

            // SideEffect executes after each composition or recomposition
            // LaunchedEffect executes one time after create composition
//            SideEffect {
//                println("AAAA Hello - $counter")
//            }


            Text(
                text = counter.toString(),
                fontSize = 32.sp,
                modifier = Modifier.constrainAs(counterTextRef) {
                    centerHorizontallyTo(parent)
                },
            )

            Button(
                // Press to button is Side-effect also
                onClick = {
                    counter++
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

            if (counter % 4 < 2) {
                // Here LaunchedEffect executes each time when counter % 4 < 2
                LaunchedEffect(counter) {
                    println("AAAA launched")
                    try {
                        while (true) {
                            delay(1000)
                            println("AAAA Hello - ${Random.nextInt(1000)}")
                        }
                    } catch (e: CancellationException) {
                        println("AAAA canceled")
                        throw e
                    }
                }

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
