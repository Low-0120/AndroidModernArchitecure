sealed class ErrorType : Throwable() {
    object NotLogin : ErrorType()
    data class Throwable(val error: ErrorType) : ErrorType()
}
