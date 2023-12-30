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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecBody
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecHeader
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSpecActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()

        val data = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("carDetail", RecommendedCar::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("carDetail") as RecommendedCar?
        }
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                DetailSpecPage(
                    testResultViewModel = hiltViewModel(),
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
    testResultViewModel: CarPickTestResultViewModel,
    carData: RecommendedCar?,
    onPressBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    var specs by remember {
        mutableStateOf<List<List<RowDataTypes>>>(listOf())
    }

    if(carData != null) {
        specs = testResultViewModel.setSpecRowDatas(carData, false)
    }
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
            DetailSpecBody(scrollState, specs)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CarpickAppTheme {
        DetailSpecPage(
            testResultViewModel = hiltViewModel(),
            null,
            onPressBack = {
                Log.d("DetailSpecActivity", "onPressBack")
            }
        )
    }
}