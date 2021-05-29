package org.sunny.interview.WangYi;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WY2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long lower = scanner.nextLong();
        long high = scanner.nextLong();

        int counter = 0;
        long i = 1 ;
        long sum = 0;

        while (i<lower){
            String str = i+"";

            char[] chars = str.toCharArray();
            char[] compare = new char[chars.length];
            Arrays.fill(compare,'0');
            for (int j=0;j<chars.length;j++){
                sum += chars[j]-compare[j];
            }
            i++;
        }

        for (long index=lower;index<=high;index++){

            char[] chars = (index+"").toCharArray();
            char[] compare = new char[chars.length];
            Arrays.fill(compare,'0');
            for (int j=0;j<chars.length;j++){
                sum += chars[j]-compare[j];
            }
            if (sum%3==0){
                counter++;
            }
        }

        System.out.println(counter);
    }
}
