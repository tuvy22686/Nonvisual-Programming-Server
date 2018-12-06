import infra.ConnectToClient
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
}