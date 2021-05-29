package org.sunny.interview.WangYi;

import java.util.Scanner;

/**
 * 输出牛牛最后面向的方向，N表示北，S表示南，E表示东，W表示西。
  10
  LRLLRRLRRR
 */
public class WY4 {
    public static void main(String[] args) {
        String[] earth = new String[]{"N","S","E","W"};
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        String path = scanner.next();

        int len;
        do {
            len = path.length();
            path = path.replaceAll("LR","").replaceAll("RL","");
        }
        while (path.length()!=len);
        //System.out.println(path);

        len = len%4; //0 1 2 3

        if (path.charAt(0)=='L'){
            switch (len){
                case 0:
                    System.out.println("N");
                    break;
                case 1:
                    System.out.println("W");
                    break;
                case 2:
                    System.out.println("S");
                    break;
                case 3:
                    System.out.println("E");
                    break;
            }
        }
        else {
            switch (len){
                case 0:
                    System.out.println("N");
                    break;
                case 1:
                    System.out.println("E");
                    break;
                case 2:
                    System.out.println("S");
                    break;
                case 3:
                    System.out.println("W");
                    break;
            }
        }

    }
}
