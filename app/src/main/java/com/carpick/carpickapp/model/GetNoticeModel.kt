package com.carpick.carpickapp.model

import com.google.gson.annotations.SerializedName

data class GetNoticeModel(
    val code: Int,
    val message: String,
    val data: Data
)

data class Data(
    val notice: List<Notice>
)
data class Notice(
    @SerializedName("is_visible")
    val isVisible: Boolean,
    @SerializedName("notice_name")
    val noticeName: String,
    @SerializedName("notice_content")
    val noticeContent: String,
    @SerializedName("notice_image")
    val noticeImage: String,
    @SerializedName("notice_link")
    val noticeLink: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
