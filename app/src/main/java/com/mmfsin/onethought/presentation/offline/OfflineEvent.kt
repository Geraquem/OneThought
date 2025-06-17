package com.mmfsin.onethought.presentation.offline

import com.mmfsin.onethought.domain.models.Words

sealed class OfflineEvent {
    data class Data(val data: List<Words>) : OfflineEvent()
    data object SWW : OfflineEvent()
}