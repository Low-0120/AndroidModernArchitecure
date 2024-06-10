package retrofit


import data.UserData
import okhttp3.Call
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("user")
    suspend fun getUser(): retrofit2.Response<UserData>
}