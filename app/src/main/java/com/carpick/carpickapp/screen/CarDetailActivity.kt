package com.carpick.carpickapp.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme

class CarDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = getIntent()
        val idx = intent.getIntExtra("idx", -1)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                CarDetailPage(
                    idx
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CarDetailPage(
    idx: Int
) {
    Log.d("CarDetailActivity", "idx: $idx")
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    CarpickAppTheme {
        CarDetailPage(
            -1
        )
    }
}