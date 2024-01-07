package com.carpick.carpickapp.screen.TestResult

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_MEDIUM
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.BalloonWindow

@Composable
fun TestResultCommonTooltip(
    toolTipContent: String,
    builder: Balloon.Builder,
    modifier: Modifier = Modifier,
    content: @Composable (balloonWindow: BalloonWindow) -> Unit,
) {

    Balloon(
        builder = builder,
        modifier = modifier,
        balloonContent = {
            Text(
                text = toolTipContent,
                fontSize = 14.sp,
                color = popupBackground,
                fontFamily = PRETENDARD_MEDIUM,
            )
        },
    ) {balloonWindow ->
        content(balloonWindow)
    }
}