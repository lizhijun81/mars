package socket.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    void accept() throws IOException {
        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(8081));


        System.out.println("接受客户端的connect start ....");

        Socket accept = serverSocket.accept();// accept 导致服务端阻塞

        System.out.println("接受到客户端的connect...");


        byte[] bytes = new byte[1024];

        System.out.println("读取客户端的写入start...");

        accept.getInputStream().read(bytes); // read 阻塞

        System.out.println("读取客户端的写入内容：" + new String(bytes) + "...");

        System.out.println("读取客户端的写入end...");
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.accept();
    }
}
