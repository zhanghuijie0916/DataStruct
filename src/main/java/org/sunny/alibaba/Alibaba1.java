package org.sunny.alibaba;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
刚才我说了我要退款我要退款我要退款我都说了我要退款
阿里旺旺
刚才我说了我要退款我都说了我要退款

//1、1、2、3、5、8、13、21、34
 */
public class Alibaba1 {
    public static int getnext(int i){
        // 测试解决冲突
        System.out.println("1235");
        if (i<=0){
            return 0;
        }
        else if (i>0 & i<=2){
            return 1;
        }
        else {
            return getnext(i-1)+getnext(i-2);
        }
    }

    public static void main(String[] args){
        System.out.println(getnext(30));

    }
}
