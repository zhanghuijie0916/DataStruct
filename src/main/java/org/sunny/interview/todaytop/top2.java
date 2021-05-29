package org.sunny.interview.todaytop;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 uuurrdddddl
 3
 6 6 4 2
 5 6 3 3
 5 6 4 2

 3
 5 6 3 3
 5 6 4 2





 */
public class top2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String path= scan.nextLine();
        char[] paths = path.toCharArray();
        //System.out.println(Arrays.toString(paths));

        int num = scan.nextInt();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i=0;i<num;i++){
            int rowlen = scan.nextInt();
            int collen = scan.nextInt();
            int startx = scan.nextInt();
            int starty = scan.nextInt();

            System.out.println(rowlen +"--"+collen+"--"+startx+"--"+starty);
            int steps = 0;

            if (startx>0 && startx<=collen && starty>0 && starty<=rowlen){
                for (char p : paths){
                    steps += 1;
                    switch (p){

                        case 'u':
                            starty += 1;
                            break;
                        case 'd':
                            starty += -1;
                            break;
                        case 'r':
                            startx += 1 ;
                            break;
                        case 'l':
                            startx += -1;
                            break;
                    }
                    System.out.println(p+"("+startx+","+starty+")");
                    if (startx>collen ||startx<1 || starty>rowlen || starty<1){
                        break;
                    }
                }

            }


            System.out.println(steps);

            //list.add(steps);
        }

        /*for (Integer i : list){
            System.out.println(i);
        }*/

    }
}
