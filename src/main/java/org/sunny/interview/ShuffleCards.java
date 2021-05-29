package org.sunny.interview;

import java.util.*;

/*
1 3 1 1 2 3 4 5 6
 */
public class ShuffleCards {
    public static List<Integer> getShuffle(int datalength,int shuffernum,LinkedList<Integer> templist){
        LinkedList<Integer> headlist = null;
        LinkedList<Integer> taillist = null;
        for (int i=0;i<shuffernum;i++){
            headlist = new LinkedList<>(templist.subList(0,datalength));
            taillist = new LinkedList<>(templist.subList(datalength,2*datalength));

            /*System.out.println();
            headlist.forEach(x->System.out.print(x+" "));
            System.out.println();

            System.out.println();
            taillist.forEach(x->System.out.print(x+" "));
            System.out.println();
*/
            LinkedList<Integer> result = new LinkedList<>();
            for (int k=0;k<datalength;k++){
                result.add(taillist.removeLast());
                result.add(headlist.removeLast());
            }

            Collections.reverse(result);

            /*System.out.println();
            result.forEach(x->System.out.print(x+" "));
            System.out.println();
*/
            templist = new LinkedList<>(result);
        }

        return templist;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int dataNum = scan.nextInt();

        List<LinkedList<Integer>> alllist = new ArrayList<>();
        int[] dataLength = new int[dataNum];  //数据折半长度
        int[] shuffleNum = new int[dataNum];  //洗牌次数

        //package data
        for (int i=0;i<dataNum;i++){
            LinkedList<Integer> templist =new LinkedList<>();
            dataLength[i] = scan.nextInt();
            shuffleNum[i] = scan.nextInt();

            for (int k=0;k<dataLength[i]*2;k++){
                templist.add(scan.nextInt());
            }
            alllist.add(templist);
        }

       /* System.out.println();
        for (LinkedList<Integer> list : alllist){
            list.forEach(System.out::print);
        }
        System.out.println();
*/

        for (int j=0;j<dataNum;j++){
            List<Integer> result= getShuffle(dataLength[j],shuffleNum[j],alllist.get(j));
            if (j!=dataNum-1){
                result.forEach(x->System.out.print(x+" "));
            }
            else {
                for (int i=0;i<result.size()-1;i++){
                    System.out.print(result.get(i)+" ");
                }
                System.out.print(result.get(result.size()-1));
            }

        }
    }
}
