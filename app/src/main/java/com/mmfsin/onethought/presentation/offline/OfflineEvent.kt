package com.mmfsin.onethought.presentation.offline

import com.mmfsin.onethought.domain.models.WordsDivided

sealed class OfflineEvent {
    data class Data(val data: WordsDivided) : OfflineEvent()
    data object SWW : OfflineEvent()
}