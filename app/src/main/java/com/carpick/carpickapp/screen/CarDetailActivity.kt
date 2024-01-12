package com.carpick.carpickapp.screen

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.Tag
import com.carpick.carpickapp.screen.CarDetail.CarDetailFooter
import com.carpick.carpickapp.screen.CarDetail.CarDetailHeader
import com.carpick.carpickapp.screen.CarDetail.CarDetailLoading
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.TestResultBackLayer
import com.carpick.carpickapp.screen.TestResult.TestResultDetail
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.Firebase
import com.google.firebase.dynamiclinks.androidParameters
import com.google.firebase.dynamiclinks.dynamicLinks
import com.google.firebase.dynamiclinks.shortLinkAsync
import com.google.firebase.dynamiclinks.socialMetaTagParameters
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
                    shareResultByLink = {link ->
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            "https://carpick.page.link/?link=$link"
                        )
                        sendIntent.type = "text/plain"
                        startActivity(sendIntent)
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
    shareResultByLink: (link: String) -> Unit
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.White)
    }

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
    var loading by remember {
        mutableStateOf<Boolean>(true)
    }

    fun initData() {
        if(idx == -1) return
        scope.launch {
            wishListViewModel.getCarDetailData("$idx").collect {
                selectedCar = it[0]
                specRowDatas = testResultViewModel.setSpecRowDatas(it[0])
                tags = it[0].tags
                loading = false

            }
        }
    }

    fun _onPressShare(){
        val dynamicLink = Firebase.dynamicLinks.shortLinkAsync {
            link = Uri.parse("https://carpick.page.link")
            domainUriPrefix = "https://carpick.page.link"
            androidParameters("com.carpick.carpickapp") {
                minimumVersion = 1
                setFallbackUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.carpick.carpickapp"))
            }
            socialMetaTagParameters {
                title = "카픽"
                description = "어떤 차를 사야 할지 고민이라면? 일상에 핏한 맞춤형 차량 추천 서비스 - 카픽"
                imageUrl = Uri.parse("https://kr.object.ncloudstorage.com/carpick/kakao-thumbnail.png")
            }
        }
            .addOnSuccessListener {
                shareResultByLink(it.shortLink.toString())
            }
            .addOnFailureListener {
                Toast.makeText(context, "공유 링크 생성에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
    }

    initData()

    if(selectedCar == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CarDetailLoading(visible = loading)
        }
    }
    else {
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
                    context,
                    onPressMoreAtSimpleSpec = {
                        onPressMoreAtSimpleSpec(selectedCar!!)
                    },
                    onPressRetest = {},
                    selectedCar!!,
                    specRowDatas,
                    tags,
                    false
                )
                CarDetailFooter(
                    onPressShare = {
                        _onPressShare()
                    },

                    )
            }
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
            shareResultByLink = {}
        )
    }
}