package util

object ClassificationOfFunction {

    fun idToCCode(id: Int): String {
        return when (id) {
            0 -> { "printf" }
            1 -> { "if" }
            2 -> { "while" }
            else -> { "irregular" }
        }
    }

    fun idToJavaCode(id: Int): String {
        return when (id) {
            0 -> { "System.out.println" }
            1 -> { "if" }
            2 -> { "while" }
            else -> { "irregular" }
        }
    }
}