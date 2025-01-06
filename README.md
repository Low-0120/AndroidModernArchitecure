# 外部ライブラリの設定
## libs.versions.tomlにて
### ライブラリバージョン指定
```markdown
[versions]
androidGradlePlugin = "8.4.0"
```
### ライブラリ指定
```markdown
[libraries]
android-gradlePlugin = { group = "com.android.tools.build", name = "gradle", version.ref = "androidGradlePlugin" }
```
### プロジェクト間依存の統合管理
コンポジットビルドにてプラグイン作成

### 依存関係グラフ
プロジェクト間依存の統合管理をコンポジットビルドにて実現
必要なモジュールにプラグイン

依存関係グラフ
![dep_grah Image](docs/images/modernarchitercture%20dep_grah.png)

# プロジェクト設定
## App
Androidアプリケーションの全ての基盤になるレイヤー
```markdown
class MainActivity : ComponentActivity() {
```
### Auth
Firebase匿名ログインをアプリ起動時に実現/Activity起動時Token差し替え
```markdown
 @Provides
    @Singleton
    fun provideFirebaseAuth(tokenProvider: TokenProvider): FirebaseAuth {
        val auth = FirebaseAuth.getInstance()
        runBlocking {
            val tokenResult = checkAuthType(auth)
            tokenResult.onSuccess { token ->
                tokenProvider.setToken(token)
                Log.d("o", "Firebase TokenLogging Success")
            }.onFailure {
                val anonymousToken = signInAnonymouslyAndGetToken(auth)
                tokenProvider.setToken(anonymousToken)
                Log.d("AppModule", "Firebase AnonymousTokenLogging Success")
            }
        }
        return auth
    }
```
### Singlton
内部依存関係をDaggerHilt2にて外部コンテナに定義/各モジュール間において一元データアクセスが可能
```markdown
@HiltAndroidApp
class Application:Application() {
}
```

# Core
プロジェクトシステム設定/プロダクト設定

## common
プロジェクトシステムにおいて、普遍的な設定値
```markdown
enum class Dispatchers {
    Default,
    IO,
}
```
## model
プロダクトにおける設定値
```markdown
data class User(val id: String, val name: String, val email: String)
```
## data
プロダクト設定におけるデータ管理
```markdown
class UserRepository(private val apiService: ApiService) {
    fun getUser(userId: String): Flow<User> {
        return flow { emit(apiService.getUser(userId)) }
    }
}
```
## database
プロダクト設定におけるローラルデータベース管理
```markdown
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
```
## datastore
プロダクト設定におけるパッケージに内包するデータ管理
```markdown
class SettingsDataStore(context: Context) {
    private val dataStore = context.createDataStore(name = "settings")

    val exampleSetting: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.EXAMPLE_KEY] ?: "default"
        }

    companion object PreferencesKeys {
        val EXAMPLE_KEY = preferencesKey<String>("example_key")
    }
}
```

## designsystem
プロダクト設定におけるローカルリソース管理
```markdown
object DesignSystem {
    val Colors = lightColors(
        primary = Color.Blue,
        primaryVariant = Color.Blue,
        secondary = Color.Green
    )

    val Typography = Typography(defaultFontFamily = FontFamily.SansSerif)
}
```
## domain
プロダクトにおけるビジネスロジックの定義
```markdown
class UserInteractor(private val repository: UserRepository) {
    fun getUserProfile(userId: String): Flow<UserProfile> {
        return repository.getUser(userId).map { user ->
            UserProfile(user.name, user.email)
        }
    }
}
```
## network
外部通信設定
```markdown
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
```
## ui
プロダクトにおける共通UIの定義
```markdown
@Composable
fun CustomButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Click Me")
    }
}
```
## testing
テストに関連する設定/定義

# Features
ユーザーインタラクションにまつわるレイヤー
プロダクトにおけるUIレイヤー


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
