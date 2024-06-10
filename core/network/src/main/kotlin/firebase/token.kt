package firebase

import data.TokenProvider
import javax.inject.Inject

class TokenSet @Inject constructor(
    private val tokenProvider: TokenProvider
) {
    fun authenticateUser() {
        // Authenticate the user and retrieve the token
        val token = "new_token_from_authentication"
        tokenProvider.setToken(token)
    }
}