package ThreadPool;

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
 *
 * 1、NIO 底层实现
 *      a、 网络模型： selector、poll、epoll;
 *      b、 Java 根据系统决定采用哪种模型; Windows 中 采用 selector 的模式;
 *
 * 2、Java 中 selector 的模式实现
 *      a、socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);  将 channel 向 selector 注册感兴趣的事件 ;
 *      b、selector 每次 select() 调用 native 的 doSelect()  WindowsSelectorImpl.c 的 Java_sun_nio_ch_WindowsSelectorImpl_00024SubSelector_poll0
 *          已调用 C 的 select() 方法 和 底层进行 交互 ;
 *          i、C 中的 select() 是 阻塞的；
 *          ii、因设置了超时时间; 如果在超时时间内没有获取到事件则进行返回；如果 获取到则 返回 获取到事件的数量和发生事件的 文件描述符的集合;
 *             可读文件描述符的集合、可写文件描述符的集合、异常文件描述符的集合;
 *          iii、根据 selectionKey 中 感兴趣的事件 和 文件描述符  与 select() 中返回的 [可读文件描述符的集合、可写文件描述符的集合、异常文件描述符的集合] 进行比对, 得到哪些
 *             selectionKey 的事件已经就绪; 放入 SelectorImpl的selectedKeys集合中;
 *      c、selector.selectedKeys().iterator(); 遍历 selectedKeys 依次处理 selectedKeys; isAcceptable()、isReadable()、isWritable()  selectedKeys 的发生的事件,
 *         并且进行对应的处理;
 *
 *
 *
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

        this.selector = Selector.open();;

        // 绑定channel的accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws Exception{
        new ServerDemo().go();
    }

    private void go() throws Exception{

        // block api
        while(selector.select()>0){

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // 新连接
                if(selectionKey.isAcceptable()){
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