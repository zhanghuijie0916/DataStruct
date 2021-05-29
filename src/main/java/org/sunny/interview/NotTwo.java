package org.sunny.interview;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class NotTwo {
    public static void main(String[] strs){
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();

        int[] xarray = new int[m*n];
        int[] yarray = new int[m*n];
        int[][] tar = new int[m][n];
        Deque<Integer> xdeque = new LinkedList<>();
        Deque<Integer> ydeque = new LinkedList<>();

        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){

                xarray[i*n+j] = i;
                yarray[m*i+j] = j;
                //System.out.println((i*n+j)+","+((m)*i+j)+":"+i+"==="+j);
            }
        }
        /*for (int[] a : tar){
            System.out.println(Arrays.toString(a));
        }*/

        System.out.println(Arrays.toString(xarray));
        System.out.println(Arrays.toString(yarray));


        xdeque.add(0);
        ydeque.add(0);

        while (!xdeque.isEmpty()){
            int x = xdeque.remove();
            int y = ydeque.remove();
            //System.out.println("error:"+x+","+y);

            //加进两个点
            if (x+1<m && tar[x+1][y] ==0){
                tar[x+1][y] = tar[x][y]+1;
                xdeque.add(x+1);
                ydeque.add(y);
            }
            if (y+1<n && tar[x][y+1] ==0){
                tar[x][y+1] = tar[x][y]+1;
                xdeque.add(x);
                ydeque.add(y+1);
            }

                if (x+2<m){
                    tar[x+2][y] = -1;

                }
                else if(x-2>=0){
                    tar[x-2][y] = -1;
                }

                else if(y+2<n){
                    tar[x][y+2]= -1;
                }
                else if(y-2>=0){
                    tar[x][y-2]=-1;

                }


        }

        int count = 0;
        for (int[] a :tar){
            count += Arrays.stream(a).filter(x->x!=-1).count();
        }

        for (int[] a : tar){
            System.out.println(Arrays.toString(a));
        }
        System.out.println(count);


    }
}
