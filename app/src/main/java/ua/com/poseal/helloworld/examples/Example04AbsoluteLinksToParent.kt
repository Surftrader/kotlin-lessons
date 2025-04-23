package ua.com.poseal.helloworld.examples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import ua.com.poseal.helloworld.Square

@Composable
@Preview(showSystemUi = true, locale = "ar")
fun Example04AbsoluteLinksToParent() {
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ){
        Square(
            modifier = Modifier.constrainAs(createRef()) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
        )
        
        Square(
            modifier = Modifier.constrainAs(createRef()) {
                absoluteLeft.linkTo(parent.absoluteLeft)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}
