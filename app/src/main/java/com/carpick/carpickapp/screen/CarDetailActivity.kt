package com.carpick.carpickapp.screen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
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
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.Tag
import com.carpick.carpickapp.screen.CarDetail.CarDetailFooter
import com.carpick.carpickapp.screen.CarDetail.CarDetailHeader
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarDetailActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val idx = intent.getIntExtra("idx", -1)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                CarDetailPage(
                    idx,
                    testResultViewModel = hiltViewModel(),
                    wishListViewModel = hiltViewModel(),
                    onPressBack = {
                        finish()
                    },
                    onPressMoreAtSimpleSpec = {
                        val intent = Intent(this, DetailSpecActivity::class.java)
                        intent.putExtra("carDetail", it)
                        startActivity(intent)
                    },
                    onPressShare = {
//                        val sendIntent: Intent = Intent().apply {
//                            action = Intent.ACTION_SEND
//                            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
//                            type = "text/plain"
//                        }
//
//                        val shareIntent = Intent.createChooser(sendIntent, null)
//                        startActivity(shareIntent)
                        Log.d("CarDetailActivity", "onPressShare")
                    }
                )
            }
        }
    }
}

@Composable
fun CarDetailPage(
    idx: Int,
    testResultViewModel: CarPickTestResultViewModel,
    wishListViewModel: CarPickWishListViewModel,
    onPressBack: () -> Unit,
    onPressMoreAtSimpleSpec: (carData: RecommendedCar) -> Unit,
    onPressShare: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    var recommendCars by remember {
        mutableStateOf<List<RecommendedCar>>(listOf())
    }

    var selectedCar by remember {
        mutableStateOf<RecommendedCar?>(null)
    }

    var specRowDatas by remember {
        mutableStateOf<List<List<RowDataTypes>>>(listOf())
    }

    var tags by remember {
        mutableStateOf<List<Tag>>(listOf())
    }

    fun initData() {
        if(idx == -1) return
        scope.launch {
            wishListViewModel.getCarDetailData("$idx").collect {
                selectedCar = it[0]
                specRowDatas = testResultViewModel.setSpecRowDatas(it[0])
                tags = it[0].tags

            }
        }
    }

    initData()

    if(selectedCar == null) return


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        scaffoldState = scaffoldState,
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
        ) {
            CarDetailHeader(
                onPressBack
            )
            TestResultBackLayer(
                recommendCars,
                selectedCar!!,
                selectedIdx = selectedCar!!.id,
                onPressCarRankListItem = {},
                isTestResultPage = false
            )
            TestResultDetail(
                onPressMoreAtSimpleSpec = {
                    onPressMoreAtSimpleSpec(selectedCar!!)
                },
                onPressRetest = {},
                selectedCar!!,
                specRowDatas,
                tags,
                false
            )
            CarDetailFooter(onPressShare)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    CarpickAppTheme {
        CarDetailPage(
            -1,
            testResultViewModel = hiltViewModel(),
            wishListViewModel = hiltViewModel(),
            onPressBack = {},
            onPressMoreAtSimpleSpec = {},
            onPressShare = {}
        )
    }
}