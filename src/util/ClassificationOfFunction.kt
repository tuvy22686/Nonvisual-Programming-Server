package util

import java.lang.StringBuilder

object ClassificationOfFunction {

    fun idToCCode(id: Int, functionName: String): String {
        return when (id) {
            0 -> { "printf" }
            1 -> { "if" }
            2 -> { "while" }
            else -> { functionName }
        }
    }

    fun idToJavaCode(id: Int, functionName: String): String {
        return when (id) {
            0 -> { "System.out.println" }
            1 -> { "if" }
            2 -> { "while" }
            else -> { functionName }
        }
    }

    fun idToTypeCode(id: Int, values: List<String>): String {
        return when (id) {
            3 -> { values.first() }
            4 -> { "\"${values.first()}\"" }
            5 -> {
                val stringBuilder = StringBuilder()
                stringBuilder.append(values.first())
                var cnt = 1
                while (cnt < values.size) {
                    stringBuilder.append(",${values[cnt]}")
                    cnt++
                }
                "{$stringBuilder}"
            }
            6 -> { "new int[${values.first()}]" }
            else -> { values.first() }
        }
    }
}