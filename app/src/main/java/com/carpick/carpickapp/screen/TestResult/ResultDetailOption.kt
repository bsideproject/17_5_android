package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultDetailOption() {

    val testData = mutableListOf<DetailOptionData>(
        DetailOptionData("안전", "에어백(운전석, 동승석, 사이드(앞), 커튼), 주행 안전(ABS, 전방 추돌 경우)", "test tooltip"),
        DetailOptionData("외장", "헤드램프(LED), 주간 주행등", "test tooltip"),
        DetailOptionData("내장", "스티어링 휠(가죽), 기어 노브(전자식 노브), 계기판(디지털)", "test tooltip"),
        DetailOptionData("편의", "정속주행(cc(차간조절)), 주차 브레이크(전자식, 오토홀드), 엔진시동(버튼시동)", "test tooltip"),
    )
    Column(
        modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        ResultDetailOptionTitle()
        ResultDetailOptionBody(testData)
    }
}

@Composable
fun ResultDetailOptionTitle() {
    Text(
        text = "옵션",
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@Composable
fun ResultDetailOptionBody(
    testData: MutableList<DetailOptionData>
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
            for(i in 0 until testData.size) {
                ResultDetailRow( testData[i], i == 0, i == testData.size-1)
            }
        }
    }
}

@Composable
fun ResultDetailRow(
    itemData: DetailOptionData,
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
            itemData,
            topPadding
        )

        Text(
            text = itemData.content,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .padding(0.dp, 4.dp, 0.dp, bottomPadding.dp)
        )
    }
}

@Composable
fun ResultDetailRowTitle(
    itemData: DetailOptionData,
    topPadding: Int
) {
    TestResultCommonTooltip(
        arrowPosition = 0.8f,
        toolTipContent = itemData.tooltipContent
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
                fontSize = 14.sp,
                color = Color(0xFFD4D4E1),
                fontWeight = FontWeight(500),
                modifier = Modifier.padding(0.dp, 0.dp, 2.dp, 0.dp)
            )

            Box(
                modifier = Modifier
                    .width(15.dp)
                    .height(15.dp)
                    .background(Color(0xFF7A7AA2), shape = RoundedCornerShape(99.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "?",
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }

}

data class DetailOptionData(
    val title: String,
    val content: String,
    val tooltipContent: String
)