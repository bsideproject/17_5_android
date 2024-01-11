package com.carpick.carpickapp.screen.WishList

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_MEDIUM

@Composable
fun DeleteRequestPopup(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    deleteWishlistItem:() -> Unit
) {

    fun _onPressNo() {
        onDismissRequest()
    }

    fun _onPressYes() {
        deleteWishlistItem()
    }

    if(!visible) return

    Dialog(
        onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 0.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                DeleteRequestPopupHeader(onDismissRequest)
                DeleteRequestPopupBody()
                DeleteRequestPopupFooter(
                    onPressNo = {
                        _onPressNo()
                    },
                    onPressYes = {
                        _onPressYes()
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun DeleteRequestPopupHeader(
    onDismissRequest: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.feedback_popup_close),
            contentDescription = "닫기",
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    onDismissRequest()
                }
        )
    }
}

@Composable
fun DeleteRequestPopupBody(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Text(
            text = "위시리스트에서 추천받은 차량을\n삭제하시겠어요?",
            fontSize = with(LocalDensity.current) { 16.dp.toSp() },
            color = Color(0xFF101317),
            fontFamily = PRETENDARD_MEDIUM
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )
    }

}

@Composable
fun DeleteRequestPopupFooter(
    onPressNo: () -> Unit,
    onPressYes: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DeleteRequestPopupCancelBtn(onPressNo)
        DeleteRequestPopupConfirmBtn(onPressYes)
    }
}

@Composable
fun DeleteRequestPopupCancelBtn(
    onPress: () -> Unit
) {
    Button(
        onClick = { onPress() },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(44.dp)
            .padding(0.dp, 0.dp, 6.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF0F1F3)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = "아니요",
            fontSize = with(LocalDensity.current) { 16.dp.toSp() },
            color = Color(0xFF101317),
            fontFamily = PRETENDARD_BOLD
        )
    }
}
@Composable
fun DeleteRequestPopupConfirmBtn(
    onPress: () -> Unit
) {
    Button(
        onClick = { onPress() },
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(44.dp)
            .padding(6.dp, 0.dp, 0.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFEB2B2B)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = "삭제하기",
            fontSize = with(LocalDensity.current) { 16.dp.toSp() },
            color = Color.White,
            fontFamily = PRETENDARD_BOLD
        )
    }
}