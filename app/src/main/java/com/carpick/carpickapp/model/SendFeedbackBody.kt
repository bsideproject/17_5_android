package com.carpick.carpickapp.model

data class SendFeedbackBody(
    val is_good: Boolean,
    val content: String
)