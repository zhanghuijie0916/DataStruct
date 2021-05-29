package org.sunny.interview;



import java.util.*;

/**
 *题目描述
 牛牛的作业薄上有一个长度为 n 的排列 A，这个排列包含了从1到n的n个数，
 但是因为一些原因，其中有一些位置（不超过 10 个）看不清了，但是牛牛记得
 这个数列顺序对的数量是 k，顺序对是指满足 i < j 且 A[i] < A[j] 的对数，
 请帮助牛牛计算出，符合这个要求的合法排列的数目。
 输入描述:
 每个输入包含一个测试用例。每个测试用例的第一行包含两个整数 n 和 k
 （1 <= n <= 100, 0 <= k <= 1000000000），接下来的 1 行，包含 n 个数字表示排列 A，
 其中等于0的项表示看不清的位置（不超过 10 个）。

 示例1
 输入
 5 5
 4 0 0 2 0
 输出
 2

 */
public class SequenceReduction {

    public static void main(String[] strs){
       Scanner scan = new Scanner(System.in);
       int num = scan.nextInt();
       int sortednum = scan.nextInt();
       int result = 0;

       int[] iniarray = new int[num];  //1,2,3,4,...n
       boolean[] flag = new boolean[num+1];

       for (int i=0;i<num;i++){
           int value = scan.nextInt();
           iniarray[i] = value;
           //如果读取的数字不为0，就标记为true
           if (value!=0){
               flag[value] = true;
           }
       }

       //填装缺失数字
        ArrayList<Integer> defectlist = new ArrayList<>();
       for (int i=1;i<num+1;i++){
           if (!flag[i]){
                defectlist.add(i);
           }
       }
       //得到缺失数字所有的排序
        ArrayList<ArrayList<Integer>> allsortedlist = new ArrayList<>();
        allSort(allsortedlist,defectlist,0);

        //计算已有的排序
        int inicount = 0;
        for (int i=0;i<iniarray.length;i++){
            if (iniarray[i]!=0){
                for (int j=i+1;j<iniarray.length;j++){
                    if (iniarray[j]!=0 && iniarray[i]<iniarray[j]){
                        inicount += 1;
                    }
                }
            }
        }

        /*
        计算加入模糊元素之后，有序对增加了多少，记住不再对原始不模糊的数据对进行比较
         */
        for (ArrayList<Integer> temp : allsortedlist){
            int count = inicount;
            count += calSortCount(temp, Arrays.copyOf(iniarray,num));

            if (count == sortednum){
                result++;
            }
        }


        System.out.println(result);
    }

    /**
     * 计算添加模糊元素之后有多少有序对
     * @param temp
     * @param array
     * @return
     */
    public static int calSortCount(ArrayList<Integer> temp,int[] array){
        int j = 0 ;
        int count = 0;
        for (int i=0;i<array.length;i++){
            if (array[i] == 0){
                array[i] = temp.get(j++);

                for (int k=0;k<i;k++){
                    if (array[k]!=0 && array[k]<array[i]){
                        count++;
                    }
                }

                for (int k=i+1;k<array.length;k++){
                    if (array[k]!=0 && array[i]<array[k]){
                        count++;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(array)+"---"+count);
        return count;

    }

    /**
     * 计算list从第n个元素开始的全排列，放在allsortedlist中
     * @param allsortedlist
     * @param list
     * @param n
     */
    public static void allSort(ArrayList<ArrayList<Integer>> allsortedlist,ArrayList<Integer> list,int n){
        if (n==list.size()){
            allsortedlist.add(new ArrayList<>(list));
        }

        else {
            for (int i=n;i<list.size();i++){
                Collections.swap(list,i,n);
                allSort(allsortedlist, list, n+1);
                Collections.swap(list,i,n);
            }
        }
    }

}
