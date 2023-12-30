package com.carpick.carpickapp.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
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
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.SendFeedbackBody
import com.carpick.carpickapp.screen.TestResult.FeedbackPopup
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.TestResult.TestResultFooter
import com.carpick.carpickapp.screen.TestResult.TestResultHeader
import com.carpick.carpickapp.screen.TestResult.WishListAddToast
import com.carpick.carpickapp.screen.activity.MainActivity
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TestResultActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             intent.getSerializableExtra("response",RecommendCars::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("response") as RecommendCars?
        }

        setContent {
            CarpickAppTheme {
                Page(
                    testResultViewModel = hiltViewModel(),
                    data,
                    onPressWishList = {
                        val intent = Intent(this, WishListActivity::class.java)
                        startActivity(intent)
                    },
                    onPressMoreAtSimpleSpec = {
                        val intent = Intent(this, DetailSpecActivity::class.java)
                        intent.putExtra("carDetail", it)
                        startActivity(intent)
                    },
                    onBackPress = {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Page(
    testResultViewModel: CarPickTestResultViewModel,
    response: RecommendCars?,
    onPressWishList: () -> Unit,
    onPressMoreAtSimpleSpec: (carData: RecommendedCar) -> Unit,
    onBackPress: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    BackHandler(true, onBack = {
        onBackPress()
    })

    var wishlistIds by remember {
        mutableStateOf(mutableListOf<Int>())
    }

    var isIncludedInWishlist by remember {
        mutableStateOf(false)
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

    var specRowDatas by remember {
        mutableStateOf<List<List<RowDataTypes>>>(testResultViewModel.setSpecRowDatas(selectedCar))
    }

    var tags by remember {
        mutableStateOf(selectedCar.tags)
    }

    var feedbackPopupVisible by remember {
        mutableStateOf(false)
    }

    fun _getWishListData() {
        scope.launch {
            wishlistIds.clear()
            testResultViewModel.getWishlistData().collect {
                var ids = mutableListOf<Int>()
                it.forEach { item ->
                    ids.add(item.id)

                }
                isIncludedInWishlist = ids.contains(selectedIdx)
                wishlistIds = ids.distinct().toMutableList()
            }
        }
    }

    _getWishListData()

    fun _addWishList(selectedId: Int) {
        val wishlistSize = wishlistIds.size
        scope.launch {
            var message = ""
            if(wishlistSize < 15) {
                testResultViewModel.addWishList(selectedId)
                _getWishListData()
                message = "wishlist"
            }
            else {
                message = "wishlistFull"
            }
            val result = snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short,
            )
        }
    }

    fun _deleteWishList(selectedId: Int) {
        scope.launch {
            testResultViewModel.deleteWishList(selectedId)
            _getWishListData()
        }
    }

    fun _onPressWishListBtn() {
        val isWishlistIncluded = wishlistIds.contains(selectedIdx)

        if(isWishlistIncluded) {
            _deleteWishList(selectedIdx)
        } else {
            _addWishList(selectedIdx)
        }
    }

    fun _onPressCarRankListItem(idx: Int) {
        val newItem = recommendCars[idx]
        selectedIdx = newItem.id
        selectedCar = newItem

        isIncludedInWishlist = wishlistIds.contains(newItem.id)
        specRowDatas = testResultViewModel.setSpecRowDatas(newItem)
        tags = newItem.tags
    }

    fun _onPressSubmitAtFeedbackPopup(selectedValue: String, inputValue: String) {
        scope.launch {
            val isGood = selectedValue == "good"
            testResultViewModel.sendFeedback(SendFeedbackBody(is_good = isGood, content = inputValue)).collect {
                val result = snackbarHostState.showSnackbar(
                    message = "feedback",
                    duration = SnackbarDuration.Short,
                )
                onBackPress()
            }

        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxSize(),
                snackbar = { WishListAddToast(snackbarData = it) }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {
            TestResultHeader(
                isIncludedInWishlist,
                onPressShare = {
                    Log.d("TestResultActivity", "onPressShare")
                },
                onPressAddWishListBtn = {
                    _onPressWishListBtn()
                }
            )
            TestResultBackLayer(
                recommendCars,
                selectedCar,
                selectedIdx,
                onPressCarRankListItem = {idx ->
                    _onPressCarRankListItem(idx)
                }
            )
            TestResultDetail(
                onPressMoreAtSimpleSpec = {
                    onPressMoreAtSimpleSpec(selectedCar)
                },
                onPressRetest = {
                    feedbackPopupVisible = true
                },
                selectedCar,
                specRowDatas,
                tags
            )
            TestResultFooter(onPressWishList)
        }

        FeedbackPopup(
            visible = feedbackPopupVisible,
            onDismissRequest = {
                feedbackPopupVisible = false
            },
            onPressSubmit = { selectedValue, inputValue ->
                _onPressSubmitAtFeedbackPopup(selectedValue, inputValue)
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CarpickAppTheme {
        Page(
            testResultViewModel = hiltViewModel(),
            null,
            onPressWishList = {
                Log.d("TestResult", "onPressWishList")
            },
            onPressMoreAtSimpleSpec = {
                Log.d("TestResult", "onPressMoreAtSimpleSpec")
            },
            onBackPress = {
                Log.d("TestResult", "onBackPress")
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