package org.sunny.interview.WangYi;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 9
 ....X.X..
 */
public class WY3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testnum = scanner.nextInt(); //测试例子数目
        //Pattern pattern = Pattern.compile("((\\.)+)|(\\.[x]\\.)");

        //Pattern pattern = Pattern.compile(("([^\\.]?\\.[X]\\.[^\\.]?)|((\\.)+)"));

        Pattern pattern = Pattern.compile(("([^\\.]?\\.[X]\\.[^\\.]?)|((\\.){1,3})"));
        Matcher matcher = null;

        for (int n=0;n<testnum;n++){
            int gridsnum = scanner.nextInt();
            int thiscounter = 0;
            String lamps = scanner.next();
            //System.out.println(lamps);

            if (matcher == null){
                matcher = pattern.matcher(lamps);
            }
            else {
                matcher = matcher.reset(lamps);
            }

            while (matcher.find()){

                String s1 = matcher.group(1);
                if (s1!=null){
                    System.out.println("group1="+s1);
                    thiscounter += s1.length()/3;
                }
                else {
                    String s2 = matcher.group();
                    System.out.println("group2="+s2);
                    int c = s2.length()%3;
                    int a = s2.length()/3;
                    if (c!=0){
                        thiscounter += a+1;
                    }
                    else
                        thiscounter += a;

                }

            }
            System.out.println(thiscounter);
        }
    }
}

/*
1
11
...XX....XX

1
3
.X.

 */
