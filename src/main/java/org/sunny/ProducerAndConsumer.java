package org.sunny;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Depot{
    private long capacity;
    private static final long MAX_AMOUNT = 400;
    private Lock lock;
    private Condition comsumeCondition;
    private Condition produceCondition;

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public Depot(long capacity) {
        this.capacity = capacity;
        lock = new ReentrantLock();
        comsumeCondition = lock.newCondition();
        produceCondition = lock.newCondition();
    }

    public void comsume(long amount){
        while (lock.tryLock()){
            try {
                if (this.capacity-amount<0){
                    comsumeCondition.await();
                }
                else {
                    this.capacity -= amount;
                    System.out.println("消费线："+amount+"--"+Thread.currentThread().getName()+" product capacity="+this.capacity);
                    produceCondition.signalAll();
                    //comsumeCondition.signalAll();
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

    public void produce(long amount){
        while (lock.tryLock()){
            try {
                if (amount+capacity>MAX_AMOUNT){
                    produceCondition.await();
                }
                else {
                    this.capacity += amount;
                    System.out.println("生产线："+amount+"--"+Thread.currentThread().getName()+" product capacity="+this.capacity);
                    comsumeCondition.signalAll();
                    //produceCondition.signalAll();
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    @Override
    public String toString() {
        return "Depot{" +
                "capacity=" + capacity +
                '}';
    }
}

class Producer implements Runnable {
    private Depot depot;
    private long prodeceAmount;

    public Producer(Depot depot, long needAmount) {
        this.depot = depot;
        this.prodeceAmount = needAmount;
    }

    @Override
    public void run() {
        depot.produce(this.prodeceAmount);
    }
}

class Cosumer implements Runnable{
    private Depot depot;
    private long needAmount;

    public Cosumer(Depot depot, long needAmount) {
        this.depot = depot;
        this.needAmount = needAmount;
    }


    @Override
    public void run() {
        depot.comsume(this.needAmount);
    }


}

public class ProducerAndConsumer{
    public static void main(String[] args){
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        try {

            Depot depot = new Depot(0);
            threadPool.submit(new Producer(depot,100));
            threadPool.submit(new Cosumer(depot,250));
            threadPool.submit(new Producer(depot,150));
            threadPool.submit(new Producer(depot,200));
            threadPool.submit(new Cosumer(depot,200));
        }finally {
            threadPool.shutdown();
        }

    }


}