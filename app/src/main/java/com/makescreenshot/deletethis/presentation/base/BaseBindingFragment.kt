package com.makescreenshot.deletethis.presentation.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.makescreenshot.deletethis.presentation.utils.Inflate
import com.makescreenshot.deletethis.presentation.utils.navigate

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment() {

    protected abstract val inflate: Inflate<VB>
    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        if (this::binding.isInitialized.not()) {
            binding = inflate.invoke(inflater, container, false)
            binding.initUI()
            onViewModelOnLoad()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("fragment/onCreateView", this::class.java.name)
    }

    open fun VB.initUI() { /* no op */
    }

    protected open fun onViewModelOnLoad() { /* no op */
    }

    protected fun navigate(route: Enum<*>, navOptions: NavOptions?) {
        findNavController().navigate(route, navOptions)
    }

    protected fun handleBackPressed() {
        if (findNavController().popBackStack().not()) {
            requireActivity().finish()
        }
    }

    protected fun finishActivity() {
        requireActivity().finish()
    }

    protected fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), permission
        ) == PackageManager.PERMISSION_GRANTED
    }

}