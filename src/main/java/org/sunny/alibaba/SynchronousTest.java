package org.sunny.alibaba;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

class Producer implements Runnable{
    private SynchronousQueue<Integer> queue;
    public Producer(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    public void run(){
        while (true){
            try {
                int p = new Random().nextInt(1000);
                queue.put(p);
                System.out.println("生产型号:"+p);
                Thread.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private SynchronousQueue<Integer> queue;
    public Consumer(SynchronousQueue<Integer> queue){
        this.queue = queue;
    }

    public void run(){
        while (true){
            try {
                int p = queue.take();
                System.out.println("消费型号:"+p);
                System.out.println("--------------");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class SynchronousTest {


    public static void main(String[] args){
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }

}
