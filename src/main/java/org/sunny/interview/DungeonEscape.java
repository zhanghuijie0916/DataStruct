package org.sunny.interview;



import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 每个输入包含 1 个测试用例。每个测试用例的第一行包含两个整数 n 和 m（1 <= n, m <= 50），表示地牢的长和宽。
 * 接下来的 n 行，每行 m 个字符，描述地牢，地牢将至少包含两个 '.'。接下来的一行，包含两个整数 x0, y0，
 * 表示牛牛的出发位置（0 <= x0 < n, 0 <= y0 < m，左上角的坐标为 （0, 0），出发位置一定是 '.'）。
 * 之后的一行包含一个整数 k（0 < k <= 50）表示牛牛合法的步长数，接下来的 k 行，每行两个整数 dx, dy
 * 表示每次可选择移动的行和列步长（-50 <= dx, dy <= 50）
 输出描述:
 输出一行一个数字表示最坏情况下需要多少次移动可以离开地牢，如果永远无法离开，输出 -1。以下测试用例中，
 牛牛可以上下左右移动，在所有可通行的位置.上，地牢出口如果被设置在右下角，牛牛想离开需要移动的次数最多，
 为3次。
 */
public class DungeonEscape {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        char[][] points = new char[row][col];
        for (int i=0;i<row;i++){
            points[i] = scanner.next().toCharArray();
        }
        int tar[][] = new int[row][col];  //用来记录步数下标
        Deque<Integer> xdeque = new LinkedList();
        Deque<Integer> ydeque = new LinkedList();

        /*//显示地图
        for (int i=0;i<row;i++){
            System.out.println(Arrays.toString(points[i]));
        }*/

        int x0 = scanner.nextInt();
        int y0 = scanner.nextInt();
        tar[x0][y0] = 1;  //初始化起点为1
        xdeque.add(x0);
        ydeque.add(y0);

        int k = scanner.nextInt();  //可以怎么走
        int[] dx = new int[k];
        int[] dy = new int[k];  //成对的x、y轴可走的步长
        for (int i=0;i<k;i++){
            dx[i] = scanner.nextInt();
            dy[i] = scanner.nextInt();
        }

        while (!xdeque.isEmpty()){
            int nowXpos = xdeque.remove();
            int nowYpos = ydeque.remove();

            for (int j=0;j<k;j++){
                if ((nowXpos+dx[j])>=0 && (nowXpos+dx[j])<row &&
                        (nowYpos+dy[j])>=0 && (nowYpos+dy[j])<col){
                    if (tar[nowXpos+dx[j]][nowYpos+dy[j]]==0){
                        if (points[nowXpos+dx[j]][nowYpos+dy[j]]=='.'){
                            tar[nowXpos+dx[j]][nowYpos+dy[j]] = tar[nowXpos][nowYpos]+1;
                            xdeque.add(nowXpos+dx[j]);
                            ydeque.add(nowYpos+dy[j]);
                        }
                        else
                            tar[nowXpos+dx[j]][nowYpos+dy[j]] = -1;
                    }

                }
            }
        }

        int max = 0;
        int hasRoad = 1;
        for (int i=0;i<row;i++){

            for (int j=0;j<col;j++){
                //存在永远都无法到达的.
                if (points[i][j]=='.' && tar[i][j]==0){
                    hasRoad = 0;
                }
                else{
                    max = Math.max(max,tar[i][j]);
                }
            }
        }


        if (hasRoad == 0){
            System.out.println(-1);
        }
        else
            System.out.println(max-1);


        for (int i=0;i<col;i++){
            System.out.println(Arrays.toString(tar[i]));
        }
        return;

    }
}
