package com.lizj.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args){

        Socket socket = null;
        OutputStream outputStream = null;
        PrintStream writer = null;
        try {
            socket = new Socket("127.0.0.1", 8088);
//            for (int i = 0; i < 100; i++) {
            while (true ){
                outputStream = socket.getOutputStream();
                writer = new PrintStream(outputStream);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String msg = reader.readLine();
//                System.out.println(msg);
//                if ("stop".equals(msg)) {
//                    break;
//                }
//                outputStream.write("hello xiaoming aaaaaaaaaaaaaaaa\n".getBytes());
                writer.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
//                if (writer != null) {
//                    writer.close();
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
