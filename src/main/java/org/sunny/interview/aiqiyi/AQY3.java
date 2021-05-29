package org.sunny.interview.aiqiyi;

import com.sun.org.apache.regexp.internal.RE;


import java.util.*;

public class AQY3 {
    private static TreeSet<String> list = new TreeSet<>();

    public static void perm(String s){
        ArrayList<String> result = new ArrayList<>();
        for (int i=1;i<=s.length();i++){
            perm(s,i,result);
        }
    }

    public static void perm(String s,int m,ArrayList<String > result){
        if(m==0){
            String temps = "";
            for(int i=0;i<result.size();i++){
                temps = temps + result.get(i);
                //System.out.print(result.get(i));
            }
            list.add(temps);
            //System.out.println();
            return;
        }

        if (s.length()!=0){
            //选当前元素
            result.add(s.charAt(0)+"");
            perm(s.substring(1,s.length()),m-1,result);
            result.remove(result.size()-1);

            //不选当前元素
            perm(s.substring(1,s.length()),m,result);
        }
    }

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        //得到字符串的所有字串
        perm(str);
        list.forEach(System.out::println);
        //System.out.println(list.last());
    }
}



