import util.CodeGenerator
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
//            println(receivedData)
//            println(Gson().fromJson<SubBlock>(receivedData, SubBlock::class.java))
            println(JsonTranslator.toCode(receivedData))
            val dataClass = JsonTranslator.toBlock(receivedData)
            CodeGenerator.write(fileName = dataClass.fileName , code = JsonTranslator.toCode(receivedData))

            printWriter.println("[From Server] Received data is {$receivedData}")
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
    }

    private fun close() {
        bufferedReader.close()
        printWriter.close()
        inputStream.close()
        socket.close()
    }
}