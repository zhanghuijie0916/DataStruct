package org.sunny.test;


import java.util.concurrent.*;

class Work implements Callable<String> {
    private CyclicBarrier barrier;

    public Work(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        System.out.println("before barrier:" + Thread.currentThread().getName());
        try {
            barrier.await();
            System.out.println("after barrier:" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName();
    }
}

public class CyclicBarrierTest {


    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ExecutorService pool =Executors.newCachedThreadPool();

        try {
            pool.submit(new Work(cyclicBarrier));
            pool.submit(new Work(cyclicBarrier));
            pool.submit(new Work(cyclicBarrier));
            pool.submit(new Work(cyclicBarrier));
            pool.submit(new Work(cyclicBarrier));
            pool.submit(new Work(cyclicBarrier));
        }finally {
            pool.shutdown();
        }



    }
}
