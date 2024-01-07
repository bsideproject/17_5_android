package com.carpick.carpickapp.screen.CarDetail

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun CarDetailFooter(
    onPressShare: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(popupBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 48.dp, 16.dp, 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CarDetailFooterShareBtn(onPressShare)
        }
    }

}

@Composable
fun CarDetailFooterShareBtn(
    onPressShare: () -> Unit
) {
    Button(
        onClick = {
            onPressShare()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFD4D4E1)
        ),
        shape = RoundedCornerShape(99.dp)
    ) {
        Text(
            text = "공유하기",
            fontSize = 14.sp,
            color = popupBackground,
            fontFamily = PRETENDARD_BOLD
        )
    }
}