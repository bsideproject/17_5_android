package com.carpick.carpickapp.screen.TestResult

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.carpick.carpickapp.model.CarDetailTestModel
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.Tag
import com.carpick.carpickapp.screen.ui.theme.popupBackground

@Composable
fun TestResultDetail(
    onPressMoreAtSimpleSpec: () -> Unit,
    onPressRetest: () -> Unit,
    selectedCar: RecommendedCar,
    specRowDatas: List<List<RowDataTypes>>,
    tags: List<Tag>,
    isTestResultPage: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(popupBackground, shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),

    ) {
        BasicSpec(
            tags
        )
        SimpleSpec(
            onPressMoreAtSimpleSpec,
            selectedCar,
            specRowDatas
        )
        ResultDetailOption(
            selectedCar
        )
        DetailRetestButton(onPressRetest, isTestResultPage)
    }

}

