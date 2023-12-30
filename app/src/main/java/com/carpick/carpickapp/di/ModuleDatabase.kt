package com.carpick.carpickapp.di

import android.app.Application
import androidx.room.Room
import com.carpick.carpickapp.api.ApiService
import com.carpick.carpickapp.db.CarPickDao
import com.carpick.carpickapp.db.CarPickDatabase
import com.carpick.carpickapp.repository.WishListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleDatabase {
    @Singleton
    @Provides
    fun provideCarPickDatabase(app : Application) =
        Room.databaseBuilder(app, CarPickDatabase::class.java, "car_wishlist_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCarPickDao(carPickDB : CarPickDatabase) : CarPickDao {
        return carPickDB.carPickDao()
    }

    @Singleton
    @Provides
    fun provideWishListRepository(apiService: ApiService, carPickDao: CarPickDao) : WishListRepository {
        return WishListRepository(apiService, carPickDao)
    }
}