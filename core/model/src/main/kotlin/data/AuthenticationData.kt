package data

import java.io.Serializable

data class AuthenticationData constructor(
    val userId: Int,
    val token: String,
) : Serializable {
    val isGuest: Boolean
        get() = token == ""
}

interface TokenProvider {
    fun getToken(): String?
    fun setToken(token: String)
}

class DefaultTokenProvider : TokenProvider {
    private var token: String? = null

    override fun getToken(): String? {
        return token
    }

    override fun setToken(token: String) {
        this.token = token
    }
}


fun mockAuthenticationData() = AuthenticationData(123456, "")