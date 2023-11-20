package com.carpick.carpickapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carpick.carpickapp.model.TestModel

@Database(entities = [TestModel::class], version = 1)
abstract class CarPickDatabase : RoomDatabase() {
    abstract fun carPickDao() : CarPickDao
}