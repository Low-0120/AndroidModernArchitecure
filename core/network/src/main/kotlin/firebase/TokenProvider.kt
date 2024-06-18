package com.example.core.network.firebase

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

interface TokenProvider {
    fun getToken(): String?
    fun setToken(token: String)
}

@Singleton
class DefaultTokenProvider @Inject constructor() : TokenProvider {
    @Volatile
    private var token: String? = null

    override fun getToken(): String? {
        Log.d("DefaultTokenProvider", "getToken called")
        return token
    }

    override fun setToken(token: String) {
        Log.d("DefaultTokenProvider", "setToken called")
        this.token = token
    }
}

