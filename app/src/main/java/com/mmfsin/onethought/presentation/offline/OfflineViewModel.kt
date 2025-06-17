package com.mmfsin.onethought.presentation.offline

import com.mmfsin.onethought.base.BaseViewModel
import com.mmfsin.onethought.domain.usecases.GetWordsDividedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfflineViewModel @Inject constructor(
    private val getWordsDividedUseCase: GetWordsDividedUseCase
) : BaseViewModel<OfflineEvent>() {

    fun getData() {
        executeUseCase(
            { getWordsDividedUseCase.execute() },
            { result -> _event.value = OfflineEvent.Data(result) },
            { _event.value = OfflineEvent.SWW }
        )
    }
}