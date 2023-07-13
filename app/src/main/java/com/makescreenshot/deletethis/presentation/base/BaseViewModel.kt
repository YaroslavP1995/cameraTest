package com.makescreenshot.deletethis.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected val parentJob = Job()
    protected val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default


    open fun onViewLoaded() {
        //no op
    }
}