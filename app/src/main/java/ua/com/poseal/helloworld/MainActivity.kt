package ua.com.poseal.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import ua.com.poseal.helloworld.base.Container

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        VectorIcon()
        MaterialIcon()
        TintedVectorIcon()
        IconWithModifier()
        SimpleImage()
        ContentScaleCropImage()
        SquareImage()
        ClippedImage()
        ClippedWithCustomShapeImage()
        SimpleAsyncLoadedImage()
        AsyncLoadedImage()

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun VectorIcon() {
    Container(name = "Simple vector icon") {
        Icon(
            painter = painterResource(R.drawable.health_and_safety_24),
            contentDescription = "Safety icon"
        )
    }
}

@Composable
fun MaterialIcon() {
    Container(name = stringResource(R.string.material_icon)) {
        Icon(
            imageVector = Icons.Rounded.MailOutline,
            contentDescription = stringResource(R.string.material_mail_icon),
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun TintedVectorIcon() {
    Container(name = stringResource(R.string.tinted_vector_icon)) {
        Icon(
            painter = painterResource(R.drawable.health_and_safety_24),
            contentDescription = stringResource(R.string.safety_icon),
            tint = Color.Red
        )
    }
}

@Composable
fun IconWithModifier() {
    Container(name = stringResource(R.string.vector_icon_with_modifier)) {
        Icon(
            painter = painterResource(R.drawable.health_and_safety_24),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, CircleShape)
                .padding(8.dp)
        )
    }
}

@Composable
fun SimpleImage() {
    Container(name = stringResource(R.string.simple_image)) {
        Image(
            painter = painterResource(R.drawable.nature),
            contentDescription = stringResource(R.string.random_nature_showcase)
        )
    }
}

@Composable
fun ContentScaleCropImage() {
    Container(name = stringResource(R.string.image_with_content_scale)) {
        Image(
            painter = painterResource(R.drawable.nature),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.random_nature_showcase)
        )
    }
}

@Composable
fun SquareImage() {
    Container(name = stringResource(R.string.image_cropped_by_aspect_ratio)) {
        Image(
            painter = painterResource(R.drawable.nature),
            contentDescription = stringResource(R.string.random_nature_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f / 1f)
        )
    }
}

@Composable
fun ClippedImage() {
    Container(name = stringResource(R.string.clipped_image)) {
        Image(
            painter = painterResource(R.drawable.nature),
            contentDescription = stringResource(R.string.random_nature_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(CircleShape)
        )
    }
}

@Composable
fun ClippedWithCustomShapeImage() {
    Container(name = stringResource(R.string.clipped_image_with_custom_shape)) {
        Image(
            painter = painterResource(R.drawable.nature),
            contentDescription = stringResource(R.string.random_nature_showcase),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f / 1f)
                .clip(GenericShape { size, _ ->
                    moveTo(0f, size.height)
                    lineTo(size.width / 2, 0f)
                    lineTo(size.width, size.height)
                })
        )
    }
}

@Composable
fun SimpleAsyncLoadedImage() {
    Container(name = stringResource(R.string.sync_load_by_coil)) {
        AsyncImage(
            model = "https://poseal.com.ua/static/img/ava.jpg",
            contentDescription = null,
        )
    }
}

enum class RequestState {
    LOAD_NOT_REQUESTED,
    LOAD_REQUESTED,
}

@Composable
fun AsyncLoadedImage() {
    var state by remember {
        mutableStateOf(RequestState.LOAD_NOT_REQUESTED)
    }

    Container(name = stringResource(R.string.sync_load_by_coil)) {
        when (state) {
            RequestState.LOAD_NOT_REQUESTED -> LoadButton { state = RequestState.LOAD_REQUESTED }
            RequestState.LOAD_REQUESTED -> {
                SubcomposeAsyncImage(
                    model = "https://poseal.com.ua/static/img/ava.jpg",
                    contentDescription = null,
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = {
                        Text(stringResource(R.string.load_failed), color = Color.Red)
                    },
                )
            }
        }
    }
}

@Composable
fun LoadButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(stringResource(R.string.load))
    }
}
