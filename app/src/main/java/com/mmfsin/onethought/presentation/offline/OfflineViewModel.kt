package com.mmfsin.onethought.presentation.offline

import com.mmfsin.onethought.base.BaseViewModel
import com.mmfsin.onethought.domain.usecases.GetAdjectivesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfflineViewModel @Inject constructor(
    private val getAdjectivesUseCase: GetAdjectivesUseCase
) : BaseViewModel<OfflineEvent>() {

    fun getData() {
        executeUseCase(
            { getAdjectivesUseCase.execute() },
            { result -> _event.value = OfflineEvent.Data(result) },
            { _event.value = OfflineEvent.SWW }
        )
    }
}