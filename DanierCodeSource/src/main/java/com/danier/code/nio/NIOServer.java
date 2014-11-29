package com.danier.code.nio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

/**
 * NIO 服务端
 * @author Danier
 * @date 2014-11-3
 */
public class NIOServer {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private ServerSocket serverSocket;

    private static int PORT;

    private static int BUFFER_SIZE;

    private ByteBuffer buf;

    public NIOServer(int port, int buffSize) {
        this.PORT = port;
        this.BUFFER_SIZE = buffSize;
        this.buf = ByteBuffer.allocate(buffSize);
    }

    /**
     * 服务端 开始监听
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void startListen() throws IOException, ClassNotFoundException {
        // 打开选择器
        selector = Selector.open();
        // 打开服务通道
        serverSocketChannel = ServerSocketChannel.open();
        // 设置服务通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 创建服务端socket
        serverSocket = serverSocketChannel.socket();
        // 服务端socket绑定端口
        serverSocket.bind(new InetSocketAddress(PORT));
        // 服务端通道建立注册链接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("端口注册完毕！");
        Iterator<SelectionKey> iterator = null;
        SelectionKey key = null;
        while (true) {// 监听
            int select = selector.select();
            System.out.println("select:" + select);
            // 选择选择键进行处理
            iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                key = iterator.next();
                this.handle(key);
                iterator.remove();
            }
        }
    }

    /**
     * 处理选择器
     * @param key
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("static-access")
    private void handle(SelectionKey key) throws IOException, ClassNotFoundException {
        if (key.isAcceptable()) {// 连接事件
            // 链接客户端socket通道
            SocketChannel socketChannel = this.serverSocketChannel.accept();
            // 设置socket通道为非阻塞
            socketChannel.configureBlocking(false);
            // 注册读事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("新客户端建立链接！");
        } else if (key.isReadable()) {// 读事件
            // 获取链接客户端socket通道
            SocketChannel channel = (SocketChannel) key.channel();
            // 清空缓冲区
            buf.clear();
            // 读数据到缓冲区 返回字节数
            int a = channel.read(buf);
            if (a > 0) {
                // 读取客户端消息
                buf.flip();// 切换读写模式
                byte[] b = new byte[buf.limit()];
                buf.get(b, buf.position(), buf.limit());
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                objectInputStream.close();
                byteArrayInputStream.close();
                System.out.println("服务端接收到客户端信息：" + dataPacket);
                System.out.println("客户端发送时间：" + dataPacket.getSendTime());
                // 回应客户端消息
                buf.flip();
                DataPacket packet = new DataPacket();
                packet.setContent("服务端已收到数据！");
                packet.setSendTime(new Date());
                packet.setId(1);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(packet);
                objectOutputStream.close();
                buf.clear();
                buf.put(outputStream.toByteArray());
                channel.write(buf);
            } else {
                channel.close();
            }
        } else if (key.isWritable()) {// 写事件
        }
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer(9999, 1024);
        try {
            server.startListen();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
