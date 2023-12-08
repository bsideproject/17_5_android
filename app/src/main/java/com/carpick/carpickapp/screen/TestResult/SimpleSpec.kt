package com.carpick.carpickapp.screen.TestResult

import android.util.Log
import androidx.compose.foundation.background
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
fun SimpleSpec() {
    var testData = mutableListOf<SimpleSpecItemData>(
        SimpleSpecItemData("가격", "3,187만원"),
        SimpleSpecItemData("차종", "준중형"),
        SimpleSpecItemData("연료", "하이브리드"),
        SimpleSpecItemData("연비", "20.9km/l"),
        SimpleSpecItemData("배기량", "1,987cc"),
        SimpleSpecItemData("최고출력", "152/6000ps/rpm")
    ).chunked(2)
    val dataRowSize = testData.size

    Column(
        modifier = Modifier.padding(0.dp, 28.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = "간단스펙",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(24.dp, 0.dp)
        )

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
                for (i in 0 until dataRowSize) {
                    Log.d("testData $i", (i == dataRowSize-1).toString())
                    SimpleSpecRow(testData[i], i == dataRowSize-1)
                }
            }
        }
    }
}

@Composable
fun SimpleSpecRow(
    rowData: List<SimpleSpecItemData>,
    isLastIdx: Boolean
) {
    val modifier = if (!isLastIdx) {
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
            .drawBehind {
                drawLine(
                    color = Color(0xFF4B4B6B),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = if(isLastIdx) 0.dp.toPx() else  1.dp.toPx()
                )
            }
    }
    else {
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        rowData.forEach { it ->
            SimpleSpecRowItem(it)
        }
    }
}

@Composable
fun SimpleSpecRowItem(
    itemData: SimpleSpecItemData
) {
    Column(
        modifier = Modifier.width(132.dp).padding(0.dp, 16.dp, 0.dp, 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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

        Text(
            text = itemData.value,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
        )
    }
}

data class SimpleSpecItemData(
    val title: String,
    val value: String
)