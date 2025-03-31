package ua.com.poseal.helloworld.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ua.com.poseal.helloworld.model.User

@Preview
@Composable
fun UserCard(
    @PreviewParameter(UserPreviewProvider::class) user: User,
    modifier: Modifier = Modifier,
    onUserClicked: (() -> Unit)? = null,
    onUserDeleted: (() -> Unit)? = null,
) {
    Card(
        shape = RoundedCornerShape(size = 6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
            ) {
                onUserClicked?.invoke()
            }
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            UserImage(url= user.photoUrl)
            Spacer(modifier = Modifier.width(16.dp))
            UserInfo(user = user)
            DeleteUserButton(onClick = { onUserDeleted?.invoke() })
        }
    }
}

private class UserPreviewProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User> = sequenceOf(
        User(
            id = 1,
            photoUrl = "",
            name = "Gandalf",
            status = "Lorem ipsum"
        ),
        User(
            id = 2,
            photoUrl = "",
            name = "Gandalf the White, the leader of the Fellowship of the Ring",
            status = "Gandalf Gray is my old name. I am Gandalf White. I returned to you at a crucial hour."
        )
    )
}
