package org.sunny.interview;

import javax.activation.MailcapCommandMap;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {

    /**
     * 1.假设有一个中央调度机，有n个相同的任务需要调度到m台服务器上执行，每台服务器的配置不同，
     * 因此，每台服务器所需要的时间也是不同的。假设第i台服务器执行一个任务所需的时间为t[i],
     * 请设计调度算法，使得所有任务完成所需要的时间最短。
     */

    public static int[] centralDispatch(int[] t,int servers,int jobs){
        int[] serverTime = new int[servers];
        for (int i=0;i<jobs;i++){

            int serverID = 0;
            int processTime = serverTime[0]+t[0];

            for (int j=1;j<servers;j++){
                if (processTime > serverTime[j]+t[j]){
                    serverID = j;
                }
            }

            serverTime[serverID] = serverTime[serverID]+t[serverID];
        }
        return serverTime;
    }


    /**
     * 2.寻找一条从左上角（array[0][0]）至右下角（array[m-1][n-1]）的路线，
     * 使得数组中的整数和最小。
     * @param array
     * @return
     */
    public static int shortestPath(int[][] array){
        int rows = array.length;
        int cols = array[0].length;


        int[][] f = new int[rows][cols];
        f[0][0] = array[0][0];
        for (int i=1;i<cols;i++){
            f[i][0] = f[i-1][0]+array[i][0];
        }

        for (int j=1;j<rows;j++){
            f[0][j] = f[0][j-1] + array[0][j];
        }

        System.out.println("array[0][0]至右下角的最短路径经历的坐标为：");
        for (int i=1; i<cols;i++){
            for (int j=1;j<rows;j++){
                if (f[i-1][j] < f[i][j-1]){
                    f[i][j] = f[i-1][j] + array[i][j];
                    System.out.println("("+(i-1)+j+")");
                }
                else {
                    f[i][j] = f[i][j-1] + array[i][j];
                    System.out.println("("+i+","+(j-1)+")");
                }
            }
        }
        HashMap<Integer,String> map = new HashMap();
        return f[rows-1][cols-1];
    }


    /*
    3.题目描述:
    牛牛拿到了一个藏宝图，顺着藏宝图的指示，牛牛发现了一个藏宝盒，藏宝盒上有一个机关，机关每次会
    显示两个字符串 s 和 t，根据古老的传说，牛牛需要每次都回答 t 是否是 s 的子序列。注意，子序列
    不要求在原字符串中是连续的，例如串 abc，它的子序列就有 {空串, a, b, c, ab, ac, bc, abc}8种。
    输入描述:
    每个输入包含一个测试用例。每个测试用例包含两行长度不超过 10 的不包含空格的可见 ASCII 字符串。
    输出描述:
    输出一行 “Yes” 或者 “No” 表示结果。
    */
    public static boolean containSource(String srcStr,String desStr){
        char[] src = srcStr.toCharArray();
        char[] des = desStr.toCharArray();

        int indexsrc = 0;
        int indexdes = 0;
        int srclen = src.length;
        int deslen = des.length;
        while (indexsrc<srclen && indexdes<deslen){
            if (src[indexsrc] == des[indexdes]){
                indexdes++;
                indexsrc++;
            }
            else
                indexsrc++;
        }
        return indexdes == deslen;
    }


    /*
    4.题目描述
    小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是
    整数的点是优雅的，小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。
    例如：半径的平方如果为25
    优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
    输入描述:输入为一个整数，即为圆半径的平方,范围在32位int范围内。
     */
    public static int getElementPoint(int rsquare){
        int counter = 0;
        double r = Math.sqrt(rsquare);
        for (int x=1;x<=r;x++){
            double ytemp = Math.sqrt(rsquare-x*x);
            if (Math.ceil(ytemp)==Math.floor(ytemp)){
                counter++;
            }
        }
        return counter*4;
    }


    public static Set<Integer> findyueshu(int n){

        Set<Integer> result = new HashSet<>();
        for (int i=2;i<=n/2;i++){
            double j = (double) n/i;
            if(Math.ceil(j)==Math.floor(j)){
               result.add(i);
               result.add((int)j);
            }
        }

        return result;
    }

    public static int getPathByRule(int src,int des){
        int count = 0;
        int now = src;
        int cha = des-src;
        while (now <= des){
            Set<Integer> yuearray = findyueshu(now);
            System.out.println("now="+now+":"+Arrays.toString(yuearray.toArray()));

            int maxcha = Collections.max(yuearray);
            int mincha = Collections.min(yuearray);

            System.out.println("max="+maxcha+"--min="+mincha);

            if(cha>=mincha && cha<=maxcha){
                int index = Arrays.binarySearch(yuearray.toArray(),cha);
                if (index>=0){
                    break;
                }
                else {
                    count =  Integer.MAX_VALUE;
                    break;
                }

            }
            else if(cha>maxcha){
                count += 1;
                now = maxcha+now;

            }

        }
        return count;
    }

    /*
    输入例子1:
AkleBiCeilD

输出例子1:
kleieilABCD
     */
    public static String getDelete(String inistr){
            char[] chars = inistr.toCharArray();
            //System.out.println(Arrays.toString(chars));
            int len = chars.length;
            int end = len;
            int i=0;
            while (i<end){
                char c = chars[i];

                if (c>='A' && c<='Z'){
                    int j;
                    for (j=i;j<len-1;j++){
                        chars[j] = chars[j+1];
                    }
                    chars[j] = c;
                    end--;
                }
                else
                    i++;
            }

    inistr = String.valueOf(chars);
    return inistr;
    }

    public static int findCount(int[] array,int a){
        int counter = 0;
        for(int now:array){
            if (now==a){
                counter++;
            }
        }
        return counter;
    }

    public static int getdigui(int n){
        int mul = 1;
        if (n==1)
            return 1;
        else
            mul = n*getdigui(n-1);
            return mul;

    }

    /*
    腾讯笔试题

     */
    public static void getCompare(int n,int[] array){
        int len = array.length;
        int maxCount;
        int minCount;
        Arrays.sort(array);
        int min = findCount(array,array[0]);
        int max = findCount(array,array[len-1]);
        System.out.println("max="+max+"--min="+min);
        maxCount = min*max;

        if (min>1 || max>1){
            minCount = getdigui(min)/2+getdigui(max)/2;
            System.out.println("a"+minCount);
        }

        else{
            int[] beiarray = Arrays.copyOfRange(array,0,len-1);
            int[] janarray = Arrays.copyOfRange(array,1,len);
            int[] res = new int[janarray.length];
            for (int i=0;i<len-1;i++){
                res[i] = janarray[i]-beiarray[i];
                System.out.println(Arrays.toString(res));
            }
            Arrays.sort(res);
            System.out.println(Arrays.toString(res));
            minCount = findCount(res,res[0]);
        }


        System.out.println(minCount);
        System.out.println(maxCount);
    }

    public static int getMinDelete(String inistr){
        int min = Integer.MAX_VALUE;

        for (int k=0;k<1;k++){
            int huiwencount = 0;

            for(int i=k;i<inistr.length();i++){
                int j = inistr.length();
                char c = inistr.charAt(i);
                while (j>i){
                    if (inistr.indexOf(c) == inistr.lastIndexOf(c,inistr.length()-j)){
                        System.out.println(inistr.indexOf(c)+"--"+inistr.lastIndexOf(c,inistr.length()-j));
                        huiwencount += 2;
                        j = inistr.lastIndexOf(c,inistr.length()-j);
                    }
                    /*else if (inistr.lastIndexOf(c,inistr.length()-j)-inistr.indexOf(c) == 2){
                        huiwencount += 1;
                        j--;
                    }*/
                    else {
                        j--;
                    }
                }

                if (min>(inistr.length()-huiwencount)){
                    min = inistr.length()-huiwencount;
                }
            }
        }

        return min;


    }



    public static void main(String[] args){

        String s = "疯狂英语:99";
        String s1 = "疯狂";
        String s2 = "英语:99";
        final String s3 = "疯狂";
        final String s4 = "英语:99";

        String s5 = s1+s2;
        String s6 = s3+s4;

        String s7 = "疯狂英语:"+Integer.valueOf(99);
        final String s8 = "疯狂英语:"+Integer.valueOf(99);
        System.out.println(s==s5);  //false
        System.out.println(s==s6);  //true
        System.out.println(s==s7); //false
        System.out.println(s==s8);  //false

        String st = "google";
        int count = getMinDelete(st);
        System.out.println(count);




//        int count = Day1.getPathByRule(8,85678);
//        System.out.println(count);

//        String str = "hxKLAGLLzPyTxsFsrUnnSKQBHdQQrOyaEYJRgiJbHIDXFcQkFmIhPNKIBfHxXDB";
//        System.out.println(getDelete(str));





//        double x = 4.2;
//        System.out.println(Math.floor(x)+"----"+Math.ceil(x));

    }
}
