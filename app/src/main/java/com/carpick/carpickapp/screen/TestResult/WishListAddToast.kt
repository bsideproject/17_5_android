package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarData
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import java.util.Locale

@Composable
fun WishListAddToast(
    snackbarData: SnackbarData
) {
    var title = ""
    var content = ""
    var contentVisible = false

    if(snackbarData.message == "wishlist") {
        title = "위시리스트에 추가되었습니다."
        content = "위시리스트 페이지에서 확인해주세요!"
        contentVisible = true
    }
    else if(snackbarData.message === "feedback") {
        title = "소중한 의견 감사합니다!"
    }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF3872FF),
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 0.dp, 16.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700)
                )
                if(contentVisible) {
                    Text(
                        text = content,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.ic_wishlist_add_close),
                contentDescription = "닫기",
                modifier = Modifier
                    .width(32.dp)
                    .height(16.dp)
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
                    .clickable {
                        snackbarData.dismiss()
                    }
            )
        }
    }
}