package org.sunny.zijietiaodong;


import java.util.Scanner;
import java.util.TreeMap;

public class Zijie2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[][] array = new int[num][2];
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int xsum = 0;
        int ysum = 0;
        for (int i=0;i<num;i++){
            array[i][0] = scanner.nextInt();
            array[i][1] = scanner.nextInt();
            map.put(array[i][1],array[i][0]);
            xsum = xsum + array[i][0];
            ysum = ysum + array[i][1];
        }

        boolean flag = true;
        while (flag){
            if (ysum%2 == 1){
                int minkey = map.firstKey();
                int v = map.get(minkey);
                map.remove(minkey);
                System.out.println(minkey+"---"+v);
                int xtsum = xsum-v;
                int ytsum = ysum-minkey;
                if (xtsum%2==0){
                    flag = false;
                    System.out.println(ytsum);
                }
                else {
                    System.out.println(0);
                }

            }
        }







    }
}
