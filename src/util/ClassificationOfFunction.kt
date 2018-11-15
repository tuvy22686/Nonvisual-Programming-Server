package util

object ClassificationOfFunction {

    fun idToString(id: Int): String {
        return when (id) {
            0 -> { "main" }
            1 -> { "printf" }
            2 -> { "if" }
            3 -> { "while" }
            else -> { "irregular" }
        }
    }
}