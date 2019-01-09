package util

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

object JsonData {

    private lateinit var fileWriter: FileWriter
    private lateinit var fileReader: FileReader

    private fun initWriter(filePath: String) {
        fileWriter = FileWriter("$filePath.json")
    }

    private fun initReader(filePath: String) {
        fileReader = FileReader("$filePath.json")
    }

    private fun writerClose() {
        fileWriter.close()
    }

    private fun readerClose() {
        fileReader.close()
    }

    fun write(json: String, fileName: String) {
        val currentPath = File(".").absoluteFile.parent
        try {
            initWriter("$currentPath/src/out/$fileName")
            fileWriter.write(json)
            writerClose()
        } catch (e: Exception) {
            e.printStackTrace()
            writerClose()
        }
    }

    fun read(fileName: String): String? {
        val currentPath = File(".").absoluteFile.parent
        return try {
            initReader("$currentPath/src/out/$fileName")
            val data = fileReader.readLines()
            readerClose()
            data.first()
        } catch (e: Exception) {
            e.printStackTrace()
            readerClose()
            null
        }
    }
}