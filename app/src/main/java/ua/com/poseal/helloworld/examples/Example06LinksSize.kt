package ua.com.poseal.helloworld.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ua.com.poseal.helloworld.Hint
import ua.com.poseal.helloworld.Rectangle

@Composable
@Preview(showSystemUi = true)
fun Example06LinksSize() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (
            startBound,
            endBound,
            rectFillToConstraints,
            rectMatchParent,
            rectPercentage,
            rectWarpContent,
            rectPreferredWarpContent,
            rectPreferredValue,
            rectAspectRatio,
        ) = createRefs()

        // --- bounds

        // start (left) bound
        Rectangle(
            width = 10.dp,
            height = 300.dp,
            color = Color.Black,
            modifier = Modifier.constrainAs(startBound) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start, margin = 40.dp)
            }
        )

        // end (right) bound
        Rectangle(
            width = 10.dp,
            height = 300.dp,
            color = Color.Black,
            modifier = Modifier.constrainAs(endBound) {
                centerVerticallyTo(parent)
                end.linkTo(parent.end, margin = 40.dp)
            }
        )

        // --- squares

        // fillToConstraints
        Hint(rectFillToConstraints, "fill to constraints")
        Rectangle(
            modifier = Modifier
                .constrainAs(rectFillToConstraints) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(startBound.top, margin = 10.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(20.dp)
                }
        )

        // matchParent
        Hint(rectMatchParent, "match parent")
        Rectangle(
            modifier = Modifier
                .constrainAs(rectMatchParent) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectFillToConstraints.bottom, margin = 30.dp)
                    width = Dimension.matchParent
                    height = Dimension.value(20.dp)
                }
        )

        // percentage
        Hint(rectPercentage, "percentage")
        Rectangle(
            modifier = Modifier
                .constrainAs(rectPercentage) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectMatchParent.bottom, margin = 30.dp)
                    width = Dimension.percent(0.7f)
                    height = Dimension.value(20.dp)
                }
        )

        // wrap content
        Hint(rectWarpContent, "wrap content")
        Text(
            text = "123",
            color = Color.White,
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 2.dp)
                .constrainAs(rectWarpContent) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectPercentage.bottom, margin = 30.dp)
                    width = Dimension.wrapContent
                }
        )

        // preferred wrap content
        Hint(rectPreferredWarpContent, "preferred wrap content")
        Text(
            text = "Adokf adocdoa idsj DMS mckdfdj finv fiahvb alhda alfbfdla afljn anv fadafnads sljnv;jdanvda;v ;afnvads;",
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 2.dp)
                .constrainAs(rectPreferredWarpContent) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectWarpContent.bottom, margin = 30.dp)
                    width = Dimension.preferredWrapContent
                }
        )

        // preferred value
        Hint(rectPreferredValue, "preferred value")
        Rectangle(
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 2.dp)
                .constrainAs(rectPreferredValue) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectPreferredWarpContent.bottom, margin = 30.dp)
                    width = Dimension.preferredValue(200.dp)
                    height = Dimension.value(20.dp)
                }
        )

        // aspect ratio
        Hint(rectAspectRatio, "aspect ratio")
        Rectangle(
            modifier = Modifier
                .background(Color.Red)
                .padding(horizontal = 6.dp, vertical = 2.dp)
                .constrainAs(rectAspectRatio) {
                    start.linkTo(startBound.end)
                    end.linkTo(endBound.start)
                    top.linkTo(rectPreferredValue.bottom, margin = 30.dp)
                    width = Dimension.percent(0.25f)
                    height = Dimension.ratio("2:3")
                }
        )
    }
}
