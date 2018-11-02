package com.zgf.thread.executor;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        new ThreadPoolExecutorTest().testExecutorService();
    }

    public void testExecutorService() {
        ThreadFactory threadFactory = new DefaultThreadFactory();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<Runnable>(1024);
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0, TimeUnit.MILLISECONDS, linkedBlockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());

        Runnable command = new Runnable() {
            @Override
            public void run() {
                System.out.println("currentThread name : " + Thread.currentThread().getName());
            }
        };
        pool.execute(command);
    }
}
