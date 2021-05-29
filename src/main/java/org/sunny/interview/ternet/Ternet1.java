package org.sunny.interview.ternet;

import java.util.ArrayList;
import java.util.Scanner;

public class Ternet1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Integer n = scan.nextInt();
        Integer m = scan.nextInt();
        int loop = n/(2*m);

        int posSum = 0;
        int negSum = 0;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int k=0;k<loop;k++){
            for (int j=1;j<=m;j++){
                list1.add(k*2*m+j);
                negSum += k*2*m+j;
            }
            for (int j=m+1;j<=2*m;j++){
                posSum += k*2*m+j;
                list2.add(k*2*m+j);
            }
        }

        System.out.println(posSum-negSum);
        list1.forEach(x->System.out.print(x+"--"));
        System.out.println();
        list2.forEach(x->System.out.print(x+"--"));
        System.out.println();
        System.out.println(posSum);
        System.out.println(0-negSum);


    }
}
