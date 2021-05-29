package org.sunny.interview.meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mei1 {
    public static int gcd(int a,int b){
        if(a<0 || b<0)
            return -1;
        if (a==b)
            return a;
        if((a&1)>(b&1))
            return gcd(a,b>>1);
        if((a&1)<(b&1))
            return gcd(a>>1,b);
        if((a&1)==0 && (b&1)==0)
            return gcd(a>>1,b>>1)<<1;
        return gcd(Math.abs(a-b),b>a?a:b);
    }

    public static void main(String[] args){
        //System.out.print(gcd(12,6));

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int[] array = new int[num];

        array[0] = p;
        for (int i=1;i<num;i++){
            array[i] = (array[i-1]+153)%p;
        }
        /*System.out.print(Arrays.toString(array));
        System.out.println();
*/
        int sum = 0;
        //ArrayList<Integer> index = new ArrayList<>();


        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                int w = gcd(i,j);
                sum += array[w-1];
            }
        }

        //System.out.println("-------index--------");
        //index.forEach(System.out::println);



        /*for (int each:index){
            sum += array[each-1];
        }*/
        //System.out.println("sum="+sum);
        System.out.println(sum);

    }
}
