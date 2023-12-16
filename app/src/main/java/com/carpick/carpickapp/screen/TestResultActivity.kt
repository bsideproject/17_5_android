package com.carpick.carpickapp.screen

import android.content.Intent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.carpick.carpickapp.MainActivity
import com.carpick.carpickapp.R
import com.carpick.carpickapp.model.CarDetailSpecTest
import com.carpick.carpickapp.model.CarDetailTestModel
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.TestResult.TestResultFooter
import com.carpick.carpickapp.screen.TestResult.TestResultHeader
import com.carpick.carpickapp.screen.TestResult.testCars
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.viewModel.NetworkTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                Page(
                    networkTestViewModel = hiltViewModel(),
                    onPressBack = {
                        Log.d("TestResult", "onPressBack")
                        finish()
                    },
                    onPressWishList = {it ->
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
    networkTestViewModel: NetworkTestViewModel,
    onPressBack: () -> Unit,
    onPressWishList: (specs: List<CarDetailSpecTest>) -> Unit,
    onPressMoreAtSimpleSpec: () -> Unit,
    onPressRetest: () -> Unit,
    onPressShareBtn: () -> Unit,
    onPressAddWishListBtn: () -> Unit
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val testCarList = testCars
    var selectedIdx by remember {
        mutableStateOf(0)
    }
    var selectedItem by remember {
        mutableStateOf<CarDetailTestModel>(testCarList[0])
    }

    fun _onPressWishList() {

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
                onPressWishList = {
                    _onPressWishList()
                }
            )
            TestResultBackLayer(
                testCarList,
                selectedItem,
                selectedIdx,
                onPressCarRankListItem = {idx ->
                    selectedIdx = idx
                    selectedItem = testCarList[idx]
                }
            )
            TestResultDetail(onPressMoreAtSimpleSpec, onPressRetest, selectedItem)
            TestResultFooter(onPressShareBtn, onPressAddWishListBtn)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CarpickAppTheme {
        Page(
            networkTestViewModel = hiltViewModel(),
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
    val price: Int
)