package com.mmfsin.onethought.presentation.menu

sealed class MenuEvent {
    data object RoomCreated : MenuEvent()
    data object SWW : MenuEvent()
}