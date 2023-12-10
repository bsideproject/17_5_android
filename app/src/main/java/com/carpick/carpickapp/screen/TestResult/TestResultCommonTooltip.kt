package com.carpick.carpickapp.screen.TestResult

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow
import com.skydoves.balloon.compose.rememberBalloonBuilder

@Composable
fun TestResultCommonTooltip(
    arrowPosition: Float,
    toolTipContent: String,
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    content: @Composable (balloonWindow: BalloonWindow) -> Unit,
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(arrowPosition)
        setBackgroundColor(backgroundColor.hashCode())
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPaddingHorizontal(21)
        setPaddingVertical(12)
        setMarginHorizontal(12)
        setCornerRadius(8f)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setArrowOrientation(ArrowOrientation.TOP)
    }

    Balloon(
        builder = builder,
        modifier = modifier,
        balloonContent = {
            Text(
                text = toolTipContent,
                fontSize = 14.sp,
                color = popupBackground,
                fontWeight = FontWeight(500)
            )
        },
    ) {balloonWindow ->
        content(balloonWindow)
    }
}