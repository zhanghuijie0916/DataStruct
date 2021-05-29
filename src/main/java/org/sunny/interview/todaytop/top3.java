package org.sunny.interview.todaytop;

import java.util.*;

/*
3
3 1 2 3
3 2 4 6
3 3 4 6
 */
public class top3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int groupnum = scan.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<groupnum;i++){

            ArrayList<Integer> numberlist = new ArrayList<>();
            LinkedHashSet<Integer> periodset = new LinkedHashSet<>();
            LinkedHashSet<Integer> periodset2 = new LinkedHashSet<>();

            //TreeSet<Integer> periodset = new TreeSet<>();
            int numbersnum = scan.nextInt();
            int prenumber = 0;

            if (numbersnum>0){
                prenumber = scan.nextInt();
                numberlist.add(prenumber);
            }
            for (int k=1;k<numbersnum;k++){
                int afternumber = scan.nextInt();
                numberlist.add(afternumber);
                periodset.add(Math.abs(afternumber-prenumber));
                prenumber=afternumber;
            }



            ArrayList<Integer> periodlist = new ArrayList<>();
            periodlist.addAll(periodset);
            //periodlist.forEach(System.out::print);

            Collections.sort(periodlist);


            /*numberlist.forEach(System.out::print);
            System.out.println("list");
            periodset.forEach(System.out::print);
            System.out.println("sort");
*/
            int periodResult = -1;
            boolean flag = true;

            int maxnumber = numberlist.get(numberlist.size()-1);

            for (Integer period : periodlist){
                for (int j=0;j<numberlist.size();j++){
                    int temp = period+numberlist.get(j);
                    if (temp<=maxnumber && !numberlist.contains(temp)){
                        flag = false;
                        break;
                    }
                }

                if (flag){
                    periodResult=period;
                }
            }


            if (periodResult==-1){
                if (numbersnum==1){
                    periodResult = 0;
                }
                else {
                    periodResult = periodlist.get(periodlist.size()-1)+1;
                }
            }
            list.add(periodResult);

        }

        for (Integer i : list){
            System.out.println(i);
        }


    }
}
