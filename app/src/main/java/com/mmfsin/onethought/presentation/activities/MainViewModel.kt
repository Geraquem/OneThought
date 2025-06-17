package com.mmfsin.onethought.presentation.activities

import com.mmfsin.onethought.base.BaseViewModel
import com.mmfsin.onethought.domain.usecases.GetWordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWordsUseCase: GetWordsUseCase
) : BaseViewModel<MainEvent>() {

    fun getData() {
        executeUseCase(
            { getWordsUseCase.execute() },
            { result -> _event.value = MainEvent.GetData },
            { _event.value = MainEvent.SWW }
        )
    }
}