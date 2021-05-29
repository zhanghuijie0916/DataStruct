package org.sunny.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadThread extends Thread{
    private Lock sourthLock;
    private Lock northLock;

    public DeadThread(String name,Lock sourthLock,Lock northLock){
        this.setName(name);
        this.northLock = northLock;
        this.sourthLock = sourthLock;
    }

    public void run(){
        if (this.getName().equals("north")){
            try {
                northLock.lockInterruptibly();
                System.out.println("get north north lock");
                try {
                    sourthLock.lockInterruptibly();
                    System.out.println("get north sourth lock ");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        else {
            try {
                sourthLock.lockInterruptibly();
                System.out.println("get sourth sourth lock");
                try {
                    northLock.lockInterruptibly();
                    System.out.println("get sourth north lock ");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Lock sourthLock = new ReentrantLock();
        Lock northLock = new ReentrantLock();

        DeadThread t1 = new DeadThread("north",sourthLock,northLock);
        DeadThread t2 = new DeadThread("sourth",sourthLock,northLock);
        t1.start();
        t2.start();
    }
}
