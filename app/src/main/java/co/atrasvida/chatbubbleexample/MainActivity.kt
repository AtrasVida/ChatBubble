package co.atrasvida.chatbubbleexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.atrasvida.chatbubble.BeakPosition
import co.atrasvida.chatbubble.ChatBubble
import co.atrasvida.chatbubbleexample.ui.theme.ChatBubbleExampleTheme

var chatList = listOf(
    ChatModel(0, "hi", true),
    ChatModel(1, "hi", false),
    ChatModel(2, "are you ok?", true),
    ChatModel(3, "yes. i'm working on my new project", false),
    ChatModel(4, "you?", false),
)

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatBubbleExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ChatBubbleList(chatList)
                }
            }
        }
    }
}


@Composable
fun ChatBubbleList(list: List<ChatModel>) {
    LazyColumn(
        Modifier
            .padding(vertical = 16.dp),
    ) {
        items(list.size) { index ->
            ChatBubble(
                modifier = Modifier.padding(vertical = 8.dp),
                beakPosition =
                if (list[index].isFromUser) BeakPosition.BOTTOM_LEFT
                else BeakPosition.BOTTOM_RIGHT,
            ) {
                Text(
                    text = list[index].text,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(4.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatBubbleExampleTheme {
        ChatBubbleList(chatList)
    }
}

@Preview
@Composable
fun ChatBubblePreview() {
    Column(
        Modifier
            .background(Color.White)
            .width(250.dp)
            .padding(16.dp)
    ) {
        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.BOTTOM_LEFT,
        ) {
            Text(
                text = "hi",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(4.dp)
            )
        }

        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.BOTTOM_RIGHT,
            borderColor = Color.Gray,
            backGroundColor = Color.LightGray

        ) {
            Text(
                text = "hi",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(4.dp)
            )
        }

        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.TOP_LEFT,

            ) {
            Text(
                text = "what's your name?",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(4.dp)
            )
        }

        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.TOP_RIGHT,
            borderColor = Color.Gray,
            backGroundColor = Color.LightGray
        ) {
            Text(
                text = "my name is avida.\nand you?",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(4.dp)
            )
        }
        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.TOP_LEFT,
            borderColor = Color.Red,
            backGroundColor = Color(0x4FFF1F32),
            roundCornerSize = 4.dp,
            beakHeight = 24.dp,
            beakWidth = 18.dp
        ) {
            Text(
                text = "this is different face",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(4.dp)
            )
        }

        ChatBubble(
            modifier = Modifier.padding(vertical = 4.dp),
            beakPosition = BeakPosition.TOP_RIGHT,
            borderColor = Color.Gray,
            backGroundColor = Color.LightGray,
            roundCornerSize = 28.dp,
            beakHeight = 24.dp,
            beakWidth = 24.dp
        ) {
            Text(
                text = "and this is big rounded",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
            )
        }

    }

}