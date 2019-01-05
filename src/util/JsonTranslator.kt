package util

import com.google.gson.Gson
import model.MainBlock
import model.OutputResult

object JsonTranslator {

    fun toCode(jsonData: String): String {
        val mainBlock = Gson().fromJson<MainBlock>(jsonData, MainBlock::class.java)

        return when (mainBlock.languageType) {
            0 -> { mainBlock.toCCode() }
            1 -> { mainBlock.toJavaCode(mainBlock.fileName) }
            else -> { "error" }
        }
    }

    fun toBlock(jsonData: String): MainBlock {
        return Gson().fromJson<MainBlock>(jsonData, MainBlock::class.java)
    }

    fun toJson(outputResult: OutputResult): String {
        return Gson().toJson(outputResult)
    }
}