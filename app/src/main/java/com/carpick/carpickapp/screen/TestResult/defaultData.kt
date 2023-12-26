package com.carpick.carpickapp.screen.TestResult

import androidx.compose.ui.graphics.Color
import com.carpick.carpickapp.R
import com.carpick.carpickapp.model.CarDetailHashTagTest
import com.carpick.carpickapp.model.CarDetailOptionTest
import com.carpick.carpickapp.model.CarDetailSpecTest
import com.carpick.carpickapp.model.CarDetailTestModel
import com.carpick.carpickapp.model.RecommendedCar
import com.carpick.carpickapp.screen.CarListItem

val testCars: List<CarDetailTestModel> = listOf(
    CarDetailTestModel(
        0,
        "쏘나타 디 엣지",
        "2024년형 가솔린 2.0 하이브리드\n프리미엄 A/T",
        R.drawable.test_car_image,
        1,
        31870000,
        listOf(
            CarDetailHashTagTest("세단", Color.White, "테스트1"),
            CarDetailHashTagTest("안정적인 서스펜션", Color.White, "테스트2"),
            CarDetailHashTagTest("70대 여성 선호차량", Color.White, "테스트3"),
            CarDetailHashTagTest("시내 주행 최적화", Color.White, "테스트4"),
            CarDetailHashTagTest("유지보수가 쉬운 차량", Color.White, "테스트5"),
            CarDetailHashTagTest("팝앤뱅 차량", Color.White, "테스트6"),
        ),
        listOf(
            CarDetailSpecTest("가격", "3,187만원","tooltip test"),
            CarDetailSpecTest("차종", "준중형","tooltip test"),
            CarDetailSpecTest("연료", "하이브리드","tooltip test"),
            CarDetailSpecTest("연비", "20.9km/l","tooltip test"),
            CarDetailSpecTest("배기량", "1,987cc","tooltip test"),
            CarDetailSpecTest("최고출력", "152/6000ps/rpm","tooltip test"),
            CarDetailSpecTest("최고토크", "19.2/4400~5200kgm/rpm","tooltip test"),
            CarDetailSpecTest("엔진", "직렬 4기통","tooltip test"),
            CarDetailSpecTest("전폭", "1,780mm","tooltip test"),
            CarDetailSpecTest("전고", "1,420mm","tooltip test"),
        ),
        listOf(
            CarDetailOptionTest("안전", "에어백(운전석, 동승석, 사이드(앞), 커튼), 주행 안전(ABS, 전방 추돌 경우)", "test tooltip"),
            CarDetailOptionTest("외장", "헤드램프(LED), 주간 주행등", "test tooltip"),
            CarDetailOptionTest("내장", "스티어링 휠(가죽), 기어 노브(전자식 노브), 계기판(디지털)", "test tooltip"),
            CarDetailOptionTest("편의", "정속주행(cc(차간조절)), 주차 브레이크(전자식, 오토홀드), 엔진시동(버튼시동)", "test tooltip"),
        )
    ),
    CarDetailTestModel(
        1,
        "디 올 뉴 코나",
        "2023년형 가솔린 1.6 하이브리드\n모던 2WD A/T",
        R.drawable.test_car_image1,
        2,
        29990000,
        listOf(
            CarDetailHashTagTest("소형SUV", Color.White, "테스트1"),
            CarDetailHashTagTest("안정적인 서스펜션", Color.White, "테스트2"),
            CarDetailHashTagTest("70대 여성 선호차량", Color.White, "테스트3"),
            CarDetailHashTagTest("시내 주행 최적화", Color.White, "테스트4"),
            CarDetailHashTagTest("유지보수가 쉬운 차량", Color.White, "테스트5"),
            CarDetailHashTagTest("팝앤뱅 차량", Color.White, "테스트6"),
        ),
        listOf(
            CarDetailSpecTest("가격", "2,999만원","tooltip test"),
            CarDetailSpecTest("차종", "소형SUV","tooltip test"),
            CarDetailSpecTest("연료", "하이브리드","tooltip test"),
            CarDetailSpecTest("연비", "19.8km/l","tooltip test"),
            CarDetailSpecTest("배기량", "1,580cc","tooltip test"),
            CarDetailSpecTest("최고출력", "105/5700ps/rpm","tooltip test"),
            CarDetailSpecTest("최고토크", "14.7/4000kgm/rpm","tooltip test"),
            CarDetailSpecTest("엔진", "직렬 4기통","tooltip test"),
            CarDetailSpecTest("전폭", "1,825mm","tooltip test"),
            CarDetailSpecTest("전고", "1,580mm","tooltip test"),
        ),
        listOf(
            CarDetailOptionTest("안전", "에어백(운전석, 동승석, 사이드(앞), 커튼), 주행 안전(ABS, 전방 추돌 경우)", "test tooltip"),
            CarDetailOptionTest("외장", "헤드램프(LED), 주간 주행등", "test tooltip"),
            CarDetailOptionTest("내장", "스티어링 휠(가죽), 기어 노브(전자식 노브), 계기판(디지털)", "test tooltip"),
            CarDetailOptionTest("편의", "정속주행(cc(차간조절)), 주차 브레이크(전자식, 오토홀드), 엔진시동(버튼시동)", "test tooltip"),
        )
    ),
    CarDetailTestModel(
        2,
        "디 올 뉴 코나 일렉트릭",
        "2023년형 전기 (롱레인지)\n프리미엄 A/T",
        R.drawable.test_car_image1,
        3,
        47520000,
        listOf(
            CarDetailHashTagTest("소형SUV", Color.White, "테스트1"),
            CarDetailHashTagTest("안정적인 서스펜션", Color.White, "테스트2"),
            CarDetailHashTagTest("70대 여성 선호차량", Color.White, "테스트3"),
            CarDetailHashTagTest("시내 주행 최적화", Color.White, "테스트4"),
            CarDetailHashTagTest("유지보수가 쉬운 차량", Color.White, "테스트5"),
            CarDetailHashTagTest("팝앤뱅 차량", Color.White, "테스트6"),
        ),
        listOf(
            CarDetailSpecTest("가격", "4,752만원","tooltip test"),
            CarDetailSpecTest("차종", "소형SUV","tooltip test"),
            CarDetailSpecTest("연료", "전기","tooltip test"),
            CarDetailSpecTest("연비", "5.5 ㎞/kWh","tooltip test"),
            CarDetailSpecTest("전폭", "1,825mm","tooltip test"),
            CarDetailSpecTest("전고", "1,580mm","tooltip test"),
        ),
        listOf(
            CarDetailOptionTest("안전", "에어백(운전석, 동승석, 사이드(앞), 커튼), 주행 안전(ABS, 전방 추돌 경우)", "test tooltip"),
            CarDetailOptionTest("외장", "헤드램프(LED), 주간 주행등", "test tooltip"),
            CarDetailOptionTest("내장", "스티어링 휠(가죽), 기어 노브(전자식 노브), 계기판(디지털)", "test tooltip"),
            CarDetailOptionTest("편의", "정속주행(cc(차간조절)), 주차 브레이크(전자식, 오토홀드), 엔진시동(버튼시동)", "test tooltip"),
        )
    )
)

