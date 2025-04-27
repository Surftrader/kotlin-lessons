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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.CancellationException
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

        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            println("AAAA - start composition")
            val (
                counterTextRef,
                incrementButtonRef
            ) = createRefs()

            Text(
//                text = counter.toString(),
                text = "QWERTY",
                fontSize = 32.sp,
                modifier = Modifier.constrainAs(counterTextRef) {
                    centerHorizontallyTo(parent)
                },
            )

            Button(
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

            if (shouldShowSquare) {

                LaunchedEffect(Unit) {
                    println("AAAA effect started")
                    try {
                        snapshotFlow { counter }
                            .collect {
                                println("AAAA collected - $it")
                            }
                    } catch (e: CancellationException) {
                        println("AAAA effect cancelled")
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
            println("AAAA - stop composition")
        }
    }
}
