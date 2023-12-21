package com.carpick.carpickapp.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
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
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val data = intent.getSerializableExtra("response", RecommendCars::class.java)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                Page(
                    data,
                    onPressBack = {
                        Log.d("TestResult", "onPressBack")
                        finish()
                    },
                    onPressWishList = {
                        val intent = Intent(this, WishListActivity::class.java)
                        startActivity(intent)
                    },
                    onPressMoreAtSimpleSpec = {
                        Log.d("TestResult", "onPressMoreAtSimpleSpec")
                        val intent = Intent(this, DetailSpecActivity::class.java)
                        startActivity(intent)
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
    response: RecommendCars?,
    onPressBack: () -> Unit,
    onPressWishList: () -> Unit,
    onPressMoreAtSimpleSpec: () -> Unit,
    onPressRetest: () -> Unit,
    onPressShareBtn: () -> Unit,
    onPressAddWishListBtn: () -> Unit
) {
    val scrollState = rememberScrollState()

    val testCarList = testCars

    var selectedItem by remember {
        mutableStateOf<CarDetailTestModel>(testCarList[0])
    }

    var recommendCars by remember {
        mutableStateOf<List<RecommendedCar>>(response?.recommendCars ?: listOf())
    }

    var selectedCar by remember {
        mutableStateOf<RecommendedCar>(recommendCars[0])
    }

    var selectedIdx by remember {
        mutableStateOf(selectedCar.id)
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
                recommendCars,
                selectedCar,
                selectedItem,
                selectedIdx,
                onPressCarRankListItem = {idx ->
                    val newItem = recommendCars[idx]
                    selectedIdx = newItem.id
                    selectedCar = newItem
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
            null,
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