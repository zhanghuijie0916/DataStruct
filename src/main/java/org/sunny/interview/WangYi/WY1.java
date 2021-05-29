package org.sunny.interview.WangYi;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.sunny.graph.INode;

import java.text.Collator;
import java.util.*;

/**
 * 输入描述:
 每个输入包含一个测试用例。
 每个测试用例的第一行包含两个正整数，分别表示工作的数量N(N<=100000)和小伙伴的数量M(M<=100000)。
 接下来的N行每行包含两个正整数，分别表示该项工作的难度Di(Di<=1000000000)和报酬Pi(Pi<=1000000000)。
 接下来的一行包含M个正整数，分别表示M个小伙伴的能力值Ai(Ai<=1000000000)。
 保证不存在两项工作的报酬相同。


 输出描述:
 对于每个小伙伴，在单独的一行输出一个正整数表示他能得到的最高报酬。一个工作可以被多个人选择。

 输入例子1:
 3 3
 1000000000 1001
 1 100
 10 1000
 9 10 1000000000

 输出例子1:
 100
 1000
 1001
 */
public class WY1 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(8);
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);




//        System.out.println(list1);
//        list1.removeIf(x->x>3);



        /*Scanner scanner = new Scanner(System.in);

        int jobNums = scanner.nextInt();
        int friendNums = scanner.nextInt();
        TreeMap<Integer,Integer> jobs = new TreeMap<>();  //（难度,报酬）

        //利用数组对元素进行排序，首先根据数组的第一个元素把数组排序，然后根据顺序把每一个数组
        //的第二个元素换成之前最大的值
        int[][] array = new int[jobNums][2];
        for (int i=0;i<jobNums;i++){
            array[i][0] = scanner.nextInt();
            array[i][1] = scanner.nextInt();
        }

        for (int[] a :array){
            System.out.println(Arrays.toString(a));
        }

        Arrays.sort(array,(o1,o2)->(o1[0]-o2[0]));
        for (int[] a :array){
            System.out.println(Arrays.toString(a));
        }
        for (int i=1;i<jobNums;i++) {
            array[i][1] = Math.max(array[i-1][1],array[i][1]);
        }


        for (int i=0;i<jobNums;i++){
                jobs.put(array[i][0],array[i][1]);
        }

        jobs.forEach((key,value)->System.out.println(key+":"+value));

        //个人能力
        for(int j=0;j<friendNums;j++){
            int friend = scanner.nextInt();
            Map.Entry<Integer,Integer> salary = jobs.floorEntry(friend);
            if (salary!=null){
                System.out.println(salary.getValue());
            }
            else
                System.out.println(0);

        }*/

    }
}
