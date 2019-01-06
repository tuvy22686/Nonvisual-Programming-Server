package util

import model.CompileResult
import model.ExecutionResult
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

object ExecuteShellScript {

    private lateinit var inputStream: InputStreamReader
    private lateinit var errorStream: InputStreamReader
    private lateinit var standardReader: BufferedReader
    private lateinit var errorReader: BufferedReader

    fun compile(languageType: Int, fileName: String): CompileResult {
        return when (languageType) {
            0 -> { gcc(fileName) }
            1 -> { javac(fileName) }
            else -> { CompileResult.ofError() }
        }
    }

    fun execute(languageType: Int, fileName: String): ExecutionResult {
        return when (languageType) {
            0 -> { exec(fileName) }
            1 -> { java(fileName) }
            else -> { ExecutionResult.ofError() }
        }
    }

    fun deleteFile() {
        val filePath = File(".").absoluteFile.parent
        val file = File("$filePath/src/out/value.master")
        file.delete()
    }

    private fun gcc(fileName: String): CompileResult {
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
            var gccStandard: Int = standardReader.read()
            while (gccStandard != -1) {
                standardBuilder.append(gccStandard.toChar())
                gccStandard = standardReader.read()
            }
            var gccError: Int = errorReader.read()
            while (gccError != -1) {
                errorBuilder.append(gccError.toChar())
                gccError = errorReader.read()
            }

            println("Compile")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            CompileResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            CompileResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            CompileResult(standard = null,
                    error = null,
                    returnCode = null)
        }
    }

    private fun exec(fileName: String): ExecutionResult {
        return try {
            val filePath = File(".").absoluteFile.parent
            val process = ProcessBuilder("sh", "$filePath/src/shell/exec.sh", filePath, fileName).start()
            init(process)

            val standardBuilder = StringBuilder()
            val errorBuilder = StringBuilder()
            var execStandard: Int = standardReader.read()
            while (execStandard != -1) {
                standardBuilder.append(execStandard.toChar())
                execStandard = standardReader.read()
            }
            var execError: Int = errorReader.read()
            while (execError != -1) {
                errorBuilder.append(execError.toChar())
                execError = errorReader.read()
            }

            println("Execute")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            ExecutionResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            ExecutionResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            ExecutionResult(standard = null,
                    error = null,
                    returnCode = null)
        }
    }

    private fun javac(fileName: String): CompileResult {
        return try {
            val filePath = File(".").absoluteFile.parent
            val command = arrayListOf(
                    "javac",
                    "-verbose",
                    "$filePath/src/out/$fileName.java"
            )
            val process = ProcessBuilder(command).start()
            init(process)

            val standardBuilder = StringBuilder()
            val errorBuilder = StringBuilder()
            var javacStandard: Int = standardReader.read()
            while (javacStandard != -1) {
                standardBuilder.append(javacStandard.toChar())
                javacStandard = standardReader.read()
            }
            var javacError: Int = errorReader.read()
            while (javacError != -1) {
                errorBuilder.append(javacError.toChar())
                javacError = errorReader.read()
            }

            println("Compile")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            CompileResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            CompileResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            CompileResult(standard = null,
                    error = null,
                    returnCode = null)
        }
    }

    private fun java(fileName: String): ExecutionResult {
        return try {
            val filePath = File(".").absoluteFile.parent
            val command = arrayListOf(
                    "java",
                    "-cp",
                    "$filePath/src/out/",
                    fileName
            )
            val process = ProcessBuilder(command).start()
            init(process)

            val standardBuilder = StringBuilder()
            val errorBuilder = StringBuilder()
            var javaStandard: Int = standardReader.read()
            while (javaStandard != -1) {
                standardBuilder.append(javaStandard.toChar())
                javaStandard = standardReader.read()
            }
            var javaError: Int = errorReader.read()
            while (javaError != -1) {
                errorBuilder.append(javaError.toChar())
                javaError = errorReader.read()
            }

            println("Execute")
            println("result standard:\n" + standardBuilder.toString())
            println("result error:\n" + errorBuilder.toString())
            println("Command return code: " + process.waitFor())
            println("-------------------------------")

            close()

            ExecutionResult(standard = standardBuilder.toString(),
                    error = errorBuilder.toString(),
                    returnCode = process.waitFor())
        } catch (e: IOException) {
            e.printStackTrace()
            close()
            ExecutionResult(standard = null,
                    error = null,
                    returnCode = null)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            close()
            ExecutionResult(standard = null,
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