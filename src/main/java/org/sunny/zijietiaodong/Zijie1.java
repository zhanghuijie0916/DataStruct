package org.sunny.zijietiaodong;


import java.util.*;

public class Zijie1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] mn = s.split(",");
        int M = Integer.valueOf(mn[0]);
        int N = Integer.valueOf(mn[1]);
        int[][] array = new int[M][N];
        for (int i=0;i<M;i++){
            String row = scanner.nextLine();
            String[] rows = row.split(",");
            for(int j=0;j<N;j++){
                array[i][j] = Integer.valueOf(rows[j]);
            }
        }

        Map<Integer,Integer> result = new HashMap<>();
        int ball = 2;
        for (int i=0;i<M;i++){
            for (int j=0;j<N;j++){
                if (array[i][j] == 0 || array[i][j]!=1){
                    continue;
                }else {
                    int flag = 0;
                    if (i>0){
                        flag = (array[i-1][j]!=0&array[i-1][j]!=1)?array[i-1][j]:flag;
                    }
                    if (i>0 && j<N-1){
                        flag = (array[i-1][j+1]!=0&array[i-1][j+1]!=1)?array[i-1][j+1]:flag;
                    }
                    if (i<M-1 && j<N-1){
                        flag = (array[i+1][j+1]!=0&array[i+1][j+1]!=1)?array[i+1][j+1]:flag;
                    }
                    if (j<N-1){
                        flag = (array[i][j+1]!=0&array[i][j+1]!=1)?array[i][j+1]:flag;
                    }
                    if (i<M-1){
                        flag = (array[i+1][j]!=0&array[i+1][j]!=1)?array[i+1][j]:flag;
                    }
                    if (j>0){
                        flag = (array[i][j-1]!=0&array[i][j-1]!=1)?array[i][j-1]:flag;
                    }
                    if (i<M-1 && j>0){
                        flag = (array[i+1][j-1]!=0&array[i+1][j-1]!=1)?array[i+1][j-1]:flag;
                    }
                    if (i>0 && j>0){
                        flag = (array[i-1][j-1]!=0&array[i-1][j-1]!=1)?array[i-1][j-1]:flag;
                    }
                    if (flag == 0){
                        flag = ball;
                        array[i][j] = ball;
                        ball++;
                    }
                    else {
                        array[i][j] = flag;
                    }

                    if (result.containsKey(flag)){
                        result.put(flag,result.get(flag)+1);
                    }
                    else {
                        result.put(flag,1);
                    }

                    if (i>0){
                        if (array[i-1][j]>array[i][j]){
                            result.put(array[i-1][j],result.get(array[i-1][j])-1);
                            array[i-1][j] = array[i][j];
                            result.put(array[i-1][j],result.get(array[i-1][j])+1);
                        }
                    }

                    if (j>0){
                        if (array[i][j-1]>array[i][j]){
                            result.put(array[i][j-1],result.get(array[i][j-1])-1);
                            array[i][j-1] = array[i][j];
                            result.put(array[i][j-1],result.get(array[i][j-1])+1);
                        }
                    }


                }
            }
        }

        List<Integer> res = new ArrayList<>();
        res.addAll(result.keySet());
        Collections.sort(res);


        System.out.println((ball-1)+","+ res.get(res.size()-1));
/*

        System.out.println("ball---"+ball);

        for (int i=0;i<M;i++){
            System.out.println(Arrays.toString(array[i]));
        }

        System.out.println(result);
*/


    }
}
