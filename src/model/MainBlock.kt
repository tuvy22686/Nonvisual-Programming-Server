package model

data class MainBlock(val userId: Long, val fileName: String, val subBlocks: List<SubBlock>): Block() {

    fun toCode(): String {
        val lib = "#include <stdio.h>\n\n"
        val fc = "int main"
        val arg = "()"

        val ch = StringBuilder()
        var cnt = 0
        ch.append("{\n")
        while (subBlocks.getOrNull(cnt) != null) {
            ch.append("${subBlocks[cnt].toCode()}\n")
            cnt++
        }
        ch.append("return 0;\n}")

        return lib + fc + arg + ch.toString()
    }
}