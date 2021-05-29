package org.sunny.interview.important;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaxContinue {

    public static void main(String[] args) throws IOException{
        Integer x = 10;
        String c = Integer.toBinaryString(x);
        //System.out.print(c);

        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("A.TXT")));

        Pattern p = Pattern.compile("(\\d{2}).*(\\1)");

        String[] strs = "11i am.a student,happy,a11".split("(11)(.)\\1");

        Matcher matcher = p.matcher("11 an 11 and 22 mysql -u root -22");
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
