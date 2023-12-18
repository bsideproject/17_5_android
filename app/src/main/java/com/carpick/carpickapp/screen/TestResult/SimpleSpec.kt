package com.carpick.carpickapp.screen.TestResult

import android.util.Log
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.carpick.carpickapp.model.CarDetailSpecTest

@Composable
fun SimpleSpec(
    onPressMoreAtSimpleSpec: () -> Unit,
    specs: List<CarDetailSpecTest>
) {

    var chunkedSpecs = specs.chunked(2)

    Column(
        modifier = Modifier.padding(0.dp, 28.dp, 0.dp, 0.dp)
    ) {

        SimpleSpecTitle()
        SimpleSpecBody(chunkedSpecs)
        ShowMoreButton(onPressMoreAtSimpleSpec)

    }
}

@Composable
fun SimpleSpecTitle() {
    Text(
        text = "간단스펙",
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@Composable
fun SimpleSpecBody(
    chunkedSpecs: List<List<CarDetailSpecTest>>,
    paddingTop: Int = 16
) {
    val dataRowSize = chunkedSpecs.size
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
            for (i in 0 until dataRowSize) {
                Log.d("testData $i", (i == dataRowSize-1).toString())
                SimpleSpecRow(chunkedSpecs[i], i == 0, i == dataRowSize-1)
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
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight(700)
            )
        }
    }
}

@Composable
fun SimpleSpecRow(
    rowData: List<CarDetailSpecTest>,
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        for(i in 0 until rowData.size) {
            SimpleSpecRowItem(
                rowData[i],
                i%2 == 1
            )
        }
    }
}

@Composable
fun SimpleSpecRowItem(
    itemData: CarDetailSpecTest,
    isLastIdx: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(if(isLastIdx) 1f else .5f)
            .padding(0.dp, 16.dp, 0.dp, 16.dp)
    ) {
        SimpleSpecRowItemTitle(itemData)

        Text(
            text = itemData.value,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun SimpleSpecRowItemTitle(
    itemData: CarDetailSpecTest
) {

    TestResultCommonTooltip(
        arrowPosition = 0.8f,
        toolTipContent = itemData.tooltipContent
    ) {balloonWindow ->
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
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