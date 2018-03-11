package org.sunny.sorting;

import java.util.Arrays;

public class Sortings{

    /*
    冒泡排序
     */
    public static <T extends Comparable<? super T>>  T[] bubbleSort(T[] array){
        int len = array.length;
        boolean stopflag = true;

        for(int i=0;i<len&&stopflag;i++){
            stopflag = false;
            for (int j=len-1;j>0;j--){
                if(array[j].compareTo(array[j-1])<0){
                    T temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    stopflag = true;
                }
            }
        }
        return array;
    }

    /*
    简单排序
     */
    public static <T extends Comparable<? super T>> T[] simpleSort(T[] array){
        int len = array.length;
        for (int i=0;i<len;i++){
            int min = i;

            for (int j=i;j<len;j++){
                if (array[j].compareTo(array[min])<0){
                    min = j;
                }
            }
            if (min != i){
                T temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }

        return array;
    }

    /*
    插入排序
     */
    public static <T extends Comparable<? super T>> T[] insertSort(T[] array){
        int len = array.length;
        for (int i=1;i<len;i++){
            int j;
            T temp = array[i];

            for (j=i;j>0&&temp.compareTo(array[j-1])<0;j--){
                array[j] = array[j-1];
            }
            array[j] = temp;
        }
        return array;
    }

    /*
    希尔排序
     */
    public static <T extends Comparable<? super T>> T[] shellSort(T[] array){
        int len = array.length;
        int j;
        for (int gap=len/3+1;gap>1;gap=gap/3+1){
            for (int i=gap;i<len;i++){
                T temp = array[i];
                for(j=i;j>=gap&&temp.compareTo(array[j-gap])<0;j-=gap){
                    array[j] = array[j-gap];
                }

                array[j] = temp;
            }
        }

        return array;
    }



    public static void main(String[] args){
        Integer[] array = new Integer[]{3,5,6,2,32,12,5,9};
//        Integer[] bubble_result = Sortings.bubbleSort(array);
//        System.out.println(Arrays.toString(bubble_result));

//        Integer[] simple_result = Sortings.simpleSort(array);
//        System.out.println(Arrays.toString(simple_result));

//        Integer[] insert_result = Sortings.insertSort(array);
//        System.out.println(Arrays.toString(insert_result));

        Integer[] shell_result = Sortings.shellSort(array);
        System.out.println(Arrays.toString(shell_result));

    }
}
