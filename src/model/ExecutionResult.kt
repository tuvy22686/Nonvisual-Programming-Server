package model

data class ExecutionResult(val standard: String?, val error: String?, val returnCode: Int?) {

    companion object {
        fun ofError(): ExecutionResult {
            return ExecutionResult("", "error", null)
        }
    }
}