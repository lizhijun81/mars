package com.lizj.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSocketServer {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args){
        FileSocketServer server = new FileSocketServer();
        server.start();
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            while (true) {
                Socket socket = serverSocket.accept();

                executorService.submit(new SimpleHandler(socket));

//                new Thread(new SimpleHandler(socket)).start();
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

//    public String handle(Socket socket) {
//        StringBuilder builder = new StringBuilder();
//        try {
//            InputStream inputStream = socket.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
////            byte[] bytes = new byte[10000000];
//            while (true) {
////                int length = inputStream.read(bytes);
////                if (length < 0){
////                    break;
////                }
////                System.out.println(new String(bytes, 0, length).toString());
//                String line = reader.readLine();
//                if( line == null) {
//                    break;
//                }
////                builder.append(line);
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return builder.toString();
//    }
}
