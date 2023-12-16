package com.carpick.carpickapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "wish_list_car")
data class TestModel(
    @PrimaryKey
    val id : Int,
    val testData : String = "test"
) :Serializable