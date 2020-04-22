package com.zgf.basic.nio;

import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable{
    private Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void run() {
        try {
            // 监听TCP链接请求
            SocketChannel socketChannel = reactor.serverSocketChannel.accept();
            // 调用Handler来处理channel
            if (socketChannel != null) {
                new SocketReadHandler(reactor.selector, socketChannel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
