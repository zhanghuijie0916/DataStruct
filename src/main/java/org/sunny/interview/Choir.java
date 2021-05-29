package org.sunny.interview;

public class Choir {


    /**
     * 题目描述:[合唱团]
     有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
     要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
     输入描述:
     每个输入包含 1 个测试用例。每个测试数据的第一行包含一个整数 n (1 <= n <= 50)，表示学生的个数，
     接下来的一行，包含 n 个整数，按顺序表示每个学生的能力值 ai（-50 <= ai <= 50）。
     接下来的一行包含两个整数，k 和 d (1 <= k <= 10, 1 <= d <= 50)。
     输出描述:
     输出一行表示最大的乘积。
     * @param N
     * @param K
     * @param energys
     * @param maxDis
     */
    public static void getMaxEnergy(Integer N,Integer K,Integer[] energys,int maxDis){

        Integer[][] f = new Integer[N+1][K+1];
        Integer[][] g = new Integer[N+1][K+1];

        for (int i=1;i<=N;i++){
            f[i][1] = energys[i];
            g[i][1] = energys[i];
        }

        for (int k=2;k<=K;k++){
            for (int now=k;now<=N;now++){
                Integer tempmax = Integer.MIN_VALUE;
                Integer tempmin = Integer.MAX_VALUE;

                for (int prevoius=Math.max(k-1,now-maxDis);prevoius<=now-1;prevoius++){

                    if (tempmax<Math.max(f[prevoius][k-1]*energys[now],g[prevoius][k-1]*energys[now])){
                        tempmax = Math.max(f[prevoius][k-1]*energys[now],g[prevoius][k-1]*energys[now]);
                    }
                    if (tempmin>Math.min(f[prevoius][k-1]*energys[now],g[prevoius][k-1]*energys[now])){
                        tempmin = Math.min(f[prevoius][k-1]*energys[now],g[prevoius][k-1]*energys[now]);
                    }
                }

                f[now][k] = tempmax;
                g[now][k] = tempmin;
            }
        }


        Integer result = Integer.MIN_VALUE;
        for (int j=K;j<=N;j++){
            if (result< f[j][K]){
                result = f[j][K];
            }
        }

        System.out.println(result);

    }


    public static void main(String[] args) {
       /* Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        Integer[] array = new Integer[N + 1];
        for (int i = 1; i <= N; i++) {
            array[i] = scan.nextInt();
        }
        int K = scan.nextInt();
        int maxDis = scan.nextInt();
        getMaxEnergy(N, K, array, maxDis);

*/
        //getMaxEnergy(6,2,new Integer[]{0,-2,-3,-4,-1,-5,-3},3);
        //System.out.println(Math.min(-2*(-5),-4*(-6)));


    }
}
