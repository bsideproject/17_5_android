package com.carpick.carpickapp.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.TestResult.testCars
import com.carpick.carpickapp.screen.WishList.DeleteRequestPopup
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

    var wishlistIds by remember {
        mutableStateOf<List<Int>>(listOf())
    }
    var wishlistCars by remember {
        mutableStateOf<List<RecommendedCar>>(listOf())
    }
    var dataReceived by remember {
        mutableStateOf(false)
    }
    var deleteSelectedId by remember {
        mutableStateOf(-1)
    }
    var deleteConfirmPopupVisible by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    fun getCars(ids: String) {
        scope.launch {
            wishListViewModel.getCarDetailData(ids).collect { cars ->
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

    fun _onPressHeartIcon(idx: Int) {
        deleteSelectedId = idx
        deleteConfirmPopupVisible = true
    }

    fun _deleteWishlistItem() {
        deleteConfirmPopupVisible = false
        var newWishlistIds = wishlistIds.toMutableList()
        var newWishlistCars = wishlistCars.toMutableList()
        wishListViewModel.deleteWishlistById(deleteSelectedId)
        newWishlistIds = newWishlistIds.filter { it != deleteSelectedId }.toMutableList()
        newWishlistCars = newWishlistCars.filter { it.id != deleteSelectedId }.toMutableList()
        wishlistIds = newWishlistIds
        wishlistCars = newWishlistCars
        deleteSelectedId = -1
        Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
    }

    init()

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
                onPressCarItem,
                onPressHeartIcon = {
                    _onPressHeartIcon(it)
                },
                dataReceived
            )
        }
        DeleteRequestPopup(
            visible = deleteConfirmPopupVisible,
            onDismissRequest = {
                deleteConfirmPopupVisible = false
                deleteSelectedId = -1
            },
            deleteWishlistItem = {
                _deleteWishlistItem()
            }
        )
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

