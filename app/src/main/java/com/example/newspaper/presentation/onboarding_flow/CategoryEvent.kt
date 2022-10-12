package com.example.newspaper.presentation.onboarding_flow

import androidx.annotation.StringRes

sealed interface CategoryEvent {
    data class ShowToast(
        @StringRes val textResId: Int
    ): CategoryEvent

    object OpenMainActivity: CategoryEvent
}