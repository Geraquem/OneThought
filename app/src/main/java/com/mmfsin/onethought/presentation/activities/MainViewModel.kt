package com.mmfsin.onethought.presentation.activities

import com.mmfsin.onethought.base.BaseViewModel
import com.mmfsin.onethought.domain.usecases.GetAdjectivesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAdjectivesUseCase: GetAdjectivesUseCase
) : BaseViewModel<MainEvent>() {

    fun getData() {
        executeUseCase(
            { getAdjectivesUseCase.execute() },
            { result -> _event.value = MainEvent.GetData },
            { _event.value = MainEvent.SWW }
        )
    }
}