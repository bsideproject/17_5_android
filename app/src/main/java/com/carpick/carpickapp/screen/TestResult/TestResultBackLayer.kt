package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.ui.theme.popupBackground
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_BOLD
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_REGULAR
import com.carpick.carpickapp.screen.ui.theme.PRETENDARD_SEMI_BOLD
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TestResultBackLayer(
    recommendCars: List<RecommendedCar>,
    selectedCar: RecommendedCar,
    selectedIdx: Int,
    onPressCarRankListItem: (idx: Int) -> Unit,
    isTestResultPage: Boolean = true
) {

    val carNamePaddingTop = if(isTestResultPage) 8.dp else 32.dp
    val imagePaddingTop = if(isTestResultPage) 16.dp else 32.dp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        if(isTestResultPage) {
            Text(
                text = "나에게\n가장 어울리는 차는",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 8.dp, 0.dp, 0.dp),
                fontSize = with(LocalDensity.current) { 18.dp.toSp() },
                color = popupBackground,
                fontFamily = PRETENDARD_BOLD
            )
        }

        Text(
            text = "${selectedCar.carBrandName} ${selectedCar.modelName}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, carNamePaddingTop, 0.dp, 0.dp),
            fontSize = with(LocalDensity.current) { 24.dp.toSp() },
            color = popupBackground,
            fontFamily = PRETENDARD_BOLD
        )
        Text(
            text = selectedCar.detailModelName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp, 0.dp, 0.dp),
            fontSize = with(LocalDensity.current) { 14.dp.toSp() },
            color = popupBackground,
            fontFamily = PRETENDARD_REGULAR
        )
        Text(
            text = selectedCar.trimName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 0.dp, 0.dp, 8.dp),
            fontSize = with(LocalDensity.current) { 14.dp.toSp() },
            color = popupBackground,
            fontFamily = PRETENDARD_REGULAR
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, imagePaddingTop),
            horizontalArrangement = Arrangement.End
        ) {
            GlideImage(
                imageModel = selectedCar.carImageUrl,
                modifier = Modifier
                    .width(360.dp)
                    .height(170.dp)
            )
        }
        CarRankListView(recommendCars, selectedIdx, onPressCarRankListItem, isTestResultPage)

    }
}

@Composable
fun CarRankListView(
    recommendCars: List<RecommendedCar>,
    selectedIdx: Int,
    onPressCarRankListItem: (idx: Int) -> Unit,
    isTestResultPage: Boolean
) {
    if(!isTestResultPage) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 24.dp),
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
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    imageModel = item.carImageUrl,
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                )
            }

            Text(
                text = "${idx+1}순위",
                fontSize = with(LocalDensity.current) { 12.dp.toSp() },
                fontFamily = PRETENDARD_SEMI_BOLD,
                color = color
            )
        }
    }

}
