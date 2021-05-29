package org.sunny.interview.ternet;

import java.util.*;

public class Ternet3 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Integer machines = scan.nextInt();
        Integer tasks = scan.nextInt();

        int result = 0;

        TreeMap<Integer,Integer> machineTree = new TreeMap<>();
        TreeMap<Integer,Integer> taskTree = new TreeMap<>();
        HashMap<Integer,Integer> resultMap = new HashMap<>();

        for (int i=0;i<machines;i++){
            machineTree.put(scan.nextInt(),scan.nextInt());  //机器的最长工作时间/等级
        }
        for (int j=0;j<tasks;j++){
            taskTree.put(scan.nextInt(),scan.nextInt());
        }

        for (Integer machineTime : machineTree.keySet()){
            Integer machineDeng = machineTree.get(machineTime);

            Integer a = taskTree.floorKey(machineTime);
            NavigableMap<Integer,Integer> temptaskTree = taskTree.headMap(a,true);
            Integer deletekey = Integer.MAX_VALUE;
            Integer diff = 0;
            for (Integer key : temptaskTree.keySet()){
                if (temptaskTree.get(key).compareTo(machineDeng)<0 && key>deletekey){
                    deletekey = key;
                    diff = temptaskTree.get(key);
                }
            }
            if (!deletekey.equals(Integer.MAX_VALUE)){
                resultMap.put(deletekey,diff);
                taskTree.remove(deletekey);
                result++;

            }



            Integer liyi = 0;
            for (Map.Entry<Integer,Integer> e : resultMap.entrySet()){
                liyi += e.getKey()*200+e.getValue()*3;
            }
            System.out.println(result+" "+liyi);


        }


    }
}
