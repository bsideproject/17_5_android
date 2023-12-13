package com.carpick.carpickapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.carpick.carpickapp.screen.WishList.WishListHeader
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme

class WishListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                Page()
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
fun Page() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        WishListHeader()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CarpickAppTheme {
        Page()
    }
}