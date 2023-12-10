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
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
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
import com.carpick.carpickapp.screen.TestResult.TestResultFooter
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
                    },
                    onPressMoreAtSimpleSpec = {
                        Log.d("TestResult", "onPressMoreAtSimpleSpec")
                    },
                    onPressShareBtn = {
                        Log.d("TestResult", "onPressShareBtn")
                    },
                    onPressAddWishListBtn = {
                        Log.d("TestResult", "onPressAddWishListBtn")
                    }
                )
            }
        }
    }
}

@Composable
fun Page(
    onPressBack: () -> Unit,
    onPressWishList: () -> Unit,
    onPressMoreAtSimpleSpec: () -> Unit,
    onPressShareBtn: () -> Unit,
    onPressAddWishListBtn: () -> Unit
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            TestResultHeader(
                onPressBack,
                onPressWishList
            )
            TestResultBackLayer()
            TestResultDetail(onPressMoreAtSimpleSpec)
            TestResultFooter(onPressShareBtn, onPressAddWishListBtn)
        }

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
            },
            onPressMoreAtSimpleSpec = {
                Log.d("TestResult", "onPressMoreAtSimpleSpec")
            },
            onPressShareBtn = {
                Log.d("TestResult", "onPressShareBtn")
            },
            onPressAddWishListBtn = {
                Log.d("TestResult", "onPressAddWishListBtn")
            }
        )
    }
}