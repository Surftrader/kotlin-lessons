package ua.com.poseal.helloworld.base

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ua.com.poseal.helloworld.R

@Composable
fun UserImage(url: String) {
    val placeholder = rememberVectorPainter(
        image = Icons.Rounded.AccountCircle
    )
    AsyncImage(
        model = url,
        placeholder = placeholder,
        contentDescription = stringResource(R.string.user_photo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(64.dp)
            .aspectRatio(1f / 1f)
            .clip(CircleShape),
    )
}
