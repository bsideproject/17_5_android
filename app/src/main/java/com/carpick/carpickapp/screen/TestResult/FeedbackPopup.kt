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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
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
    var neverShowForGood by remember {
        mutableStateOf(false)
    }
    var selectedValue by remember {
        mutableStateOf("")
    }

    fun onPressGood() {
        Log.d("FeedbackPopup", "onPressGood")
        selectedValue = "good"
    }

    fun onPressBad() {
        Log.d("FeedbackPopup", "onPressBad")
        selectedValue = "bad"
    }

    fun onPressSubmit() {
        Log.d("FeedbackPopup", "onPressSubmit")
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
                .padding(24.dp, 0.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                FeedbackPopupHeader(onDismissRequest)
                FeedbackPopupTitle()
                FeedbackPopupButtonView(
                    onPressGood = {
                        onPressGood()
                    },
                    onPressBad = {
                        onPressBad()
                    },
                    selectedValue
                )
                FeedbackInputGrid(
                    inputValue,
                    onValueChange = {
                        inputValue = it
                    },
                    neverShowForGood,
                    onCheckedChange = {
                        neverShowForGood = it
                    }
                )
                FeedbackPopupSubmitButton(
                    onPressSubmit = {
                        onPressSubmit()
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
    onPressGood: () -> Unit,
    onPressBad: () -> Unit,
    selectedValue: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 24.dp, 8.dp, 0.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FeedbackSelectBtn(
            type = "good",
            onPress = onPressGood,
            selectedValue == "good"
        )
        Spacer(modifier = Modifier.width(36.dp))
        FeedbackSelectBtn(
            type = "bad",
            onPress = onPressBad,
            selectedValue == "bad"
        )
    }
}

@Composable
fun FeedbackSelectBtn(
    type: String,
    onPress: () -> Unit,
    isSelected: Boolean
) {
    val btnImg = if(type === "good") {
        if(!isSelected) R.drawable.feedback_good_on
        else R.drawable.feedback_good_off
    } else {
        if(!isSelected) R.drawable.feedback_bad_on
        else R.drawable.feedback_bad_off
    }
    val btnTxt = if(type === "good") "만족해요" else "아쉬워요"
    val btnBgColor = if(isSelected) Color(0xFFEAF0FF) else Color(0xFFD1DEFF)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onPress()
        }
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(btnBgColor, shape = RoundedCornerShape(64.dp)),
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
fun FeedbackInputGrid(
    value: String,
    onValueChange: (String) -> Unit,
    neverShowForGood: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(0.dp, 24.dp, 0.dp, 0.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        FeedbackInput(value, onValueChange)
        FeedbackPopupNeverShowCheckBox(neverShowForGood, onCheckedChange)
    }
}

@Composable
fun FeedbackInput(
    value: String,
    onValueChange: (String) -> Unit
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
            .fillMaxWidth()
            .height(130.dp)
            .padding(8.dp, 0.dp, 8.dp, 0.dp),
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

@Composable
fun FeedbackPopupNeverShowCheckBox(
    neverShowForGood: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = neverShowForGood,
            onCheckedChange,
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color(0xFFD4D4E1)
            )
        )

        ClickableText(
            text = AnnotatedString("다시 보지 않기"),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF7A7AA2),
            ),
            onClick = {
                onCheckedChange(!neverShowForGood)
            },
            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun FeedbackPopupSubmitButton(
    onPressSubmit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 24.dp, 8.dp, 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(Color(0xFF3872FF), shape = RoundedCornerShape(33.dp))
                .clickable {
                    onPressSubmit()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "전송하기",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        }

//        Button(
//            onClick = {
//                onPressSubmit()
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(44.dp)
//                .background(Color(0xFF3872FF)),
//            shape = RoundedCornerShape(22.dp),
//        ) {
//            Text(
//                text = "전송하기",
//                fontSize = 16.sp,
//                fontWeight = FontWeight(700),
//                color = Color.White
//            )
//        }
    }

}

