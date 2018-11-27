package util

import model.ExecResult
import model.GccResult
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object ExecuteShellScript {

    private lateinit var inputStream: InputStreamReader
    private lateinit var errorStream: InputStreamReader
    private lateinit var standardReader: BufferedReader
    private lateinit var errorReader: BufferedReader

    fun gcc(fileName: String): GccResult {
        return try {
            val filePath = File(".").absoluteFile.parent
            val command = arrayListOf(
                    "gcc",
                    "$filePath/src/out/$fileName.c",
                    "-o",
                    "$filePath/src/out/$fileName.exe",
                    "-Wall"
            )
            val process = ProcessBuilder(command).start()
            init(process)

            val standardBuilder = StringBuilder()
            val errorBuilder = StringBuilder()
            var cStandard: Int = standardReader.read()
            while (cStandard != -1) {
                standardBuilder.append(cStandard.toChar())
                cStandard = standardReader.read()
            }
            var cError: Int = errorReader.read()
            while (cError != -1) {
                errorBuilder.append(cError.toChar())
                cError = errorReader.read()
            }

            println("Compile")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            GccResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            GccResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            GccResult(standard = null,
                    error = null,
                    returnCode = null)
        }
    }

    fun exec(fileName: String): ExecResult {
        return try {
            val filePath = File(".").absoluteFile.parent
            val process = ProcessBuilder("sh", "$filePath/src/shell/exec.sh", filePath, fileName).start()
            init(process)

            val standardBuilder = StringBuilder()
            val errorBuilder = StringBuilder()
            var cStandard: Int = standardReader.read()
            while (cStandard != -1) {
                standardBuilder.append(cStandard.toChar())
                cStandard = standardReader.read()
            }
            var cError: Int = errorReader.read()
            while (cError != -1) {
                errorBuilder.append(cError.toChar())
                cError = errorReader.read()
            }

            println("Execute")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            ExecResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            ExecResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            ExecResult(standard = null,
                    error = null,
                    returnCode = null)
        }
    }

    private fun init(process: Process) {
        inputStream = InputStreamReader(process.inputStream, "UTF-8")
        errorStream = InputStreamReader(process.errorStream, "UTF-8")
        standardReader = BufferedReader(inputStream)
        errorReader = BufferedReader(errorStream)
    }

    private fun close() {
        inputStream.close()
        errorStream.close()
        standardReader.close()
        errorReader.close()
    }
}