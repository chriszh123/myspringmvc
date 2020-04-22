package com.zgf.basic.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
    public Selector selector;
    public ServerSocketChannel serverSocketChannel;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
        // 监听指定端口号
        serverSocketChannel.socket().bind(inetSocketAddress);
        // 不设置阻塞队列
        serverSocketChannel.configureBlocking(false);
        // 向selector注册该channel 返回selectionKey
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 利用selectionKey的attache功能绑定Acceptor 如果有事情，触发Acceptor
        selectionKey.attach(new Acceptor(this));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // selector 阻塞
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行
                while (iterator.hasNext()) {
                    // 来一个事件 第一次触发一个accepter线程
                    // 以后触发SocketReadHandler
                    SelectionKey selectionKey = iterator.next();
                    this.dispatch(selectionKey);
                    selectionKeys.clear();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey key) {
        Runnable runnable = (Runnable) key.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }
}
