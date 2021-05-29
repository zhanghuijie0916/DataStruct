package org.sunny.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 水仙花
 */
public class NarcissisticNumber {
    public static void main(String[] args) {
        HashMap<Character,Integer> numberMap = new HashMap<>();
        ArrayList<Integer> result = null;

        char[] keys = new char[]{'0','1','2','3','4','5','6','7','8','9'};
        for (int i=0;i<10;i++){
            numberMap.put(keys[i],(int)Math.pow(i,3));
        }

        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()){
            result = new ArrayList<>();

            Integer head = scan.nextInt();
            Integer tail = scan.nextInt();

            for (Integer number=head;number<=tail;number++){
                String s = String.valueOf(number);
                int sum = 0;
                char[] chararray = s.toCharArray();
                for (char c : chararray){
                    //System.out.println(c);
                    sum = sum + numberMap.get(c);
                }
                if (sum == number){
                    result.add(number);
                }
            }

            if (result.isEmpty()){
                System.out.println("none");
            }
            else {
                for (int j=0;j<result.size();j++){
                    if (j!=result.size()-1){
                        System.out.print(result.get(j)+" ");
                    }
                    else
                        System.out.print(result.get(j)+"\n");

                }

            }



        }
    }
}
