package com.carpick.carpickapp.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carpick.carpickapp.screen.ui.theme.CarpickAppTheme
import com.carpick.carpickapp.screen.ui.theme.popupBackground

class TestResultActivity : ComponentActivity() {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Page() {
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)
    BackdropScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        frontLayerShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        frontLayerScrimColor = popupBackground,
        frontLayerBackgroundColor = Color.White,
        backLayerBackgroundColor = Color.White,
        appBar = {
            Header()
        },
        backLayerContent = {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.White)
            ) {

            }
        },
        frontLayerContent = {
            ResultDetail()
        },
    )
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White)
    ){

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultDetail() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = popupBackground,
    ) {
        LazyColumn{
            items(50) {
                ListItem(
                    text = { Text("Item $it", color = Color.White) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CarpickAppTheme {
        Page()
    }
}