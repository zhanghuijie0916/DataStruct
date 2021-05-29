package org.sunny.interview.lianjia;



import java.util.*;

/*
3
1 1

1 2

1 1

 */
public class Lian1 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int kgs = scan.nextInt();
        ArrayList<Integer> controlK = new ArrayList<>();  //每一个开关控制的灯数目
        ArrayList<ArrayList<Integer>> tamps = new ArrayList<>();
        HashMap<Integer,Integer> tampsMap = new HashMap<>();


        for (int i=0;i<kgs;i++){
            int k = scan.nextInt();
            controlK.add(k);

            ArrayList<Integer> tempTamp = new ArrayList<>();
            for (int j=0;j<k;j++){
                Integer tampID = scan.nextInt();

                if(tampsMap.containsKey(tampID)){
                    tampsMap.compute(tampID,(key,value)->value+1);
                }else {
                    tampsMap.put(tampID,1);
                }
                tempTamp.add(tampID);
            }
            tamps.add(tempTamp);
        }

        System.out.println(tampsMap.size());
        //tampsMap.forEach((key,value)->System.out.println(key+":"+value));


        /*int[][] tampArray = new int[tampsMap.size()][2];

        tampsMap.forEach((key,value)->System.out.println(key+":"+value));
        int i=0;
        for (Integer key:tampsMap.keySet()){
            Integer value = tampsMap.get(key);
            tampArray[i][0] = key;
            tampArray[i][1] = value;
            i++;
        }

        Arrays.sort(tampArray,(o1,o2)->o1[1]-o2[1]);
        for (int[] a:tampArray){
            System.out.println(Arrays.toString(a));
        }
*/


    }
}
