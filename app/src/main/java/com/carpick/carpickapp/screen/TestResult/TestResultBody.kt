package com.carpick.carpickapp.screen.TestResult

import androidx.compose.runtime.Composable
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.viewModel.CarPickTestResultViewModel

@Composable
fun TestResultBody(
    testResultViewModel: CarPickTestResultViewModel,
    recommendCars: List<RecommendedCar>,
    onPressMoreAtSimpleSpec: (item: RecommendedCar) -> Unit,
    onPressRetest: () -> Unit,
    selectedIdx: Int,
) {
    TestResultDetail(
        onPressMoreAtSimpleSpec = {
            onPressMoreAtSimpleSpec(recommendCars[0])
        },
        onPressRetest,
        selectedCar = recommendCars[0],
        specRowDatas = testResultViewModel.setSpecRowDatas(recommendCars[0]),
        tags = recommendCars[0].tags,
        true,
        selectedIdx == recommendCars[0].id
    )
    TestResultDetail(
        onPressMoreAtSimpleSpec = {
            onPressMoreAtSimpleSpec(recommendCars[1])
        },
        onPressRetest,
        selectedCar = recommendCars[1],
        specRowDatas = testResultViewModel.setSpecRowDatas(recommendCars[1]),
        tags = recommendCars[1].tags,
        true,
        selectedIdx == recommendCars[1].id
    )
    TestResultDetail(
        onPressMoreAtSimpleSpec = {
            onPressMoreAtSimpleSpec(recommendCars[2])
        },
        onPressRetest,
        selectedCar = recommendCars[2],
        specRowDatas = testResultViewModel.setSpecRowDatas(recommendCars[2]),
        tags = recommendCars[2].tags,
        true,
        selectedIdx == recommendCars[2].id
    )
}