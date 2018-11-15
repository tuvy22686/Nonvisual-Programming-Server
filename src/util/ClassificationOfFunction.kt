package util

object ClassificationOfFunction {

    fun idToString(id: Int): String {
        return when (id) {
            0 -> { "printf" }
            1 -> { "if" }
            2 -> { "while" }
            else -> { "irregular" }
        }
    }
}