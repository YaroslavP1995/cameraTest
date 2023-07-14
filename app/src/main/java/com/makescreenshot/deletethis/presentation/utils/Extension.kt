package com.makescreenshot.deletethis.presentation.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import okhttp3.Request

fun AppCompatActivity.findNavController(@IdRes id: Int): NavController {
    return (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
}

public fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Request.headersToStorage(): Map<String, String> {
    val headersMap = LinkedHashMap<String, String>()
    for (i in 0 until headers.size) {
        headersMap[headers.name(i)] = headers.value(i)
    }
    return headersMap
}

private fun Fragment.loadWithPlaceHolder(
    item: Bitmap,
    into: ImageView,
    placeholder: Int,
) {
    Glide.with(into.context)
        .load(item)
        .placeholder(placeholder)
        .into(into)
}