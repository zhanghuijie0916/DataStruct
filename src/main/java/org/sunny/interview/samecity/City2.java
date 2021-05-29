package org.sunny.interview.samecity;

import java.util.Arrays;
import java.util.Scanner;

public class City2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        StringBuilder result = new StringBuilder();
        String temp = s;
        while (temp.length()>0){
            char[] charsArray = temp.toCharArray();
            Arrays.sort(charsArray);
            char bigchar = charsArray[charsArray.length-1];
            result.append(bigchar);
            int tempstart = temp.indexOf(bigchar);
            if (tempstart==temp.length()-1){
                break;
            }
            else
                temp = temp.substring(tempstart+1);
            //System.out.println(temp);
        }

        //System.out.println(Arrays.toString(charsArray));


        System.out.println(result);
    }
}
