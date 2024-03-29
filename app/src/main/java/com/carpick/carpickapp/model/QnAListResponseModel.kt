package com.carpick.carpickapp.model

import com.google.gson.annotations.SerializedName

data class QnAListResponseModel(
    @SerializedName("question_name")
    val questionName: String,
    val choices: List<Choice>
)
data class Choice(
    @SerializedName("choice_code")
    val choiceCode: String,
    val content: String
)