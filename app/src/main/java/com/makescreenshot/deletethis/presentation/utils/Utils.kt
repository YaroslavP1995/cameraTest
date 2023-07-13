package com.makescreenshot.deletethis.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

typealias LayoutInflate<T> = (LayoutInflater) -> T

fun NavController.navigate(enumRoute: Enum<*>, navOptions: NavOptions? = null) {
    navigate(enumRoute.name, navOptions)
}