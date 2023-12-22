package com.carpick.carpickapp.screen.TestResult

import androidx.compose.ui.graphics.Color
import com.carpick.carpickapp.R
import com.carpick.carpickapp.model.CarDetailHashTagTest
import com.carpick.carpickapp.model.CarDetailOptionTest
import com.carpick.carpickapp.model.CarDetailSpecTest
import com.carpick.carpickapp.model.CarDetailTestModel
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