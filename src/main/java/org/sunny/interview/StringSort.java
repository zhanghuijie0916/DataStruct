package org.sunny.interview;

import java.util.*;
import java.util.Scanner;

/**
 * 题目描述
 考拉有n个字符串字符串，任意两个字符串长度都是不同的。考拉最近学习到有两种字符串的排序方法：
 1.根据字符串的字典序排序。例如：
 "car" < "carriage" < "cats" < "doggies < "koala"
 2.根据字符串的长度排序。例如：
 "car" < "cats" < "koala" < "doggies" < "carriage"
 考拉想知道自己的这些字符串排列顺序是否满足这两种排序方法，考拉要忙着吃树叶，所以需要你来帮忙验证。
 输入描述:
 输入第一行为字符串个数n(n ≤ 100)
 接下来的n行,每行一个字符串,字符串长度均小于100，均由小写字母组成
 输出描述:
 如果这些字符串是根据字典序排列而不是根据长度排列输出"lexicographically",

 如果根据长度排列而不是字典序排列输出"lengths",

 如果两种方式都符合输出"both"，否则输出"none"
 示例1
 输入
 3
 a
 aa
 bbb
 输出
 both
 */
public class StringSort {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i=0;i<n;i++){
            list.add(scan.next());
        }

        ArrayList<String> wordlist = new ArrayList<>(list);
        Collections.sort(wordlist);

        ArrayList<String> lengthlist = new ArrayList<>(list);
        Collections.sort(lengthlist,(o1,o2)->o1.length()-o2.length());

        boolean wordflag = true;
        boolean lengthflag = true;

        for (int i=0;i<list.size();i++){
            if (!list.get(i).equals(wordlist.get(i))){
                wordflag = false;
                break;
            }
        }

        for (int i=0;i<list.size();i++){
            if (!list.get(i).equals(lengthlist.get(i))){
                lengthflag = false;
                break;
            }
        }

        if (wordflag && lengthflag){
            System.out.println("both");
        }
        else if(wordflag){
            System.out.println("lexicographically");
        }
        else  if(lengthflag){
            System.out.println("lengths");
        }
        else
            System.out.println("none");

        /*System.out.println();
        list.forEach(x->System.out.print(x+"  "));

        System.out.println();
        wordlist.forEach(x->System.out.print(x+"  "));

        System.out.println();
        lengthlist.forEach(x->System.out.print(x+"  "));*/
    }

}
