package org.sunny.alibaba;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.*;

/*
一共有num组客服，每组两个人，一次只需要一个人上班，但是有些人由于某些原因
不能同时上班，
客服编号：1，2，3，4，。。。
不能一起上班的编号：1--4  2--3  3--7
 */
public class Alibaba4 {

    public static ArrayList list = new ArrayList<>();

    public static ArrayList<Integer> het(ArrayList<Integer> arr){
        ArrayList<Integer> res = new ArrayList<>();
        if (arr.size()==1){
            return arr;
        }
        else {
            for (int i=0;i<2;i++){
                res.addAll(het((ArrayList<Integer>) arr.subList(2,arr.size())));
                list.addAll(res);
            }
        }
        return res;
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int groupnum = scanner.nextInt();
        int limitnum = scanner.nextInt();
        int[] people = new int[(int)Math.pow(2,groupnum)];

        int[][] limitArray = new int[limitnum][2];
        int[][] limitArrayT = new int[limitnum][2];

        //限制
        for (int i=0;i<limitnum;i++){
            String[] s = scanner.nextLine().split(",");
            limitArray[i][0] = Integer.parseInt(s[0]);
            limitArray[i][1] = Integer.parseInt(s[1]);

            limitArrayT[i][0] = Integer.parseInt(s[1]);
            limitArrayT[i][1] = Integer.parseInt(s[0]);
        }


        for (int i=0;i<Math.pow(2,groupnum/2);i++){


            for (int j=0;j<2;j++){


            }
        }
    }
}
