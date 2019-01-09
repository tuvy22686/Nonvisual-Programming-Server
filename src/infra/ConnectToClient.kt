package infra

import model.OutputResult
import util.CodeGenerator
import util.ExecuteShellScript
import util.JsonData
import util.JsonTranslator
import java.io.*
import java.net.Socket

class ConnectToClient(private val socket: Socket, private val id: Int): Thread() {

    private lateinit var inputStream: InputStream
    private lateinit var bufferedReader: BufferedReader
    private lateinit var printWriter: PrintWriter

    override fun run() {
        try {
            inputStream = socket.getInputStream()
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            printWriter = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            while (inputStream.available() == 0) {}

            val receivedData = bufferedReader.readLine()
            val dataClass = JsonTranslator.toBlock(receivedData)

            // Jsonファイル保存
            JsonData.write(receivedData, dataClass.fileName)

            CodeGenerator.write(languageType = dataClass.languageType, fileName = dataClass.fileName , code = JsonTranslator.toCode(receivedData))

            val outputResult = OutputResult(
                    JsonData.read(dataClass.fileName),
                    ExecuteShellScript.compile(languageType = dataClass.languageType, fileName = dataClass.fileName),
                    ExecuteShellScript.execute(languageType = dataClass.languageType, fileName = dataClass.fileName)
            )

            printWriter.println(JsonTranslator.toJson(outputResult))
            printWriter.flush()
        } catch (e: Exception) {
            e.printStackTrace()

            try {
                close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        println("Bye ID: $id")
        ExecuteShellScript.deleteFile()
    }

    private fun close() {
        bufferedReader.close()
        printWriter.close()
        inputStream.close()
        socket.close()
    }
}