var fuelTypeName = mapOf<String, String>("GASOLINE" to "가솔린", "DIESEL" to "디젤", "EV" to "전기", "HYBRID" to "하이브리드")
val transmissionName = mapOf<String, String>("AUTO" to "오토", "MANUAL" to "수동", "CVT" to "CVT", "DCT" to "DCT")
val specTooltips = mapOf<String, String>(
    "연비" to "1리터 당 차량이 주행가능한 거리를 의미해요.",
    "배기량" to "배기량이 큰 엔진일 수록 성능이 더 좋아서 폭발적인 속도를 낼 수 있어요. 그런데 일반적으로 그만큼 세금이 더 높게 부과돼요.",
    "촤고출력" to "최고출력은 엔진이 낼 수 있는 가장 큰 힘을 말해요. PS는 엔진이 1초에 할 수 있는 일을 나타내는 단위로, PS가 클 수록 엔진이 할 수 있는 양이 많아져요. RPM은 엔진이 1분에 몇 번 회전하는지를 나타내는 단위로, RPM이 높을수록 엔진이 더욱 빠르게 회전해요.",
    "최대토크" to "토크는 엔진의 회전력을 말하며, 엔진이 얼마나 강한 힘을 가지고 있는지를 나타내요. 토크가 좋을수록 자동차의 가속력이 좋다고 느낄 수 있어요.",
    "전장" to "차량의 옆면에서 보았을 때 가장 앞쪽부터 뒷쪽까지의 길이를 뜻해요.",
    "전폭" to "차량의 앞면에서 보았을 때 가장 왼쪽에서 오른쪽까지의 폭을 뜻해요.",
    "전고" to "차량의 접지면부터 가장 높은 곳까지의 높이를 뜻해요.",
    "엔진" to "직렬 엔진은 실린더가 I 자로 정렬해 있는 모양이고, V형 엔진은 실린더가 V자로 정렬되어 있어요. 기통 숫자는 몇 개의 실린더가 있는지를 의미해요. 실린더가 많을수록 더욱 빨리 달릴 수 있어요",
    "변속기" to "자동차 변속기는 기계의 속도나 토크를 제어하여 엔진의 동력을 전달하는 장치예요. 자동 변속기가 있는 차는 속도에 따라 기어가 자동으로 변속이 돼요.",
    "제로백" to "차량의 가속능력을 보여주는 척도로, 시속 0km 에서 100km 까지 도달하기까지 걸리는 시간을 의미해요.",
    "서스펜션(전)" to "자동차의 서스펜션은 차량과 노면이 부딪힐 때의 충격이 차량의 바디와 운전자, 승객에게 직접 전달되지 않도록 보호하는 역할을 해요.",
    "서스펜션(후)" to "자동차의 서스펜션은 차량과 노면이 부딪힐 때의 충격이 차량의 바디와 운전자, 승객에게 직접 전달되지 않도록 보호하는 역할을 해요.",
    "복합연비" to "1리터당 차량이 도심과 고속도로에서 일반적으로 주행가능한 거리를 의미해요.",
    "도심연비" to "1리터당 차량이 도심에서 주행가능한 거리를 의미해요.",
    "고속연비" to "1리터당 차량이 고속도로에서 주행가능한 거리를 의미해요.",
    "축간거리" to "앞바퀴의 중심에서 뒷바퀴의 중심까지의 거리를 뜻해요.",

)