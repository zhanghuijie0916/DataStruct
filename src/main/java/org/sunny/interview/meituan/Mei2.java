package org.sunny.interview.meituan;


import java.util.Arrays;
import java.util.Scanner;

public class Mei2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();  //数据组数

        for (int i=0;i<T;i++){
            Integer number = scanner.nextInt();
            int bits = (number+"").length();
            long counter = 0;

            for (int j=1;j<bits;j++){
                counter = counter + j*9*(long) Math.pow(10,j-1);
                //System.out.println(j+"---"+j*9*(int) Math.pow(10,j-1));
            }

            char[] chars = new char[bits-1];
            Arrays.fill(chars,'0');
            String s = "1"+new String(chars);
            int v = Integer.valueOf(s);
            counter = counter + (number-v+1)*bits;

            //System.out.println("bits="+bits+"----last len"+(number-v+1)*bits);


            System.out.println(counter);
        }
    }
}
