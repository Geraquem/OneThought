package com.mmfsin.onethought.presentation.menu

import com.mmfsin.onethought.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
) : BaseViewModel<MenuEvent>() {

    fun getData() {
//        executeUseCase(
//            { getAdjectivesUseCase.execute("") },
//            { result -> _event.value = MainEvent.GetData },
//            { _event.value = MainEvent.SWW }
//        )
    }
}