package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.google.android.material.color.MaterialColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestResultDetail(
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(popupBackground),
    ) {
        BasicSpec()
    }

}

@Composable
fun BasicSpec() {
    Column(
        modifier = Modifier.padding(0.dp, 32.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = "주요특징",
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(24.dp, 0.dp)
        )
        BasicSpecTags()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BasicSpecTags() {
    val testList = mutableListOf<String>("testa", "testb", "testc", "testd", "teste")
    FlowRow(
        modifier = Modifier.padding(20.dp, 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
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
    value: String
) {
    Box(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(99.dp))
                .padding(15.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = value,
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