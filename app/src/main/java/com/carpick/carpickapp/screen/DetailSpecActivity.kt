package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecBody
import com.carpick.carpickapp.screen.DetailSpec.DetailSpecHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.screen.ui.theme.popupBackground

class DetailSpecActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                DetailSpecPage(
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
            DetailSpecBody(scrollState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    CarpickAppTheme {
        DetailSpecPage(
            onPressBack = {
                Log.d("DetailSpecActivity", "onPressBack")
            }
        )
    }
}