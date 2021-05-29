package org.sunny.alibaba;

import java.util.Scanner;

public class Alibaba3 {
    private static int screw(int x,int y){
        int oo = Math.max(Math.abs(x),Math.abs(y)); //当前值所在的圈数值
        int max = (oo*2+1)*(oo*2+1);

        if (y==-oo){
            return max+ x+y;  //上边
        }else if (x==-oo){
            return max+ 3*x-y; //左边
        }else if (y==oo){
            return max+(-x-5*y); //下边
        }else{
            return max + (-7*x+y);  //右边
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int xHalfLen = scanner.nextInt();
        int yHalfLen = scanner.nextInt();
        for (int y=-yHalfLen;y<=yHalfLen;y++){
            for (int x=-xHalfLen;x<=xHalfLen;x++){
                System.out.printf("%6d",screw(x,y));
            }
            System.out.println();
        }
    }
}
