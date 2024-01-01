package com.carpick.carpickapp.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
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
import com.carpick.carpickapp.screen.TestResult.testCars
import com.carpick.carpickapp.screen.WishList.WishListBody
import com.carpick.carpickapp.screen.WishList.WishListHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                WishListPage(
                    wishListViewModel = hiltViewModel(),
                    onPressBack = {
                        finish()
                    },
                    onPressCarItem = {
                        val intent = Intent(this, CarDetailActivity::class.java)
                        intent.putExtra("idx", it)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@Composable
fun WishListPage(
    wishListViewModel: CarPickWishListViewModel,
    onPressBack: () -> Unit,
    onPressCarItem: (idx: Int) -> Unit
) {

    val testCarList = testCars

    var wishlistIds by remember {
        mutableStateOf<List<Int>>(listOf())
    }
    var wishlistCars by remember {
        mutableStateOf<List<RecommendedCar>>(listOf())
    }
    var dataReceived by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    fun getCars(ids: String) {
        scope.launch {
            wishListViewModel.getCarDetailData(ids).collect { cars ->
                Log.d("WishListActivity", "getCars: $cars")
                wishlistCars = cars
                dataReceived = true
            }
        }
    }

    fun init() {
        scope.launch {
            var _ids = mutableListOf<Int>()
            wishListViewModel.getWishlistData().collect {
                it.forEach {item ->
                    _ids.add(item.id)
                }
                wishlistIds = _ids
                if(_ids.isNotEmpty()) {
                    val ids = _ids.joinToString(",")
                    getCars(ids)
                }
                else {
                    dataReceived = true
                }
            }
        }
    }

    init()

    Log.d("WishListActivity", "wishlistCars: $wishlistCars")


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WishListHeader(
                onPressBack
            )
            WishListBody(
                wishlistIds,
                wishlistCars,
                testCarList,
                onPressCarItem,
                dataReceived
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CarpickAppTheme {
        WishListPage(
            wishListViewModel = hiltViewModel(),
            onPressBack = {
                Log.d("WishListActivity", "onPressBack")
            },
            onPressCarItem = {
                Log.d("WishListActivity", "onPressCarItem")
            }
        )
    }
}

