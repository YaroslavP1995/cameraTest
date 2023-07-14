package com.makescreenshot.deletethis.data.network.header

interface HeaderStorage {
    fun getApiHeaders(existingHeaders: Map<String, String>): Map<String, String>
}