package org.sunny.interview;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 题目描述
 小易喜欢的单词具有以下特性：
 1.单词每个字母都是大写字母
 2.单词没有连续相等的字母
 3.单词没有形如“xyxy”(这里的x，y指的都是字母，并且可以相同)这样的子序列，子序列可能不连续。
 例如：
 小易不喜欢"ABBA"，因为这里有两个连续的'B'
 小易不喜欢"THETXH"，因为这里包含子序列"THTH"
 小易不喜欢"ABACADA"，因为这里包含子序列"AAAA"
 小易喜欢"A","ABA"和"ABCBA"这些单词
 给你一个单词，你要回答小易是否会喜欢这个单词。
 输入描述:
 输入为一个字符串，都由大写字母组成，长度小于100
 输出描述:
 如果小易喜欢输出"Likes",不喜欢输出"Dislikes"
 示例1
 输入
 AAA
 输出
 Dislikes
 */
public class LikedString {
    public static boolean isUperWord(String str){
        return str.matches("[A-Z]*");
    }

    //匹配第二个条件
    public static boolean hasContinueWord(String str){
        return str.matches(".*(.)(\\1).*");
    }

    //匹配第三个条件
    public static boolean hasSubString(String str){
        return str.matches(".*(.).*(.)(.*\\1).*(.*\\2).*");
    }

    public static void main(String[] args) {


        /*Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println(!hasContinueWord(str)+"---"+!hasSubString(str));

        if (isUperWord(str) && !hasContinueWord(str) && !hasSubString(str)){

            System.out.println("Likes");
        }
        else
            System.out.println("Dislikes");
*/

        //正则表达练习
        String s = "aa2012-2-13ass";

        Pattern p = Pattern.compile("(\\d{1,})");

        Matcher matcher = null;

        matcher = p.matcher(s);
        matcher.find();
        System.out.println(matcher.group(1)+":"+matcher.start(1)+"----"+matcher.end(1));

         /*while (matcher.find()){
             System.out.println(matcher.group()+":"+matcher.start()+"----"+matcher.end());
         }*/

    }


}

