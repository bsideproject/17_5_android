package com.carpick.carpickapp.model

import androidx.compose.ui.graphics.Color

data class CarDetailTestModel(
    val idx: Int,
    val name: String,
    val simpleInfo: String,
    val carImg: Int,
    val rank: Int,
    val price: Int,
    val hashTags: List<CarDetailHashTagTest>,
    val specs: List<CarDetailSpecTest>,
    val options: List<CarDetailOptionTest>
)

data class CarDetailHashTagTest(
    val content: String,
    val backgroundColor: Color,
    val tooltipContent: String
)

data class CarDetailSpecTest(
    val title: String,
    val value: String,
    val tooltipContent: String
)

data class CarDetailOptionTest(
    val title: String,
    val content: String,
    val tooltipContent: String
)