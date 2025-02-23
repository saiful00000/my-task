package com.shaiful.mynote.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.shaiful.mynote.domain.repositories.HabitTrackerRepository
import com.shaiful.mynote.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailsViewModel @Inject constructor(
    repository: HabitTrackerRepository,
    dateUtils: DateUtils
) : ViewModel() {

}