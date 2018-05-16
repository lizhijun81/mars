package com.lizj.socket;

import com.google.common.collect.Lists;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class SocketClient {

    public static void main(String[] args) throws Exception {

        boolean b = Boolean.valueOf("1");
        System.out.println(b);


//        List<Socket>  sockets = Lists.newArrayList();
//        int a = 0;
//        while (true) {
//            OutputStream outputStream = null;
//
//            Socket socket = new Socket("10.16.208.69", 8082);
//            sockets.add(socket);
//            outputStream = socket.getOutputStream();
//            byte[] bytes = MessageUtil.hexToBytes("ab000b11e1aeb9a3b9005d");//ab000b11e1aeb9a3b9005d
//            outputStream.write(bytes);
//
////            InputStream inputStream = socket.getInputStream();
////            byte[] bytea = new byte[100000];
////            int read = inputStream.read(bytea);
////            for (int i = 0; i < read; i++) {
////                System.out.print(String.format("%02x", bytea[i]));
////            }
////            System.out.println("");
//
////            inputStream.close();
//            outputStream.close();
//            System.out.println(a++);
//        }
    }
}


//            ab000911e1aeb9a3bc5a
//                    ab001011e1aeb9a3b900000000000046
//
//            AB 00 09 11 E1 AE B9 A3 BC AB