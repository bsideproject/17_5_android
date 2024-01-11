package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun TestResultFooter(
    onPressWishlist: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(popupBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WishListBtn(onPressWishlist)
        }
    }
}

@Composable
fun WishListBtn(
    onPressWishlist: () -> Unit
) {
    Button(
        onClick = {
            onPressWishlist()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF3872FF)
        ),
        shape = RoundedCornerShape(99.dp)
    ) {
        Text(
            text = "위시리스트 바로가기",
            fontSize = with(LocalDensity.current) { 14.dp.toSp() },
            color = Color.White,
            fontFamily = PRETENDARD_BOLD
        )
    }
}