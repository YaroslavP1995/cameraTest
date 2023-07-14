package com.makescreenshot.deletethis.data.repository

interface TokenRepository {
    fun saveAuthToken(token: String)
    fun getToken(): String
    fun deleteToken()
    fun saveToken(token: String)
    fun getRefreshToken(): String
    fun saveRefreshToken(refreshToken: String)
    fun isTokenExists(): Boolean
}