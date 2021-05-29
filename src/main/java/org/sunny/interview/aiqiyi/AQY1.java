package org.sunny.interview.aiqiyi;

import java.util.Arrays;
import java.util.Scanner;

public class AQY1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        int y = scan.nextInt();
        int z = scan.nextInt();

        int[] array = new int[]{x,y,z};
        Arrays.sort(array);
        //System.out.println(Arrays.toString(array));
        int count = 0;
        int lowdiff = array[2]-array[1];
        int highdiff = array[2]-array[0];
        count = count+lowdiff;
        if (lowdiff==0){
            if (lowdiff%2==1){
                count += lowdiff/2+1;
            }
            else {
                count += lowdiff/2;
            }

        }
        else if (highdiff-lowdiff>0){
            int againdiff = highdiff-lowdiff;
            if (againdiff%2==1){
                count = count+againdiff/2+1;
            }
            else {
                count = count + againdiff/2;
            }
        }

        System.out.println(count);
    }
}
