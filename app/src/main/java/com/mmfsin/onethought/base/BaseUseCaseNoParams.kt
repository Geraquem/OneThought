package com.mmfsin.onethought.base

abstract class BaseUseCaseNoParams<T> {
    abstract suspend fun execute(): T
}