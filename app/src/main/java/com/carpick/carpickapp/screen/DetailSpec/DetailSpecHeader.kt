package com.carpick.carpickapp.screen.DetailSpec

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_SEMI_BOLD

@Composable
fun DetailSpecHeader(
    onPressBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_white),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onPressBack()
                    },
            )

            Text(
                text = "상세스펙",
                fontSize = with(LocalDensity.current) { 16.dp.toSp() },
                color = Color.White,
                fontFamily = PRETENDARD_SEMI_BOLD
            )

            Box(
                modifier = Modifier.size(30.dp)
            )
        }
    }
}