package com.carpick.carpickapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carpick.carpickapp.model.TestModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CarPickDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishList(car : TestModel)

    @Delete
    suspend fun deleteWishList(car : TestModel)

    @Query("SELECT * FROM wish_list_car")
    fun selectWishList() : Flow<List<TestModel>>

    @Query("Delete FROM wish_list_car where id = :id")
    suspend fun deleteWishListById(id : Int)

}