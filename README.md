# ChatBubble module


![](img.png)

## Installation
in your app.gradle

```groovy  

  

```  

## Basic Usage

```kotlin
@Preview
@Composable
fun ChatBubblePreview() {
    ChatBubble(
        modifier = Modifier.padding(vertical = 4.dp),
        beakPosition = BeakPosition.TOP_RIGHT,
        borderColor = Color.Gray,
        backGroundColor = Color.LightGray,
        roundCornerSize = 8.dp,
        beakHeight = 8.dp,
        beakWidth = 8.dp
    ) {
        Text(
            text = "my name is avida.\nand this is sample bubble?",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(4.dp)
        )
    }

}
```