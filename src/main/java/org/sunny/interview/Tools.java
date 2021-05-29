package org.sunny.interview;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {


    public static void main(String[] strs){
        ArrayList<ArrayList<Integer>> allList = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        calQuanSort(allList,list,0);

        for (int i=0;i<allList.size();i++){
            System.out.println();
            allList.get(i).forEach(System.out::print);
        }
    }

    /**
     * 得到arraylist的全排序
     * @param allList
     * @param list
     * @param n
     */
    public static void calQuanSort(ArrayList<ArrayList<Integer>> allList,ArrayList<Integer> list,int n){

        if (n==list.size()){
            allList.add(new ArrayList<>(list));
        }

        else {
            for (int i=n;i<list.size();i++){
                Collections.swap(list,i,n);
                calQuanSort(allList,list,n+1);
                Collections.swap(list,i,n);
            }
        }
    }

    /*
    刚才我说了我要退款我要退款我要退款我都说了我要退款 => 刚才我说了我要退款我都说了我要退款
    阿里旺旺 => 阿里旺旺
    刚才我说了我要退款我都说了我要退款 => 刚才我说了我要退款我都说了我要退款
     */
    public static void regexMatch(String s){
        Pattern pattern = Pattern.compile("(\\D{2,})(\\1)+");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String pstr = matcher.group(1);
            s = s.substring(0,start+pstr.length())+s.substring(end,s.length());
            System.out.println(s);
            matcher.reset(s);
        }

        System.out.println(s);
    }
}
