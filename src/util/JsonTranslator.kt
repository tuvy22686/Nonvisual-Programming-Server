package util

import com.google.gson.Gson
import model.MainBlock

object JsonTranslator {

    fun toCode(jsonData: String): String {
        val mainBlock = Gson().fromJson<MainBlock>(jsonData, MainBlock::class.java)
        return mainBlock.toCode()
    }

    fun toBlock(jsonData: String): MainBlock {
        return Gson().fromJson<MainBlock>(jsonData, MainBlock::class.java)
    }
}