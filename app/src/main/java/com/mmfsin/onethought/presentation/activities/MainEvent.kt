package com.mmfsin.onethought.presentation.activities

sealed class MainEvent {
    data object GetData : MainEvent()
    data object SWW : MainEvent()
}