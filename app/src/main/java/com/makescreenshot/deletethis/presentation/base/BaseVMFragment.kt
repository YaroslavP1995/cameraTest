package com.makescreenshot.deletethis.presentation.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding

abstract class BaseVMFragment<VB : ViewBinding, VM : BaseViewModel> : BaseBindingFragment<VB>() {

    protected abstract val viewModel: VM

    override fun onViewModelOnLoad() {
        viewModel.onViewLoaded()
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeViewModel()
    }

    open fun VM.observeViewModel() {
        //no op
    }


}