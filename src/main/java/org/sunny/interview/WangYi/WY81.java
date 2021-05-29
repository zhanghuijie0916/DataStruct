package org.sunny.interview.WangYi;

import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;

public class WY81 {
    private static int couter = 0;
    private static long volumns;

    private static void getiter(List<Integer> list,long sum){
        if (sum<=volumns){
            couter++;
            for (int i=0;i<list.size();i++){
                List<Integer> sublist = list.subList(i+1,list.size());
                getiter(sublist,sum+list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int snakenum = scanner.nextInt();
        volumns = scanner.nextInt();
        ArrayList<Integer> stack = new ArrayList<>();
        for (int j = 0; j < snakenum; j++) {
            stack.add(scanner.nextInt());
        }

        getiter(stack,0);
        System.out.println(couter);



    }
}
