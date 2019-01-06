package util

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

object ValueList {

    private lateinit var fileWriter: FileWriter
    private lateinit var fileReader: FileReader

    private fun initWriter(filePath: String) {
        fileWriter = FileWriter("$filePath.master", true)
    }

    private fun initReader(filePath: String) {
        fileReader = FileReader("$filePath.master")
    }

    private fun writerClose() {
        fileWriter.close()
    }

    private fun readerClose() {
        fileReader.close()
    }

    // すでに登録しているならfalseを返す
    fun register(valueName: String): Boolean {
        var registered = false
        val currentPath = File(".").absoluteFile.parent

        try {
            initReader("$currentPath/src/out/value")

            if (!this.search(fileReader.readLines(), valueName)) {
                initWriter("$currentPath/src/out/value")

                fileWriter.write("$valueName\n")
                writerClose()
                registered = true
            }
            readerClose()
        } catch (fileNotFoundException: FileNotFoundException) {
            // ファイルが見つからなかった場合、新規ファイルを作成して変数名を登録する
            initWriter("$currentPath/src/out/value")

            fileWriter.write("$valueName\n")
            writerClose()
            registered = true
        } catch (e: Exception) {
            e.printStackTrace()
            writerClose()
        }

        return registered
    }

    private fun search(list: List<String>, valueName: String): Boolean {
        var cnt = 0
        var found = false
        while (cnt < list.size) {
            if (list[cnt] == valueName) {
                found = true
                break
            }
            cnt ++
        }
        return found
    }
}