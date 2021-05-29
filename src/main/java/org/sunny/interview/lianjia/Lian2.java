package org.sunny.interview.lianjia;

import org.sunny.graph.INode;

import java.util.*;

public class Lian2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int buckets = scan.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=1;i<=buckets;i++){
            arrayList.add(i);
        }

        LinkedList<Integer> result = new LinkedList<>();
        if (buckets>3){
            result.add(1);
            result.add(2);

            int temp = 3;
            if (temp<buckets){
                result.add(temp);
                int last = result.getLast();
                int secondlast = result.get(result.size()-2);
                temp = last+secondlast;
            }
        }
        else {
            System.out.println(0);
        }

        System.out.println(buckets-result.size());


    }

}
