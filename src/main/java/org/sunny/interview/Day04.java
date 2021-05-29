package org.sunny.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day04 {

    /*
    四个点，判断是否是四边形
     */
    public static boolean panduan(int[] xarray,int[] yarray){
        boolean result = true;

        for (int i=0;i<xarray.length;i++){
            int x = xarray[i];
            int y = yarray[i];
            int yindex = Arrays.binarySearch(yarray,x);
            int xindex = Arrays.binarySearch(xarray,y);
            if (xindex!=yindex){
                result = false;
               break;
            }

        }

        return result;
    }


    /*
    1 1 2 2 4 4 8 8 16 16......
    给出一正整数n，求有几种方案
     */
    public static void money(int n){
        Integer N = ((int) Math.floor(Math.log(n)/Math.log(2))+1)*2;
        System.out.println("钱集合的数目："+N);

        //将1,1,2,2,4,4,8,8装进去
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<N/2;i++){
            list.add((int)Math.pow(2,i));
            list.add((int)Math.pow(2,i));
        }
        list.forEach(System.out::print);
        System.out.println("\n");

        //存放匹配的结果
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> templist = null;  //用于临时存放叠加的元素
        //使用二进制进行便利
        for (Integer i=0b0;i<Math.pow(2,N);i=i+0b1){

            int sum = 0;
            String str = Integer.toBinaryString(i);
            char[] chars = str.toCharArray();
            templist = new ArrayList<>();
            for (int k=0;k<chars.length;k++){
                if (chars[k] != '0'){
                    int ele = list.get(list.size()-chars.length+k);  //准备叠加的钱数
                    templist.add(ele);
                    sum += ele;

                }
            }
            System.out.println("i="+i+"----sum="+sum);
            //钱数匹配，将此次钱数的对象存到map中。
            if (sum == n){
                if(!map.containsValue(templist)){
                    map.put(i,templist);
                }
            }

        }
        map.forEach((key,value)->System.out.println("key="+key+"---value:"+Arrays.toString(value.toArray())));
        System.out.println("count="+map.size());
    }

    public static void main(String[] args){

        money(3);


        /*Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
        int time = 0;

        while (time<count){
            int[] xarray = new int[4];
            int[] yarray = new int[4];
            for (int i=0;i<4;i++) {
                xarray[i] = scan.nextInt();
            }

            for (int i=0;i<4;i++) {
                yarray[i] = scan.nextInt();
            }

            time++;
            boolean res = panduan(xarray,yarray);
            System.out.println(res);
        }


*/

    }
}
