package model

import util.ClassificationOfFunction
import java.lang.StringBuilder

data class Block(val id: Int, val functionName: String, val arguments: List<String>?, val children: List<Block>?) {

    enum class Function(val id: Int?) {
        Main(0),
        Print(1),
        If(2),
        While(3)
    }

    fun toCode(): String {
        // 関数名
//        println("id: $id")
        val fc = ClassificationOfFunction.idToString(id)
//        println("functionName: $head")

        // 引数
//        println("arguments: $arguments")
        var cnt = 0
        val arg = StringBuilder()
        arg.append("(")
        while (arguments?.getOrNull(cnt) != null) {
            if (cnt > 1) {
                arg.append(",")
            }
            arg.append(arguments[cnt])
            cnt++
        }
        arg.append(")")
//        println("concatenated argument: $arg")

        // 中括弧中身連結
//        println("children: $children")
        cnt = 0
        val ch = StringBuilder()
        while (children?.getOrNull(cnt) != null) {
            if (cnt == 0) {
                ch.append("{\n")
            }
            ch.append("\t${children[cnt].toCode()}\n")
            cnt++
        }
        if (ch.isNotEmpty()) {
            ch.append("}\n")
        } else {
            ch.append(";")
        }

//        println("block: $ch")

        return fc + arg.toString() + ch.toString()
    }
}