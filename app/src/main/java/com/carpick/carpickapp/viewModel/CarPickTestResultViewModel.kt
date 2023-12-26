package com.carpick.carpickapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.model.TestModel
import com.carpick.carpickapp.repository.TestResultRepository
import com.carpick.carpickapp.screen.TestResult.ResultDetailOptionRowData
import com.carpick.carpickapp.screen.TestResult.RowDataTypes
import com.carpick.carpickapp.screen.TestResult.fuelTypeName
import com.carpick.carpickapp.screen.TestResult.specTooltips
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
            rowTotalDatas = mutableListOf(
                RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}", specTooltips["가격"]),
                RowDataTypes("차종", selectedCar.carBodyTypeName, specTooltips["차종"]),
                RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: "", specTooltips["연료"]),
                RowDataTypes("연비", "${selectedCar.fuelEconomy}km/l", specTooltips["연비"]),
            )

            if(selectedCar.displacement != null) {
                rowTotalDatas.add(RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}", specTooltips["배기량"]))
            }

            if(selectedCar.maximumPowerDescription != null) {
                rowTotalDatas.add(RowDataTypes("최고출력", "${selectedCar.maximumPowerDescription}ps/rpm", specTooltips["최고출력"]))
            }
        }
        else {
            rowTotalDatas = mutableListOf(
                RowDataTypes("가격", "${priceDec.format(selectedCar.price/10000)}", specTooltips["가격"]),
                RowDataTypes("차종", selectedCar.carBodyTypeName, specTooltips["차종"]),
                RowDataTypes("연료", fuelTypeName[selectedCar.fuelTypeName] ?: "", specTooltips["연료"]),

            )

            if(selectedCar.displacement != null) {
                rowTotalDatas.add(RowDataTypes("배기량", "${displacementDec.format(selectedCar.displacement)}", specTooltips["배기량"]))
            }

            rowTotalDatas.add(RowDataTypes("복합연비", "${selectedCar.combinedFuelEconomy}km/l", specTooltips["복합연비"]))
            rowTotalDatas.add(RowDataTypes("도심연비", "${selectedCar.cityFuelEconomy}km/l", specTooltips["도심연비"]))
            rowTotalDatas.add(RowDataTypes("고속연비", "${selectedCar.highwayFuelEconomy}km/l", specTooltips["고속연비"]))
            rowTotalDatas.add(RowDataTypes("전장", "${lengthDec.format(selectedCar.length)}", specTooltips["전장"]))
            rowTotalDatas.add(RowDataTypes("전폭", "${lengthDec.format(selectedCar.width)}", specTooltips["전폭"]))
            rowTotalDatas.add(RowDataTypes("전고", "${lengthDec.format(selectedCar.height)}", specTooltips["전고"]))

            if(selectedCar.engineTypeName != null && selectedCar.engineCylinderCount != null) {
                rowTotalDatas.add(RowDataTypes("엔진", "${selectedCar.engineTypeName} ${selectedCar.engineCylinderCount}기통", specTooltips["엔진"]))
            }
            if(selectedCar.maximumPowerDescription != null) {
                rowTotalDatas.add(RowDataTypes("최고출력", "${selectedCar.maximumPowerDescription}ps/rpm", specTooltips["최고출력"]))
            }
            if(selectedCar.maximumTorqueDescription != null) {
                rowTotalDatas.add(RowDataTypes("최대토크", "${selectedCar.maximumTorqueDescription}kg·m/rpm", specTooltips["최대토크"]))
            }
            if(selectedCar.wheelbase != null) {
                rowTotalDatas.add(RowDataTypes("축간거리", "${weelbaseDec.format(selectedCar.wheelbase)}", specTooltips["축간거리"]))
            }
            rowTotalDatas.add(RowDataTypes("변속기", "${transmissionName[selectedCar.transmissionTypeName] ?: ""} ${selectedCar.numberOfGears}단", specTooltips["변속기"]))

            if(selectedCar.zeroToHundred != null) {
                rowTotalDatas.add(RowDataTypes("제로백", "${selectedCar.zeroToHundred}", specTooltips["제로백"]))
            }

            if(selectedCar.frontSuspensionTypeName != null) {
                rowTotalDatas.add(RowDataTypes("서스펜션(전)", "${selectedCar.frontSuspensionTypeName}", specTooltips["서스펜션(전)"]))
            }
            if(selectedCar.rearSuspensionTypeName != null) {
                rowTotalDatas.add(RowDataTypes("서스펜션(후)", "${selectedCar.rearSuspensionTypeName}", specTooltips["서스펜션(후)"]))
            }
        }

        return rowTotalDatas.chunked(2)
    }


}