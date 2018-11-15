package util

import java.io.File
import java.io.FileWriter
import java.lang.Exception

object CodeGenerator {

    private lateinit var fileWriter: FileWriter

    private fun init(filePath: String) {
        fileWriter = FileWriter(filePath)
    }

    private fun close() {
        fileWriter.close()
    }

    private fun dependOnLibrary(code: String): String {
        return "#include <stdio.h>\n\n$code"
    }

    fun write(fileName: String, code: String) {
        val currentPath = File(".").absoluteFile.parent
        try {
            init("$currentPath/src/out/$fileName")
            fileWriter.write(dependOnLibrary(code))
            close()
        } catch (e: Exception) {
            e.printStackTrace()
            close()
        }
    }
}