package com.mmfsin.onethought.base

abstract class BaseUseCase<T> {
    abstract suspend fun execute(): T
}