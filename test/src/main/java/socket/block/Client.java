package socket.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    void connection() throws IOException {
        Socket socket = new Socket();

        System.out.println("客户端connect start...");
        socket.connect(new InetSocketAddress(8081));
        System.out.println("客户端connect end...");



        Scanner scanner = new Scanner(System.in);

        while (true) {
            String next = scanner.next();

            System.out.println("客户端写出start...");

            socket.getOutputStream().write(next.getBytes());

            System.out.println("客户端写出end...");
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connection();
    }
}
