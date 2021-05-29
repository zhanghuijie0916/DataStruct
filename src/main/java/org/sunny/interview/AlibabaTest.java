package org.sunny.interview;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
记录停车场每一辆车进入的时间和开出的时间，出库的优先权大于入库的优先权，
判断停车场中最多有多少量汽车
8,9;4,6;3,7;6,8

8,9;6,11;4,10;3,7
 */
public class AlibabaTest {


    private static final String carSplit =";";
    private static final String timeSplit =",";
    private static final String regress = "(\\d{1,2},\\d{1,2};)*\\d{1,2},\\d{1,2}$";

    public static void main(String[] args) {
        String inString = null;
        // 数据输入
        Scanner in = new Scanner(System.in);
        inString = in.nextLine();
        //字符串数组格式校验
        Pattern pat = Pattern.compile(regress);
        if(inString == null || inString.trim().equals("")||!pat.matcher(inString).matches()){
            System.out.println("输入错误!");
            return;
        }
        AlibabaTest sol = new AlibabaTest();
        int countCars = sol.countCars(sol.convertToArray(inString));
        System.out.println(countCars);
    }

    //输入字符串转数组
    public int[][] convertToArray(String str) {
        String[] strArray = str.split(carSplit);
        int row = strArray.length;
        int col = 2;
        // 字符转数组判断
        int[][] carArray = new int[row][col];
        int start,end;
        for (int i = 0; i < row; i++) {
            start = Integer.parseInt(strArray[i].split(timeSplit)[0]);
            end = Integer.parseInt(strArray[i].split(timeSplit)[1]);
            if(start>end){
                continue;
            }
            carArray[i][0] = start;
            carArray[i][1] = end;
        }
        return carArray;
    }
    //核心算法实现
    public int countCars(int[][] carArray) {
        int ans = 0;
        for (int[] a :carArray){
            System.out.println(Arrays.toString(a));
        }



        System.out.println(carArray.length);
        int[] tar = new int[carArray.length];
        for (int i=0;i<carArray.length;i++){
            int input = carArray[i][0];
            int output = carArray[i][1];
            for (int j=i+1;j<carArray.length;j++){
                System.out.println(input+"-"+output+"---"+carArray[j][0]+"--"+carArray[j][1]);
                if ((output>carArray[j][0] && output<carArray[j][1]) || (input>carArray[j][0] && input<carArray[j][1])){
                    tar[i]++;
                    tar[j]++;
                }
            }
        }

        ans = Arrays.stream(tar).max().getAsInt();

        return ans; // 返回计算结果
    }
}
