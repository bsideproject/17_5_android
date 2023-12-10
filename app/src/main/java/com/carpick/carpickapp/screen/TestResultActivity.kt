package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.TestResult.TestResultFooter
import com.carpick.carpickapp.screen.TestResult.TestResultHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme

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
                    onPressRetest = {
                        Log.d("TestResult", "onPressRetest")
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
    onPressRetest: () -> Unit,
    onPressShareBtn: () -> Unit,
    onPressAddWishListBtn: () -> Unit
) {
    val scrollState = rememberScrollState()
    val testCarList = mutableListOf<CarListItem>(
        CarListItem(0, "쏘나타 디 엣지", "2024년형 가솔린 2.0 하이브리드\n프리미엄 A/T", R.drawable.test_car_image, 1),
        CarListItem(1, "디 올 뉴 코나", "2023년형 가솔린 1.6 하이브리드\n모던 2WD A/T", R.drawable.test_car_image1, 2),
        CarListItem(2, "디 올 뉴 코나 일렉트릭", "2023년형 전기 (롱레인지)\n프리미엄 A/T", R.drawable.test_car_image1, 3),
    )
    var selectedIdx by remember {
        mutableStateOf(0)
    }

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
            TestResultBackLayer(
                testCarList,
                selectedIdx,
                onPressCarRankListItem = {idx ->
                    selectedIdx = idx

                }
            )
            TestResultDetail(onPressMoreAtSimpleSpec, onPressRetest)
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
            onPressRetest = {
                Log.d("TestResult", "onPressRetest")
            },
            onPressAddWishListBtn = {
                Log.d("TestResult", "onPressAddWishListBtn")
            }
        )
    }
}

data class CarListItem(
    val idx: Int,
    val name: String,
    val simpleInfo: String,
    val carImg: Int,
    val rank: Int,
)