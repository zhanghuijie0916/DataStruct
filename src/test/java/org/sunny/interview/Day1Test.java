package org.sunny.interview;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day1Test {
    @Ignore
    public void centralDispatch() throws Exception {
        int[] t = new int[]{7,10};
        int jobs = 6;
        int[] processTime = Day1.centralDispatch(t,2,jobs);
        for (int i=0;i<t.length;i++){
            System.out.println("第"+i+"台机器执行"+processTime[i]/t[i]+"个任务，共花费"+processTime[i]);
        }
    }

    @Test
    public void shortestPathTest() throws Exception{
        int[][] array = new int[][]{{1,4,3},{8,7,5},{2,1,5}};
        int path_len = Day1.shortestPath(array);
        System.out.println("\n"+"最短经过的路径为："+path_len);
    }

    @Test
    public void containSourceTest() throws Exception{

        boolean result = Day1.containSource("oxoxoxox","ooxxoo");
        if(result){
            System.out.println("Yes");
        }
        else
            System.out.println("No");
    }

    /*
    找到圆上优雅的点
     */
    @Test
    public void getElementPoint() throws Exception{
        int count = Day1.getElementPoint(365);
        System.out.println(count);
    }

}