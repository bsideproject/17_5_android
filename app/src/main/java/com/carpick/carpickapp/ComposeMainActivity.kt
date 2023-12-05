package com.carpick.carpickapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.carpick.carpickapp.screen.CarPoorTest
import com.carpick.carpickapp.screen.CarStanding
import com.carpick.carpickapp.screen.RecommandMyCar
import com.carpick.carpickapp.ui.theme.CarpickAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContent {
            CarpickAppTheme {
                // A surface container using the 'background' color from the theme
                AppMain()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMain() {
    val navController = rememberNavController()
    val navItems = listOf(
        NavigationItem("내 차 추천", "tab1"),
        NavigationItem("자동차 순위표", "tab2"),
        NavigationItem("카푸어 측정기", "tab3")
    )

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp).background(Color.Cyan),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                navItems.forEach { nav ->
                    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(IntrinsicSize.Max).fillMaxHeight().clickable {
                            navController.navigate(nav.route)
                        }
                    ) {
                        val isCurrentRoute = nav.route == currentRoute

                        Text(
                            text = nav.name,
                            color = if(isCurrentRoute) Color.Red else Color.Black
                        )
                    }
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = navItems.first().route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("tab1") {
                RecommandMyCar()
            }
            composable("tab2") {
                CarStanding()
            }
            composable("tab3") {
                CarPoorTest()
            }
        }
    }


}

data class NavigationItem(
    val name: String,
    val route: String
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CarpickAppTheme {
        AppMain()
    }
}