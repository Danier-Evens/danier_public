package com.danier.code.nio;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * NIO 客户端
 * @author Danier
 * @date 2014-11-3
 */
public class NIOClient {

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("localhost", 9999);
            SocketChannel client = SocketChannel.open(address);
            client.configureBlocking(false);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (true) {
                buf.clear();
                System.out.println("请输入发送包内容：");
                String a = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (StringUtils.isNotBlank(a)) {
                    System.out.println(a);
                    DataPacket packet = new DataPacket();
                    packet.setContent(a);
                    packet.setSendTime(new Date());
                    packet.setId(1);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(packet);
                    objectOutputStream.close();
                    buf.put(outputStream.toByteArray());
                    outputStream.close();
                    buf.flip();
                    client.write(buf);
                    System.out.println("客户端发送数据：" + packet);
                    buf.clear();
                    while (true) {
                        int i = client.read(buf);
                        if (i > 0) {
                            buf.flip();
                            byte[] b = new byte[buf.limit()];
                            buf.get(b, buf.position(), buf.limit());
                            System.out.println("服务端传来数据：" + new String(b));
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
