package org.sunny.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 题目描述
 小易邀请你玩一个数字游戏，小易给你一系列的整数。你们俩使用这些整数玩游戏。
 每次小易会任意说一个数字出来，然后你需要从这一系列数字中选取一部分出来让它们的和
 等于小易所说的数字。 例如： 如果{2,1,2,7}是你有的一系列数，小易说的数字是11.
 你可以得到方案2+2+7 = 11.如果顽皮的小易想坑你，他说的数字是6，那么你没有办法拼凑出和为6
 现在小易给你n个数，让你找出无法从n个数中选取部分求和的数字中的最小数。
 输入描述:输入第一行为数字个数n (n ≤ 20)
    第二行为n个数xi (1 ≤ xi ≤ 100000)
 输出描述:输出最小不能由n个数选取求和组成的数
 示例1
    输入 3
        5 1 2
    输出
    4

    输入 19
 * 64 8192 99932 512 65536 1024 2 2048 4 4096 16384 8 15919 256 32 1 32768 128 16
 * 输出
    246923
 *
 *
 */
public class NumberGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        ArrayList<Long> list = new ArrayList<>();
        for (int i=0;i<N;i++){
            list.add(scan.nextLong());
        }
        Collections.sort(list);

        HashSet<Long> set = new HashSet<>();
        set.add(list.get(0));

        for (int k=1;k<list.size();k++){
            Long ele = list.get(k);
            Set tempset = set.stream().map(x->x+ele).collect(Collectors.toSet());
            set.addAll(tempset);
            set.add(ele);
        }

        ArrayList<Long> result = new ArrayList(set);
        Collections.sort(result);
        //Long[] longarray = new Long[set.size()];
        //set.toArray(longarray);
        //result.forEach(x->System.out.println("--"+x));

        //System.out.println(set.size());
        for (int j=0;j<result.size();j++){
            if ((j+1) != result.get(j)){
                System.out.println((j+1));
                System.exit(0);
            }
        }

        //System.out.println();
        System.out.println(result.get(result.size()-1)+1);
    }
}
