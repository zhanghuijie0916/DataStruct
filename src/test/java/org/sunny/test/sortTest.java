package org.sunny.test;

import java.util.Arrays;

public class sortTest {

    public static <T extends Comparable<? super T>> T[] insertSort(T[] array){
        int len = array.length;

        for (int i=1;i<len;i++){
            T temp = array[i];
            int j;

            for (j=i;j>0&&temp.compareTo(array[j-1])>0;j--){
                array[j] = array[j-1];
            }
            array[j] = temp;
        }

        return array;
    }

    public static <T extends Comparable<? super T>> T[] shellSort(T[] array){
        int len = array.length;

        for (int trap=len/2;trap>0;trap/=2){


            for (int i=trap;i<len;i++){
                T temp = array[i];
                int j;
                for (j=i;j>=trap&&temp.compareTo(array[j-trap])>0;j-=trap){
                    array[j] = array[j-trap];
                }

                array[j] = temp;
            }


        }
        return array;
    }

    public static void main(String[] args){
        Integer[] array = new Integer[]{21,2,5,43,23,9,0,85,12};
        //Integer[] res = sortTest.insertSort(array);
        Integer[] res2 = sortTest.shellSort(array);
        System.out.println(Arrays.toString(res2));

    }
}
