package com.carpick.carpickapp.screen.TestResult

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.carpick.carpickapp.R
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_MEDIUM
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.compose.Balloon
import com.skydoves.balloon.compose.rememberBalloonBuilder
import com.skydoves.balloon.compose.setTextColor

@Composable
fun SimpleSpec(
    context: Context,
    onPressMoreAtSimpleSpec: () -> Unit,
    selectedCar: RecommendedCar,
    specRowDatas: List<List<RowDataTypes>>
) {

    Column(
        modifier = Modifier.padding(0.dp, 28.dp, 0.dp, 0.dp)
    ) {

        SimpleSpecTitle()
        SimpleSpecBody(context, specRowDatas)
        ShowMoreButton(onPressMoreAtSimpleSpec)

    }
}

@Composable
fun SimpleSpecTitle() {
    Text(
        text = "간단스펙",
        fontSize = with(LocalDensity.current) { 18.dp.toSp() },
        color = Color.White,
        fontFamily = PRETENDARD_BOLD,
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@Composable
fun SimpleSpecBody(
    context: Context,
    chunkedTotalDatas: List<List<RowDataTypes>>,
    paddingTop: Int = 16
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, paddingTop.dp, 24.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF3f3f4D), shape = RoundedCornerShape(10.dp))
        ) {
            for (i in 0 until chunkedTotalDatas.size) {
                SimpleSpecRow(context, chunkedTotalDatas[i], i == 0, i == chunkedTotalDatas.size-1)
            }
        }

    }
}

@Composable
fun ShowMoreButton(
    onPressMoreAtSimpleSpec: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 16.dp, 24.dp, 0.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF4B4B6B)
            ),
            shape = RoundedCornerShape(99.dp),
            onClick = onPressMoreAtSimpleSpec
        ) {
            Text(
                text = "더보기",
                fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                color = Color.White,
               fontFamily = PRETENDARD_BOLD
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimpleSpecRow(
    context: Context,
    rowData: List<RowDataTypes>,
    isFirstIdx: Boolean,
    isLastIdx: Boolean
) {
    val topPadding = if(isFirstIdx) 8 else 0
    val bottomPadding = if(isLastIdx) 8 else 0

    val modifier = if (!isLastIdx) {
        Modifier
            .fillMaxWidth()
            .padding(16.dp, topPadding.dp, 16.dp, bottomPadding.dp)
            .drawBehind {
                drawLine(
                    color = Color(0xFF4B4B6B),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = if (isLastIdx) 0.dp.toPx() else 1.dp.toPx()
                )
            }
    }
    else {
        Modifier
            .fillMaxWidth()
            .padding(16.dp, topPadding.dp, 16.dp, bottomPadding.dp)
    }

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
    ) {
        for(i in 0 until rowData.size) {
            SimpleSpecRowItem(
                context,
                rowData[i],
                i%2 == 1
            )
        }
    }
}

@Composable
fun SimpleSpecRowItem(
    context: Context,
    itemData: RowDataTypes,
    isLastIdx: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(0.dp, 16.dp, 0.dp, 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight()
        ) {
            SimpleSpecRowItemTitle(context, itemData)

            Text(
                text = itemData.value,
                fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                color = Color.White,
                fontFamily = PRETENDARD_BOLD,
                modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
            )

        }
    }
}

@Composable
fun SimpleSpecRowItemTitle(
    context: Context,
    itemData: RowDataTypes
) {
    val builder = rememberBalloonBuilder {
        setArrowSize(10)
        setArrowPosition(0.8f)
        setBackgroundColor(Color(0xFFF2F2F6).hashCode())
        setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        setWidth(BalloonSizeSpec.WRAP)
        setHeight(BalloonSizeSpec.WRAP)
        setPaddingHorizontal(17)
        setPaddingVertical(12)
        setMarginHorizontal(12)
        setCornerRadius(8f)
        setBalloonAnimation(BalloonAnimation.ELASTIC)
        setElevation(15)
        setText(itemData.tooltipContent ?: "")
        setTextColor(popupBackground)
        setTextSize(12f)
        setTextGravity(Gravity.START)
        setTextTypeface(Typeface.MONOSPACE)
        setTextTypeface(ResourcesCompat.getFont(context, R.font.pretendard_medium)!!)
    }

    if(itemData.tooltipContent == null) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = itemData.title,
                fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                color = Color(0xFFD4D4E1),
                fontFamily = PRETENDARD_MEDIUM,
                modifier = Modifier.padding(0.dp, 0.dp, 2.dp, 0.dp)
            )
        }
    }
    else {
        Balloon(
            builder = builder
        ) {balloonWindow ->
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        balloonWindow.showAlignBottom()
                    }
            ) {
                Text(
                    text = itemData.title,
                    fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                    color = Color(0xFFD4D4E1),
                    fontFamily = PRETENDARD_MEDIUM,
                    modifier = Modifier.padding(0.dp, 0.dp, 2.dp, 0.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_tooltip),
                    contentDescription = "툴팁",
                    modifier = Modifier
                        .size(15.dp)
                )
            }
        }
    }

}

data class RowDataTypes(
    val title: String,
    val value: String,
    val tooltipContent: String? = null
)