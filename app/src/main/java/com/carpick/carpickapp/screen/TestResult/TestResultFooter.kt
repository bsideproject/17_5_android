package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun TestResultFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(popupBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShareBtn()
            AddWishListBtn()
        }
    }
}

@Composable
fun ShareBtn() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(44.dp)
            .padding(0.dp, 0.dp, 4.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFD4D4E1)
        ),
        shape = RoundedCornerShape(99.dp)
    ) {
        Text(
            text = "공유하기",
            fontSize = 14.sp,
            color = popupBackground,
            fontWeight = FontWeight(700)
        )
    }
}

@Composable
fun AddWishListBtn() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(4.dp, 0.dp, 0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF3872FF)
        ),
        shape = RoundedCornerShape(99.dp)
    ) {
        Text(
            text = "위시리스트 담기",
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight(700)
        )
    }
}