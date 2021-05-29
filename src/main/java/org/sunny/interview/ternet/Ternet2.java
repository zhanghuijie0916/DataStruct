package org.sunny.interview.ternet;

import java.util.Scanner;

public class Ternet2 {
    public static Long getEachResult(Integer num,Integer i){
        Long jie = 1L;
        Long kjie = 1L;
        for (int k=0;k<i;k++){
            jie = jie*(num-k);
        }

        for (int k=0;k<i;k++){
            kjie = kjie*(i-k);
        }
        //System.out.println("分母="+jie+"分子"+kjie);
        return jie/kjie;
    }

    /**
     * 5
     2 3 3 3
     * @param args
     */
    public static void main(String[] args){


        Scanner scan = new Scanner(System.in);
        Integer targetk = scan.nextInt();
        Integer aLen = scan.nextInt();
        Integer asum = scan.nextInt();
        Integer bLen = scan.nextInt();
        Integer bsum = scan.nextInt();
        Long result = 0L;
        Long mode = 1000_0000_007L;

        for (int i=0;i<=asum;i++){
            for (int j=0;j<=bsum;j++){

                Integer t = i*aLen+j*bLen;
                //System.out.println(i+"--"+aLen+"--"+j+"--"+bLen+"--sum="+t);
                if (t.equals(targetk)){
                    Long temp = (getEachResult(asum,i)%mode)*(getEachResult(bsum,j)%mode);
                    result += temp;
                }

            }
        }


        System.out.println(result);
    }
}
