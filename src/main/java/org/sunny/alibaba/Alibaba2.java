package org.sunny.alibaba;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/*

刚才我说了我要退款我要退款我要退款我都说了我要退款
阿里旺旺
刚才我说了我要退款我都说了我要退款
 */
public class Alibaba2 {
    public static void reverseNumber(StringBuilder builder,int num) {

        System.out.println("1是使用master合并的最终版本");
        if (num != 0) {
            builder.append(num % 10);
            reverseNumber(builder, num / 10);
        }
        System.out.println("2我是决绝冲突之后的版本-下master");
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();  //input number
        try {
            StringBuilder builder = new StringBuilder();
            reverseNumber(builder,number);
            int[] array = new int[builder.length()];
            for (int i=0;i<builder.length();i++){
                array[i] = 57-builder.charAt(i)+1;
            }
            System.out.println(Arrays.toString(array));
            System.out.println(Arrays.toString(array));

        }catch (Exception e){
            System.out.println(" 3master");
            e.printStackTrace();
        }

    }
}
