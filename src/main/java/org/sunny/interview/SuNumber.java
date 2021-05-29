package org.sunny.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 腾讯
 * 题目描述
 给定一个正整数，编写程序计算有多少对质数的和等于输入的这个正整数，并输出结果。输入值小于1000。
 如，输入为10, 程序应该输出结果为2。（共有两对质数的和为10,分别为(5,5),(3,7)）
 输入描述:
 输入包括一个整数n,(3 ≤ n < 1000)
 输出描述:
 输出对数
 示例1
 输入
 10
 输出
 2
 */
public class SuNumber {
    public static boolean isSu(int k){
        if (k==1){
            return true;
        }
        else {
            for (int j=1;j<=k/2;j++){
                if (k%(j+1) == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i=1;i<number;i++){
            if (isSu(i)){
                list.add(i);
            }
        }

        list.forEach(System.out::println);
        int count = 0;

        for (int j=1;j<=number/2;j++){
            int k = number-j;
            if (list.contains(k) && list.contains(j)){
                count++;
            }
        }
        System.out.println(count);
    }
}
