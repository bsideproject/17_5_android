package com.carpick.carpickapp.screen.WishList

import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun DeleteRequestPopup(
    visible: Boolean,
    onDismissRequest: () -> Unit,
) {

    fun _onPressNo() {
        Log.d("DeleteRequestPopup", "onPressNo")
        onDismissRequest()
    }

    fun _onPressYes() {
        Log.d("DeleteRequestPopup", "onPressYes")
        onDismissRequest()
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
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
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
            .padding(),
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
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Text(
            text = "위시리스트에서 추천받은 차량을\n삭제하시겠어요?",
            fontSize = 16.sp,
            color = Color(0xFF101317),
            fontWeight = FontWeight(500)
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
            .padding(4.dp, 0.dp, 4.dp, 12.dp),
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
            fontSize = 16.sp,
            color = Color(0xFF101317),
            fontWeight = FontWeight(700)
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
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight(700)
        )
    }
}