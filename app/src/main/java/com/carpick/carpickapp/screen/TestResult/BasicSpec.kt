package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.model.Tag
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_MEDIUM
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.rememberBalloonBuilder

@Composable
fun BasicSpec(
    tags: List<Tag>
) {
    Column(
        modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        BasicSpecTitle()
        BasicSpecTags(tags)
    }
}

@Composable
fun BasicSpecTitle() {
    Text(
        text = "주요특징",
        fontSize = 18.sp,
        color = Color.White,
        fontFamily = PRETENDARD_BOLD,
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicSpecTags(
    tags: List<Tag>
) {

    FlowRow(
        modifier = Modifier.padding(20.dp, 12.dp),
        horizontalArrangement = Arrangement.Start,
        maxItemsInEachRow = 4,
    ) {
        tags.forEach { hashTag ->
            run {
                HashTag(value = hashTag)
            }
        }
    }
}

@Composable
fun HashTag(
    value: Tag
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(0.7f)
        setBackgroundColor(Color(0xFFF2F2F6).hashCode())
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPaddingHorizontal(21)
        setPaddingVertical(12)
        setMarginHorizontal(12)
        setCornerRadius(8f)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setArrowOrientation(ArrowOrientation.TOP)
        setElevation(15)
        setArrowElevation(15)
    }

    TestResultCommonTooltip(
        toolTipContent = value.tagDescription,
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = Color(android.graphics.Color.parseColor(value.tagRgbColorCode)),
                shape = RoundedCornerShape(99.dp)
            ),
        builder = builder
    ) {balloonWindow ->
        Box(
            modifier = Modifier.clickable {
                balloonWindow.showAlignBottom()
            }
        ) {

            Row(
                modifier = Modifier
                    .background(Color(android.graphics.Color.parseColor(value.tagRgbColorCode)), shape = RoundedCornerShape(99.dp))
                    .padding(15.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = value.tagName,
                    fontSize = 14.sp,
                    fontFamily = PRETENDARD_MEDIUM,
                    color = Color(0xFF21212F),
                    modifier = Modifier.padding(0.dp, 0.dp, 2.dp, 0.dp)
                )

                Box(
                    modifier = Modifier
                        .width(15.dp)
                        .height(15.dp)
                        .background(popupBackground, shape = RoundedCornerShape(99.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "?",
                        fontSize = 10.sp,
                        color = Color.White,
                        fontFamily = PRETENDARD_MEDIUM
                    )
                }
            }
        }
    }


}
