package com.carpick.carpickapp.model

abstract class BaseResponse<T> {
    abstract fun mapper(): T
}