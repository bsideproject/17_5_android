package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.carpick.carpickapp.R
import com.carpick.carpickapp.screen.WishList.WishListBody
import com.carpick.carpickapp.screen.WishList.WishListHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme

class WishListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                WishListPage(
                    onPressBack = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun WishListPage(
    onPressBack: () -> Unit
) {

    val testCarList = mutableListOf<CarListItem>(
        CarListItem(0, "쏘나타 디 엣지", "2024년형 가솔린 2.0 하이브리드\n프리미엄 A/T", R.drawable.wishlist_test_car_img1, 1, 31870000),
        CarListItem(1, "디 올 뉴 코나", "2023년형 가솔린 1.6 하이브리드\n모던 2WD A/T", R.drawable.wishlist_test_car_img2, 2, 29990000),
    )
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
            WishListBody(testCarList)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CarpickAppTheme {
        WishListPage(
            onPressBack = {
                Log.d("WishListActivity", "onPressBack")
            }
        )
    }
}

