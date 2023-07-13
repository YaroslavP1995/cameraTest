package com.makescreenshot.deletethis.presentation.utils.comparator

import androidx.recyclerview.widget.DiffUtil

class IntIdComparator<T : IdEntity> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.ids == newItem.ids
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}