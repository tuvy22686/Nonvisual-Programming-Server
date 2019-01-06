package util

import java.io.File
import java.io.FileWriter
import java.lang.Exception

object CodeGenerator {

    private lateinit var fileWriter: FileWriter

    private fun init(languageType: Int, filePath: String) {
        val extension = when (languageType) {
            0 -> { ".c" }
            1 -> { ".java" }
            else -> { "" }
        }
        fileWriter = FileWriter("$filePath$extension", false)
    }

    private fun writerClose() {
        fileWriter.close()
    }

    fun write(languageType: Int, fileName: String, code: String) {
        val currentPath = File(".").absoluteFile.parent
        try {
            init(languageType, "$currentPath/src/out/$fileName")
            fileWriter.write(code)
            writerClose()
        } catch (e: Exception) {
            e.printStackTrace()
            writerClose()
        }
    }
}