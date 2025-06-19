package com.mmfsin.onethought.presentation.menu

import com.mmfsin.onethought.base.BaseViewModel
import com.mmfsin.onethought.domain.usecases.CreateRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val createRoomUseCase: CreateRoomUseCase
) : BaseViewModel<MenuEvent>() {

    fun createRoom(roomName: String) {
        executeUseCase(
            { createRoomUseCase.execute(roomName) },
            { result -> _event.value = MenuEvent.GetData },
            { _event.value = MenuEvent.SWW }
        )
    }
}