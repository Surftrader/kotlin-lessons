package ua.com.poseal.helloworld.base

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
import androidx.compose.ui.unit.dp

@Composable
fun UserCard(
    user: User,
    onUserClicked: () -> Unit,
    onUserDeleted: () -> Unit,
    modifier: Modifier = Modifier,
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
                onUserClicked()
            }
    ) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            UserImage(url= user.photoUrl)
            Spacer(modifier = Modifier.width(16.dp))
            UserInfo(user = user)
            DeleteUserButton(onClick = onUserDeleted)
        }
    }
}