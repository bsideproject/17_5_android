package com.carpick.carpickapp.screen.DetailSpec

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.SimpleSpecBody
import com.carpick.carpickapp.screen.TestResult.fuelTypeName
import com.carpick.carpickapp.screen.TestResult.testCars
import com.carpick.carpickapp.screen.TestResult.transmissionName
import java.text.DecimalFormat

@Composable
fun DetailSpecBody(
    selectedCar: RecommendedCar,
    scrollState: ScrollState
) {
    val testSpecs = testCars[0].specs
    val priceDec = DecimalFormat("#,###만원")
    val displacementDec = DecimalFormat("#,###cc")
    val lengthDec = DecimalFormat("#,###mm")
    val weelbaseDec = DecimalFormat("#,###mm")
    var rowTotalDatas = listOf<RowDataTypes>(
        RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}"),
        RowDataTypes("차종", selectedCar.carBodyTypeName),
        RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: ""),
        RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}"),
        RowDataTypes("복합연비", "${selectedCar.combinedFuelEconomy}km/l"),
        RowDataTypes("도심연비", "${selectedCar.cityFuelEconomy}km/l"),
        RowDataTypes("고속연비", "${selectedCar.highwayFuelEconomy}km/l"),
        RowDataTypes("전장", "${lengthDec.format(selectedCar.length)}"),
        RowDataTypes("전폭", "${lengthDec.format(selectedCar.width)}"),
        RowDataTypes("전고", "${lengthDec.format(selectedCar.height)}"),
        RowDataTypes("엔진", "${selectedCar.engineTypeName} ${selectedCar.engineCylinderCount}기통"),
        RowDataTypes("최대출력", "${selectedCar.maximumPowerDescription}ps/rpm"),
        RowDataTypes("최고토크", "${selectedCar.maximumTorqueDescription}kg·m/rpm"),
        RowDataTypes("축간거리", "${weelbaseDec.format(selectedCar.wheelbase)}"),
        RowDataTypes("변속기", "${transmissionName[selectedCar.transmissionTypeName] ?: ""} ${selectedCar.numberOfGears}단"),
    )
    var chunkedTotalDatas = rowTotalDatas.chunked(2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        SimpleSpecBody(chunkedTotalDatas, 24)
    }
}