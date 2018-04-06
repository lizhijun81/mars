package com.lizj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServerSocket {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true) {
            if(selector.select(3000) == 0) {// 阻塞获取通道就绪的数量
                System.out.println("select no channels");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();
                // 获取就绪的通道key:
                //      interest集合：注册事件集合
                //      ready集合：准备就绪通道的事件集合
                //      Channel：准备就绪通道
                //      Selector
                //      附加的对象(可选)
                iterator.remove();
                // selector.selectedKeys每次返回准备就绪的通道，防止准备就绪的通道重复使用，所以每次迭代需要删除
                // 表示对改准备就绪的channel已经处理
                if (selectionKey.isAcceptable()) {// 服务端接收客户端连接事件
                    System.out.println("isAcceptable");
                    ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();// 准备就绪的channel
                    SocketChannel channel = socketChannel.accept();// 与客户端建立的channel
                    channel.configureBlocking(false);
                    channel.register(selectionKey.selector(), SelectionKey.OP_READ);
                }

                if (selectionKey.isConnectable()) {// 客户端连接服务端事件
                    System.out.println("isConnectable");
                }

                if (selectionKey.isReadable()) {//可读
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(20);
                    buffer.flip();
                    int read = channel.read(buffer);
                    if (read <= 0) {
                        System.out.println("read end...");
                        channel.close();
                    } else {
                        String content = new String(buffer.array());
                        System.out.println("read content " + content);
                        selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    }
                }

                if (selectionKey.isWritable()) {//可写
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.write(ByteBuffer.wrap("hi".getBytes()));
                    selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                }
            }
        }

    }
}
