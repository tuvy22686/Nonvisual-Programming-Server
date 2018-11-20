package model

import util.ClassificationOfFunction
import java.lang.StringBuilder

data class SubBlock(val id: Int, val functionName: String, val arguments: List<String>?, val children: List<SubBlock>?): Block() {

    fun toCode(): String {
        // 関数名
        val fc = ClassificationOfFunction.idToString(id)

        // 引数
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

        // 中括弧中身連結
        cnt = 0
        val ch = StringBuilder()
        while (children?.getOrNull(cnt) != null) {
            if (cnt == 0) {
                ch.append("{\n")
            }
            ch.append("${children[cnt].toCode()}\n")
            cnt++
        }
        if (ch.isNotEmpty()) {
            ch.append("}\n")
        } else {
            ch.append(";")
        }

        return fc + arg.toString() + ch.toString()
    }
}