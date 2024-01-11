package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD

@Composable
fun DetailRetestButton(
    onPressRetest: () -> Unit,
    isTestResultPage: Boolean
) {
    if(!isTestResultPage) return

    Row(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 28.dp, 0.dp, 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickable {
                onPressRetest()
            },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_replay),
                contentDescription = "다시 테스트",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "다른 차 추천 받기",
                fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                color = Color.White,
                fontFamily = PRETENDARD_BOLD
            )
        }
    }
}