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
import com.carpick.carpickapp.screen.TestResult.testCars
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

    val testCarList = testCars
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

