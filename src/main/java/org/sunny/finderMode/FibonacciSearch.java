package org.sunny.finderMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FibonacciSearch {
    public static List<Integer> F;

    static {
        F = new ArrayList<>();
        for (int i=0;i<10;i++){
            F.add(getF(i));
        }
    }

    public static Integer getF(int n){
        if (n<2){
            return n==0?0:1;
        }
        else
            return getF(n-2)+getF(n-2);
    }

    public static int getIndndex(List<Integer> list,Integer len,Integer val){
        int low = 0;
        int high = len;
        int k = 0;
        while (len>F.get(k)){
            k++;
        }
        //将尾部数据填补空缺
        for (int i=len;i<k;i++){
            Integer lastEle = list.get(len-1);
            list.add(lastEle);
        }

        while (low<=high){
            int mid = low+F.get(k-1)-1;

            if (val > list.get(mid)){
                low = mid+1;
                k = k-2;
            }
            else if (val < list.get(mid)){
                high = mid-1;
                k = k-1;
            }
            else {
                if(mid<len){
                    return mid;
                }
                else
                    return len-1;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        Integer[] array = new Integer[]{0,1,16,24,35,47,59,62,73,88,99};
        List<Integer> f = FibonacciSearch.F;
        for (Integer i : f){
            System.out.println(i);
        }

        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,array);
        int index = FibonacciSearch.getIndndex(list,list.size(),99);
        System.out.println(index);
    }
}


