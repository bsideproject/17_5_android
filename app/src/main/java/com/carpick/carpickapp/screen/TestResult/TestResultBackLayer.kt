package com.carpick.carpickapp.screen.TestResult

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.model.CarDetailTestModel
import com.carpick.carpickapp.model.RecommendCars
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.CarListItem
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TestResultBackLayer(
    testCarList: List<CarDetailTestModel>,
    recommendCars: List<RecommendedCar>,
    selectedCar: RecommendedCar,
    selectedItem: CarDetailTestModel,
    selectedIdx: Int,
    onPressCarRankListItem: (idx: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = "나에게\n가장 어울리는 차는",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 32.dp, 0.dp, 0.dp),
            fontSize = 18.sp,
            color = popupBackground,
            fontWeight = FontWeight(700)
        )
        Text(
            text = selectedCar.modelName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp, 0.dp, 0.dp),
            fontSize = 18.sp,
            color = popupBackground,
            fontWeight = FontWeight(700)
        )
        Text(
            text = selectedCar.detailModelName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp, 0.dp, 0.dp),
            fontSize = 14.sp,
            color = popupBackground,
            fontWeight = FontWeight(400)
        )
        Text(
            text = selectedCar.trimName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 0.dp, 0.dp, 8.dp),
            fontSize = 14.sp,
            color = popupBackground,
            fontWeight = FontWeight(400)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 32.dp)
            ,
            horizontalArrangement = Arrangement.End
        ) {
//            Image(
//                painter = painterResource(id = selectedItem.carImg),
//                contentDescription = "테스트용 자동차 이미지",
//                modifier = Modifier
//                    .width(360.dp)
//                    .height(170.dp),
//            )
            GlideImage(
                imageModel = selectedCar.carImageUrl,
                modifier = Modifier.width(360.dp).height(170.dp)
            )
        }
        CarRankListView(testCarList, recommendCars, selectedIdx, onPressCarRankListItem)

    }
}

@Composable
fun CarRankListView(
    testCarList: List<CarDetailTestModel>,
    recommendCars: List<RecommendedCar>,
    selectedIdx: Int,
    onPressCarRankListItem: (idx: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp, 0.dp, 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until recommendCars.size) {
            CarRankListItem(
                recommendCars[i],
                i,
                i == recommendCars.size-1,
                onPressCarRankListItem,
                selectedIdx
            )
        }
    }
}

@Composable
fun CarRankListItem(
    item: RecommendedCar,
    idx: Int,
    isLastIdx: Boolean,
    onPressCarRankListItem: (idx: Int) -> Unit,
    selectedIdx: Int,
) {

    Log.d("TestResultBackLayer", "$idx : ${item.toString()}")
    val width = if(isLastIdx) 50 else 60
    val color = if(selectedIdx == item.id) popupBackground else Color(0xFFD4D4E1)

    Row(
        modifier = Modifier.width(width = width.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .clickable {
                    onPressCarRankListItem(idx)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(color, shape = RoundedCornerShape(9.dp)),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    imageModel = item.carImageUrl,
                    modifier = Modifier.width(40.dp).height(20.dp)
                )
            }

            Text(
                text = "${idx+1}순위",
                fontSize = 12.sp,
                fontWeight = FontWeight(600),
                color = color
            )
        }
    }

}
