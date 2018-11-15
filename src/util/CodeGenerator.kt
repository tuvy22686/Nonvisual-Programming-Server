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

    fun write(fileName: String, code: String) {
        val currentPath = File(".").absoluteFile.parent
        try {
            init("$currentPath/src/out/$fileName")
            fileWriter.write(code)
            close()
        } catch (e: Exception) {
            e.printStackTrace()
            close()
        }
    }
}