package com.makescreenshot.deletethis.presentation.utils.comparator

open class IdEntity(open val ids: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as IdEntity
        if (ids != other.ids) return false
        return true
    }
    override fun hashCode(): Int {
        return ids.hashCode()
    }
}