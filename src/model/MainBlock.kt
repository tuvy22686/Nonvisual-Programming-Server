package model

data class MainBlock(val userId: Long, val languageType: Int, val fileName: String, val subBlocks: List<SubBlock>): Block() {

    fun toCCode(): String {
        val lib = "#include <stdio.h>\n\n"
        val fc = "int main"
        val arg = "()"

        val ch = StringBuilder()
        var cnt = 0
        ch.append("{\n")
        while (subBlocks.getOrNull(cnt) != null) {
            ch.append("${subBlocks[cnt].toCCode()}\n")
            cnt++
        }
        ch.append("return 0;\n}")

        return lib + fc + arg + ch.toString()
    }

    fun toJavaCode(className: String): String {
        val lib =
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n"
        val fc = "public class $className {\npublic static void main"
        val arg = "(String[] args)"

        val ch = StringBuilder()
        var cnt = 0
        ch.append("{\n")
        while (subBlocks.getOrNull(cnt) != null) {
            ch.append("${subBlocks[cnt].toJavaCode()}\n")
            cnt++
        }
        ch.append("}\n}")

        return lib + fc + arg + ch.toString()
    }
}