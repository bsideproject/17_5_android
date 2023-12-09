package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.TestResult.TestResultHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.screen.ui.theme.popupBackground

class TestResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                Page(
                    onPressBack = {
                        Log.d("TestResult", "onPressBack")
                    },
                    onPressWishList = {
                        Log.d("TestResult", "onPressWishList")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Page(
    onPressBack: () -> Unit,
    onPressWishList: () -> Unit
) {
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .border(1.dp, color = Color.Red)
                    .background(Color(0xFF21212F))
            ) {

            }
        }
    ) {paddingValues ->
        BackdropScaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            frontLayerShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            frontLayerScrimColor = Color.Unspecified,
            frontLayerBackgroundColor = popupBackground,
            backLayerBackgroundColor = Color.White,
            appBar = {
                TestResultHeader(
                    onPressBack,
                    onPressWishList
                )
            },
            backLayerContent = {
                TestResultBackLayer()
            },
            frontLayerContent = {
                TestResultDetail(scrollState)
            },
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CarpickAppTheme {
        Page(
            onPressBack = {
                Log.d("TestResult", "onPressBack")
            },
            onPressWishList = {
                Log.d("TestResult", "onPressWishList")
            }
        )
    }
}