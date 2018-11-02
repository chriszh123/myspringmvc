package com.zgf.thread.executor;

import java.util.concurrent.*;


public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        new ThreadPoolExecutorTest().testExecutorService();
    }

    public void testExecutorService() {
        ThreadFactory threadFactory = new DefaultThreadFactory();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<Runnable>(200);
        ExecutorService pool = new ThreadPoolExecutor(5, 20,
                0, TimeUnit.MILLISECONDS, linkedBlockingQueue, threadFactory, new ThreadPoolExecutor.AbortPolicy());

        Runnable command = new Runnable() {
            @Override
            public void run() {
                System.out.println("currentThread name : " + Thread.currentThread().getName());
            }
        };
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i <= 10; i++) {
                pool.execute(command);
            }
            pool.shutdown();
            while (!pool.awaitTermination(1, TimeUnit.MILLISECONDS)) {
                System.out.println("线程还在执行。。。");
            }
            long end = System.currentTimeMillis();
            System.out.println(String.format("一共处理了%s", (end - start)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
