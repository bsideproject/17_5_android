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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun BasicSpec() {
    Column(
        modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        BasicSpecTitle()
        BasicSpecTags()
    }
}

@Composable
fun BasicSpecTitle() {
    Text(
        text = "주요특징",
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(24.dp, 0.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicSpecTags() {
    val testList = mutableListOf<HashTagData>(
        HashTagData("testa", Color.White, "tooltipa"),
        HashTagData("testb", Color.White, "tooltipb"),
        HashTagData("testc", Color.White, "tooltipc"),
        HashTagData("testd", Color.White, "tooltipd"),
        HashTagData("teste", Color.White, "tooltipe")
    )
    FlowRow(
        modifier = Modifier.padding(20.dp, 12.dp),
        horizontalArrangement = Arrangement.Start,
        maxItemsInEachRow = 4,
    ) {
        testList.forEach { hashTag ->
            run {
                HashTag(value = hashTag)
            }
        }
    }
}

@Composable
fun HashTag(
    value: HashTagData
) {

    TestResultCommonTooltip(
        arrowPosition = 0.7f,
        toolTipContent = value.tooltipContent,
        backgroundColor = value.backgroundColor,
        modifier = Modifier
            .padding(4.dp)
            .background(value.backgroundColor, shape = RoundedCornerShape(99.dp)),
    ) {balloonWindow ->
        Box(
            modifier = Modifier.clickable {
                balloonWindow.showAlignBottom()
            }
        ) {

            Row(
                modifier = Modifier
                    .background(value.backgroundColor, shape = RoundedCornerShape(99.dp))
                    .padding(15.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = value.content,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
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
                        fontWeight = FontWeight(500)
                    )
                }
            }
        }
    }


}

data class HashTagData(
    val content: String,
    val backgroundColor: Color,
    val tooltipContent: String
)