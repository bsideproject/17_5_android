package com.carpick.carpickapp.screen.TestResult

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ResultDetailOption(
    context: Context,
    selectedCar: RecommendedCar
) {
    var rowTotalDatas = listOf<ResultDetailOptionRowData>(
        ResultDetailOptionRowData(
            "안전",
            selectedCar.securityOptionDescription,
            "차량의 기본 안전 옵션"
        ),
        ResultDetailOptionRowData(
            "외장",
            selectedCar.externalOptionDescription,
            "차량의 기본 외장 옵션"
        ),
        ResultDetailOptionRowData(
            "내장",
            selectedCar.internalOptionDescription,
            "차량의 기본 내장 옵션"
        ),
        ResultDetailOptionRowData(
            "편의",
            selectedCar.convenienceOptionDescription,
            "차량의 기본 편의 옵션"
        ),
        ResultDetailOptionRowData(
            "공조",
            selectedCar.airConditioningOptionDescription,
            "차량의 기본 공조 옵션"
        ),
        ResultDetailOptionRowData(
            "A/V",
            selectedCar.audioAndVisualOptionDescription,
            "차량의 기본 A/V 옵션"
        ),
        ResultDetailOptionRowData(
            "시트",
            selectedCar.sheetOptionDescription,
            "차량의 기본 시트 옵션"
        )
    )
    Column(
        modifier = Modifier
            .padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        ResultDetailOptionTitle()
        ResultDetailOptionBody(context, rowTotalDatas)
    }
}

@Composable
fun ResultDetailOptionTitle() {
    Text(
        text = "옵션",
        fontSize = with(LocalDensity.current) { 18.dp.toSp() },
        color = Color.White,
        fontFamily = PRETENDARD_BOLD,
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@Composable
fun ResultDetailOptionBody(
    context: Context,
    rowTotalDatas: List<ResultDetailOptionRowData>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 16.dp, 24.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF3f3f4D), shape = RoundedCornerShape(10.dp))
        ) {
            for(i in 0 until rowTotalDatas.size) {
                ResultDetailRow(context, rowTotalDatas[i], i == 0, i == rowTotalDatas.size-1)
            }
        }
    }
}

@Composable
fun ResultDetailRow(
    context: Context,
    itemData: ResultDetailOptionRowData,
    isFirstIdx: Boolean,
    isLastIdx: Boolean
) {
    val topPadding = if(isFirstIdx) 24 else 16
    val bottomPadding = if(isLastIdx) 24 else 16

    val modifier = if (!isLastIdx) {
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
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
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        ResultDetailRowTitle(
            context,
            itemData,
            topPadding,
            itemData.tooltipContent
        )

        Text(
            text = itemData.content,
            fontSize = with(LocalDensity.current) { 14.dp.toSp() },
            color = Color.White,
            fontFamily = PRETENDARD_BOLD,
            modifier = Modifier
                .padding(0.dp, 4.dp, 0.dp, bottomPadding.dp),
            lineHeight = 19.6.sp
        )
    }
}

@Composable
fun ResultDetailRowTitle(
    context: Context,
    itemData: ResultDetailOptionRowData,
    topPadding: Int,
    tooltipContent: String
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
        setText(tooltipContent ?: "")
        setTextColor(popupBackground)
        setTextSize(12f)
        setTextGravity(Gravity.START)
        setTextTypeface(ResourcesCompat.getFont(context, R.font.pretendard_medium)!!)
    }

    Balloon(
        builder = builder
    ) {balloonWindow ->
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp, topPadding.dp, 0.dp, 0.dp)
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

data class ResultDetailOptionRowData(
    val title: String,
    val content: String,
    val tooltipContent: String
)