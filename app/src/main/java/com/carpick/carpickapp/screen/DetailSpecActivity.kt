package com.carpick.carpickapp.screen

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecBody
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.screen.ui.theme.popupBackground

class DetailSpecActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val data = intent.getSerializableExtra("carDetail", RecommendedCar::class.java)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                DetailSpecPage(
                    data,
                    onPressBack = {
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun DetailSpecPage(
    carData: RecommendedCar?,
    onPressBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = popupBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DetailSpecHeader(
                onPressBack
            )
            if(carData != null) {
                DetailSpecBody(carData, scrollState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CarpickAppTheme {
        DetailSpecPage(
            null,
            onPressBack = {
                Log.d("DetailSpecActivity", "onPressBack")
            }
        )
    }
}