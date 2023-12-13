package com.carpick.carpickapp.screen.WishList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun WishListBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F6)),

    ) {
        WishListEmptyBody()
    }
}

@Composable
fun WishListEmptyBody() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "앗!",
            fontSize = 32.sp,
            color = popupBackground,
            fontWeight = FontWeight(700)
        )

        Text(
            text = "위시리스트가 비어있어요.\n추천 받은 차를 위시리스트에\n담아보세요",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color(0xFF9898B7),
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.img_empty),
            contentDescription = "빈 위시리스트",
            modifier = Modifier
                .width(124.dp)
                .height(150.dp)
                .padding(0.dp, 0.dp, 0.dp, 32.dp)
        )

        WishListBodyTestBtn()
    }
}

@Composable
fun WishListBodyTestBtn() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(80.dp, 0.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = popupBackground
            ),
            shape = RoundedCornerShape(99.dp)
        ) {
            Text(
                text = "테스트 다시하기",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight(700)
            )
        }
    }
}