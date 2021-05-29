package org.sunny.interview.WangYi;

import java.util.Scanner;

public class WY8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int snakenum = scanner.nextInt();
        long volumns = scanner.nextInt();
        long[] snackvolarray = new long[snakenum];
        for (int j=0;j<snakenum;j++){
            snackvolarray[j] = scanner.nextInt();
        }

        int counter = 0;
        char[] chars = null;
        long sum ;
        for (int i=0;i<=Math.pow(2,snakenum)-1;i++){
            sum = 0;

            chars = Integer.toBinaryString(i).toCharArray();
            //System.out.println(Integer.toBinaryString(i));

            for (int k=0;k<chars.length;k++){
                if (sum>volumns){
                    break;
                }
                if (chars[k]=='1'){
                    //System.out.println(snakenum-1-k);
                    sum += snackvolarray[snakenum-chars.length+k];

                }
            }

            if (sum<=volumns){
               counter++;
            }
            //System.out.println("SUM="+sum+"----"+"volums="+volumns);
        }

        System.out.println(counter);

    }

}
