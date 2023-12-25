package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.repository.TestResultRepository
import com.carpick.carpickapp.screen.TestResult.ResultDetailOptionRowData
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.fuelTypeName
import com.carpick.carpickapp.screen.TestResult.transmissionName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CarPickTestResultViewModel @Inject constructor(
    private val testResultRepository: TestResultRepository
) : ViewModel() {

    fun getWishlistData(): Flow<List<TestModel>> {
        return testResultRepository.getWishlistData()
    }

    fun addWishList(selectedId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            testResultRepository.insertWishList(TestModel(selectedId))
        }
    }

    fun deleteWishList(selectedId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            testResultRepository.deleteWishlistById(selectedId)
        }
    }

    fun setSpecRowDatas(selectedCar: RecommendedCar, isSimple: Boolean = true): List<List<RowDataTypes>> {
        val priceDec = DecimalFormat("#,###만원")
        val displacementDec = DecimalFormat("#,###cc")
        val lengthDec = DecimalFormat("#,###mm")
        val weelbaseDec = DecimalFormat("#,###mm")

        var rowTotalDatas = mutableListOf<RowDataTypes>()

        if (isSimple) {
            rowTotalDatas.add(RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}"))
            rowTotalDatas.add(RowDataTypes("차종", selectedCar.carBodyTypeName))
            rowTotalDatas.add(RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: ""))
            rowTotalDatas.add(RowDataTypes("연비", "${selectedCar.fuelEconomy}km/l"))
            rowTotalDatas.add(RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}"))
            rowTotalDatas.add(RowDataTypes("최대출력", "${selectedCar.maximumPowerDescription}ps/rpm"))
        }
        else {
            rowTotalDatas.add(RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}"))
            rowTotalDatas.add(RowDataTypes("차종", selectedCar.carBodyTypeName))
            rowTotalDatas.add(RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: ""))
            rowTotalDatas.add(RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}"))
            rowTotalDatas.add(RowDataTypes("복합연비", "${selectedCar.combinedFuelEconomy}km/l"))
            rowTotalDatas.add(RowDataTypes("도심연비", "${selectedCar.cityFuelEconomy}km/l"))
            rowTotalDatas.add(RowDataTypes("고속연비", "${selectedCar.highwayFuelEconomy}km/l"))
            rowTotalDatas.add(RowDataTypes("전장", "${lengthDec.format(selectedCar.length)}"))
            rowTotalDatas.add(RowDataTypes("전폭", "${lengthDec.format(selectedCar.width)}"))
            rowTotalDatas.add(RowDataTypes("전고", "${lengthDec.format(selectedCar.height)}"))
            rowTotalDatas.add(RowDataTypes("엔진", "${selectedCar.engineTypeName} ${selectedCar.engineCylinderCount}기통"))
            rowTotalDatas.add(RowDataTypes("최대출력", "${selectedCar.maximumPowerDescription}ps/rpm"))
            rowTotalDatas.add(RowDataTypes("최고토크", "${selectedCar.maximumTorqueDescription}kg·m/rpm"))
            rowTotalDatas.add(RowDataTypes("축간거리", "${weelbaseDec.format(selectedCar.wheelbase)}"))
            rowTotalDatas.add(RowDataTypes("변속기", "${transmissionName[selectedCar.transmissionTypeName] ?: ""} ${selectedCar.numberOfGears}단"))
        }

        return rowTotalDatas.chunked(2)
    }


}