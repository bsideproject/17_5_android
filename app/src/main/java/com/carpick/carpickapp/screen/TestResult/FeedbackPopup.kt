package com.carpick.carpickapp.screen.TestResult

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.carpick.carpickapp.R

@Composable
fun FeedbackPopup(
    visible: Boolean,
    onDismissRequest: () -> Unit
) {
    var inputValue by remember {
        mutableStateOf("")
    }

    if(!visible) return

    Dialog(
        onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(24.dp, 0.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                FeedbackPopupHeader(onDismissRequest)
                FeedbackPopupTitle()
                FeedbackPopupButtonView()
                FeedbackInput(
                    inputValue,
                    onValueChange = {
                        inputValue = it
                    }
                )
            }
        }
    }
}

@Composable
fun FeedbackPopupHeader(
    onDismissRequest: () -> Unit
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
fun FeedbackPopupTitle() {
    Text(
        text = "차량 추천에 대해서 만족하시나요?",
        fontSize = 20.sp,
        fontWeight = FontWeight(700),
        color = Color(0xFF101317),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 0.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun FeedbackPopupButtonView(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 24.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FeedbackSelectBtn(
            type = "good"
        )
        Spacer(modifier = Modifier.width(36.dp))
        FeedbackSelectBtn(
            type = "bad"
        )
    }
}

@Composable
fun FeedbackSelectBtn(
    type: String
) {
    val btnImg = if(type === "good") R.drawable.feedback_good_on else R.drawable.feedback_bad_on
    val btnTxt = if(type === "good") "만족해요" else "아쉬워요"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            Log.d("FeedbackSelectBtn", "type: $type")
        }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color(0xFFD1DEFF), shape = RoundedCornerShape(64.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = btnImg),
                contentDescription = btnTxt,
                modifier = Modifier.size(32.dp)
            )

        }
        Text(
            text = btnTxt,
            fontSize = 14.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF3872FF),
            modifier = Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun FeedbackInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(20.dp, 24.dp, 20.dp, 0.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
    ) {
        TextField(
            value,
            onValueChange,
            placeholder = {
                Text(
                    text = "내용을 입력해 주세요. (선택)",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFB6B6CC)
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF4B4B6B),
            ),
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF2F2F6),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
        )
    }
}

