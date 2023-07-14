package com.makescreenshot.deletethis.data.network.header

class TokenHeaderStorage()
    : HeaderStorage {
    override fun getApiHeaders(existingHeaders: Map<String, String>): Map<String, String> {
        val headers = HashMap(existingHeaders)
            headers["Authorization"] = "Client-ID OKrOg948-bgMe0TYAaPos5Jbd61BwnQsZuhlcG0AnEg"
        return headers
    }
}