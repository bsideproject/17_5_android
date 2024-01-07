package com.carpick.carpickapp.screen.CarDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_SEMI_BOLD
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun CarDetailHeader(
    onPressBack: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .drawBehind {
                val borderSize = 4.dp.toPx();
                drawLine(
                    color = Color(0xFFEFEFEF),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize
                )
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(16.dp, 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onPressBack()
                    }
            )

            Text(
                text = "위시리스트",
                fontSize = 16.sp,
                fontFamily = PRETENDARD_SEMI_BOLD,
                color = popupBackground
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
            )
        }
    }
}