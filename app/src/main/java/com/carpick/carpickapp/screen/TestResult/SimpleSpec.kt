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
import com.carpick.carpickapp.model.RecommendedCar
import java.text.DecimalFormat

@Composable
fun SimpleSpec(
    onPressMoreAtSimpleSpec: () -> Unit,
    selectedCar: RecommendedCar
) {

    val priceDec = DecimalFormat("#,###만원")
    val displacementDec = DecimalFormat("#,###cc")

    var rowTotalDatas = listOf<RowDataTypes>(
        RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}"),
        RowDataTypes("차종", selectedCar.carBodyTypeName),
        RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: ""),
        RowDataTypes("연비", "${selectedCar.fuelEconomy}km/l"),
        RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}"),
        RowDataTypes("최대출력", "${selectedCar.maximumPowerDescription}ps/rpm"),
    )

    var chunkedTotalDatas = rowTotalDatas.chunked(2)

    Column(
        modifier = Modifier.padding(0.dp, 28.dp, 0.dp, 0.dp)
    ) {

        SimpleSpecTitle()
        SimpleSpecBody(chunkedTotalDatas)
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
                SimpleSpecRow(chunkedTotalDatas[i], i == 0, i == chunkedTotalDatas.size-1)
            }
//            for (i in 0 until dataRowSize) {
//                Log.d("testData $i", (i == dataRowSize-1).toString())
//                SimpleSpecRow(chunkedSpecs[i], i == 0, i == dataRowSize-1)
//            }
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
//        for(i in 0 until rowData.size) {
//            SimpleSpecRowItem(
//                rowData[i],
//                i%2 == 1
//            )
//        }
    }
}

@Composable
fun SimpleSpecRowItem(
    itemData: RowDataTypes,
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
    itemData: RowDataTypes
) {

    TestResultCommonTooltip(
        arrowPosition = 0.8f,
        toolTipContent = "test"
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

data class RowDataTypes(
    val title: String,
    val value: String,
)