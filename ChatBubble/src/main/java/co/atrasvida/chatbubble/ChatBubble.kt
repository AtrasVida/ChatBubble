package co.atrasvida.chatbubble

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class BeakPosition {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
}

/**
 * ChatBubble
 *
 * @param modifier
 * @param backGroundColor Background color of chat bubble
 * @param beakPosition [BeakPosition]
 * @param borderColor borderColor color of chat bubble
 * @param content content of chat bubble
 */


@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    beakPosition: BeakPosition = BeakPosition.BOTTOM_LEFT,
    beakWidth: Dp = 8.dp,
    beakHeight: Dp = 10.dp,
    roundCornerSize: Dp = 10.dp,
    backGroundColor: Color = MaterialTheme.colors.background,
    borderColor: Color = MaterialTheme.colors.primary,
    strokeWidth: Dp = 1.dp,
    content: @Composable BoxScope.() -> Unit
) {

    val isLeft by remember {
        mutableStateOf(beakPosition == BeakPosition.TOP_LEFT || beakPosition == BeakPosition.BOTTOM_LEFT)
    }
    val isTop by remember {
        mutableStateOf(beakPosition == BeakPosition.TOP_LEFT || beakPosition == BeakPosition.TOP_RIGHT)
    }
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .padding(
                start = if (!isLeft) beakWidth else 0.dp,
                end = if (isLeft) beakWidth else 0.dp
            )
    )
    {
        Box(
            modifier = Modifier.align(if (isLeft) Alignment.CenterStart else Alignment.CenterEnd)
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                this.drawTooltipCanvas(
                    roundCornerSize,
                    beakHeight.toPx(),
                    beakWidth.toPx(),
                    isLeft = isLeft,
                    isTop = isTop,
                    backGroundColor,
                    borderColor,
                    strokeWidth,
                )
            }
            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = beakHeight + roundCornerSize)
                    .padding(
                        //top = 10.dp, bottom = 10.dp,
                        start = if (isLeft) beakWidth else 0.dp,
                        end = if (!isLeft) beakWidth else 0.dp,
                    ),
            ) { this.content() }

        }
    }
}


private fun DrawScope.drawTooltipCanvas(
    curve: Dp,
    beakHeight: Float,
    beakWidth: Float,
    isLeft: Boolean,
    isTop: Boolean,
    backGroundColor: Color,
    borderColor: Color,
    strokeWidth: Dp
) {

    val mCurve = curve.toPx()
    val boxTop = 0f

    val width = size.width
    val height = size.height

    val path = Path().apply {

        val leftStart = if (isLeft) beakWidth else 0f
        val rightStart = if (isLeft) width else width - beakWidth



        if (!isLeft) {
            moveTo(leftStart, boxTop + mCurve)

            quadraticBezierTo(
                leftStart, boxTop,
                leftStart + mCurve, boxTop,
            )

            if (isTop) {
                lineTo(width, boxTop)
                quadraticBezierTo(
                    rightStart, boxTop,
                    rightStart, boxTop + beakHeight,
                )

                lineTo(rightStart, height - mCurve)

                quadraticBezierTo(
                    rightStart, height,
                    rightStart - mCurve, height,
                )

            } else {
                lineTo(rightStart - mCurve, boxTop)
                quadraticBezierTo(
                    rightStart, boxTop,
                    rightStart, boxTop + mCurve,
                )
                lineTo(rightStart, height - beakHeight)

                quadraticBezierTo(
                    rightStart, height,
                    width, height,
                )
            }

            lineTo(leftStart + mCurve, height)

            quadraticBezierTo(
                leftStart, height,
                leftStart, height - mCurve,
            )
        } else {

            if (isTop) {
                moveTo(leftStart, boxTop + beakHeight)

                quadraticBezierTo(
                    leftStart, boxTop,
                    0f, boxTop,
                )
            } else {
                moveTo(leftStart, boxTop + mCurve)

                quadraticBezierTo(
                    leftStart, boxTop,
                    leftStart + mCurve, boxTop,
                )
            }



            lineTo(rightStart - mCurve, boxTop)

            quadraticBezierTo(
                rightStart, boxTop,
                rightStart, boxTop + mCurve,
            )

            lineTo(rightStart, height - mCurve)/////


            quadraticBezierTo(
                rightStart, height,
                rightStart - mCurve, height,
            )

            if (isTop) {
                lineTo(leftStart + mCurve, height)
                quadraticBezierTo(
                    leftStart, height,
                    leftStart, height - mCurve,
                )
            } else {
                lineTo(0f, height)
                quadraticBezierTo(
                    leftStart, height,
                    leftStart, height - beakHeight,
                )
            }

        }


        close()


    }
    clipPath(path) {
        drawRect(
            color = backGroundColor,
            size = size,
        )
    }
    drawPath(
        path = path,
        color = borderColor,
        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    )

}


