package org.sunny.interview.WangYi;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Spliterator;

/**
 * 49
 0 0
 8 43
 11 14
 4 38
 19 48
 21 8
 21 0
 17 18
 14 18
 13 41
 16 57
 7 12
 19 38
 15 55
 0 0
 10 0
 9 51
 23 8
 13 15
 22 51
 19 55
 20 55
 4 55
 20 18
 22 5
 3 39
 14 51
 11 32
 13 50
 11 57
 23 33
 20 55
 16 21
 23 21
 0 51
 21 31
 1 51
 7 41
 6 15
 0 18
 22 11
 7 26
 9 52
 1 9
 10 42
 6 26
 6 59
 19 50
 20 49
 69
 12 49
 */
public class WY7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int clocknum = scanner.nextInt();
        LocalTime[] clocks = new LocalTime[clocknum];
        for (int i=0;i<clocknum;i++){
            clocks[i] = LocalTime.of(scanner.nextInt(),scanner.nextInt());

        }

        int pathspend = scanner.nextInt();
        LocalTime lessontime = LocalTime.of(scanner.nextInt(),scanner.nextInt());
        //System.out.println("lesson:"+lessontime.getHour()+" "+lessontime.getMinute());

        LocalTime laterClock = null;
        for (LocalTime l : clocks){
            LocalTime ll = l.plusMinutes(pathspend);

            if (laterClock != null){
                if ((ll.isBefore(lessontime)|| ll.equals(lessontime)) && l.isAfter(laterClock) && (ll.isAfter(l) || ll.equals(l))){

                    laterClock = l;
                    //System.out.println("later:"+laterClock.getHour()+" "+laterClock.getMinute());
                }
            }
            else {
                if ((ll.isBefore(lessontime)|| ll.equals(lessontime)) && (ll.isAfter(l) || ll.equals(l))){

                    laterClock = l;
                    //System.out.println("later:"+laterClock.getHour()+" "+laterClock.getMinute());
                }
            }


        }



        System.out.println(laterClock.getHour()+" "+laterClock.getMinute());
    }
}
