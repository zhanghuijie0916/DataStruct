package org.sunny.interview.WangYi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 牛牛以前在老师那里得到了一个正整数数对(x, y), 牛牛忘记他们具体是多少了。

 但是牛牛记得老师告诉过他x和y均不大于n, 并且x除以y的余数大于等于k。
 牛牛希望你能帮他计算一共有多少个可能的数对。


 输入描述:
 输入包括两个正整数n,k(1 <= n <= 10^5, 0 <= k <= n - 1)。


 输出描述:
 对于每个测试用例, 输出一个正整数表示可能的数对数量。

 输入例子1:
 5 2
 */
public class WY5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        long count = 0;
        /*for (int col=k;col<n+1;col++){
            count = count+n-col;
            for (int row=1;row<col;row++){
                if (col%row>=k){
                    count++;
                }
            }
        }*/

        for (int col=k;col<n+1;col++){
            count = count+n-col;

            if (col>=k+1){
                for (int row=col-k;row>=k+1;row--){
                    if (col%row>=k){
                        count++;
                    }
                }
            }

        }



        System.out.println(count);

    }
}
