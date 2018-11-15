import util.Constant
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket


fun main(args: Array<String>) {
    val serverSocket = ServerSocket()
    serverSocket.reuseAddress = true
    serverSocket.bind(InetSocketAddress(Constant.Connection.PORT_NUMBER))
    println("running")

    var cnt = 0

    while (true) {
        try {
            val socket: Socket = serverSocket.accept()
            println("Welcome ID: $cnt")
            val connectToClient = ConnectToClient(socket = socket, id = cnt)
            connectToClient.start()
            cnt++
        } catch (e: Exception) {
            e.printStackTrace()
            break
        }
    }

//    ExecuteShellScript.gcc("hello")
//    ExecuteShellScript.exec("hello")

//    val test1 = SubBlock(id = 0, functionName = "printf", arguments = listOf("Hello World"), children = listOf(null))
//    val test2 = SubBlock(id = 1, functionName = "if", arguments = listOf("a == 0"), children = listOf(test1))
//
//    val mockJson = Gson().toJson(test2)
//
//    println(mockJson)
//
//    val test3 = Gson().fromJson<SubBlock>(mockJson, SubBlock::class.java)
//
//    println(test3)
}