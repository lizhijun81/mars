package socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * server 端
 *
 * @author xiezhengchao
 * @since 2018/4/7 14:32
 */
public class ServerDemo{

    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;

    public ServerDemo() throws IOException{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        System.out.println("listening on port 8080");

        this.selector = Selector.open();

        // 绑定channel的accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);// 注册 OP_ACCEPT
    }

    public static void main(String[] args) throws Exception{
        new ServerDemo().go();
    }

    private void go() throws Exception{

        // block api
        while(selector.select()>0) {//

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // 新连接
                if(selectionKey.isAcceptable()) {
                    System.out.println("isAcceptable");
                    ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();

                    // 新注册channel
                    SocketChannel socketChannel  = server.accept();
                    if(socketChannel==null){
                        continue;
                    }
                    socketChannel.configureBlocking(false);
                     // 注意！这里和阻塞io的区别非常大，在编码层面之前的等待输入已经变成了注册事件，这样我们就可以在等待的时候做别的事情，
                    // 比如监听更多的socket连接，也就是之前说了一个线程监听多个socket连接。这也是在编码的时候最直观的感受
                    socketChannel.register(selector, SelectionKey.OP_READ| SelectionKey.OP_WRITE);


                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    buffer.put("hi new channel".getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                }

                // 服务端关心的可读，意味着有数据从client传来了，根据不同的需要进行读取，然后返回

//                 读就绪
//                 (1) socket内核中，接收缓冲区的字节数大于等于低水位标记（数据数量一定时），此时可以无阻塞的读，并且返回值大于0。
//                 (2) socketTCP通信中，对端关闭连接，此时读，返回0.
//                 (3) 监听socket上有新的连接请求。
//                 (4) socket上有未处理的错误。
                if(selectionKey.isReadable()){
                    System.out.println("isReadable");
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();

                    String receiveData= Charset.forName("UTF-8").decode(readBuffer).toString();
                    System.out.println("receiveData:"+receiveData);

                    // 把读到的数据绑定到key中
                    selectionKey.attach("server message echo:"+receiveData);
                }

                // 实际上服务端不在意这个，这个写入应该是client端关心的，这只是个demo,顺便试一下selectionKey的attach方法

//                写就绪
//                （1） socket内核中，发送缓冲区的空闲位置大于等于低水位时，可以无阻塞的写，并且返回值大于0；
//                （2）socket的写操作被关闭，触发SIGPIPE信号。
//                （3）socket使用connect连接时。
//                （4）socket上有未读取的错误。
                if(selectionKey.isWritable()){
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();

                    String message = (String) selectionKey.attachment();
                    if(message==null){
                        continue;
                    }
                    selectionKey.attach(null);

                    writeBuffer.clear();
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    while(writeBuffer.hasRemaining()){
                        socketChannel.write(writeBuffer);
                    }
                }
            }
        }
    }

}
