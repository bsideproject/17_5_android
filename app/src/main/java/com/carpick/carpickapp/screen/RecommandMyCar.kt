package com.carpick.carpickapp.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.viewModel.CarPickWishListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun RecommandMyCar() {
    RecommandMyCarView(
        wishListViewModel = hiltViewModel()
    )
}

@Composable
fun RecommandMyCarView(
    wishListViewModel: CarPickWishListViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val context = LocalContext.current
        Column {
            Text(text = "내 차 추천")
            // 테스트 하기 위해 임의로 그냥 모델 하드 코딩
            Button(onClick = {
                wishListViewModel.insertTestData(TestModel(1, "insert test"))
            }) {
                Text(text = "insert 테스트")
            }

            Button(onClick = {
                wishListViewModel.deleteTestData(TestModel(1, "insert test"))
            }) {
                Text(text = "delete 테스트")
            }

            Button(onClick = {
                val intent = Intent(context, BottomNavTestActivity::class.java)
                context.startActivity(intent)
            }) {
                Text(text = "select 테스트")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommandMyCarPreview() {
    RecommandMyCarView(
        wishListViewModel = hiltViewModel()
    )
}