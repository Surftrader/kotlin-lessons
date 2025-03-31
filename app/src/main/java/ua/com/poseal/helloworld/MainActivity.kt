package ua.com.poseal.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.com.poseal.helloworld.base.UserCard
import ua.com.poseal.helloworld.base.createUserList

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
    var userList by remember {
        mutableStateOf(createUserList())
    }
    val context = LocalContext.current

    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        items(
            items = userList,
            key = { user -> user.id },
        ) { user ->
            UserCard(
                user = user,
                onUserClicked = {
                    Toast.makeText(
                        context,
                        context.getString(R.string.clicked_on) + ' ' + user.name,
                        Toast.LENGTH_SHORT
                    ).show()
                },
                onUserDeleted = {
                    userList -= user
                },
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.listHeader(text: String) {
    stickyHeader {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(all = 16.dp)
        )
    }
}

@Composable
fun ListItem(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = 16.dp)
    )
}
