package util

import com.google.gson.Gson
import model.Block

object JsonToBlock {

    fun run(jsonData: String): String {
        val block = Gson().fromJson<Block>(jsonData, Block::class.java)
        return block.toCode()
    }
}