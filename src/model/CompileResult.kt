package model

data class CompileResult(val standard: String?, val error: String?, val returnCode: Int?) {

    companion object {
        fun ofError(): CompileResult {
            return CompileResult("", "error", null)
        }
    }
}