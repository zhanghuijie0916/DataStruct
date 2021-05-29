package org.sunny.interview;


import java.util.*;

/**
 * map新增方法练习
 */
public class Day3 {

    /*
        1。如果没有这个key而且新值不为null,添加键值对；
        2。如果没有这个key而且新值为null,直接返回null，不添加；
        3。如果有key但是新值为null，删除该键值对；
        4。有key且新值不为null，修改键值对；
         */
    public static Map mapcompute(Map map){
        Object o1 = map.compute("疯狂java讲义",(key,value)->null);
        System.out.println(o1);
        return map;
    }

    /*
    1。如果不存在该key值，就会根据key值重新计算出一个新value，如果新value不为null，添加键值对；否则不添加；
    2。如果该key值对应的value值为null，会根据key值重新计算出一个新value，然后替换掉旧value，
    但是如果新value仍为null，不删除该键值对。
    3.如果该key值对应的value值不为null，无操作
     */
    public static Map mapcomputeIfAbsent(Map map){

        Object o2 = map.computeIfAbsent("疯狂java讲义",key->null);
        System.out.println(o2);
        return map;
    }

    /*
        如果key对应的value值为null,那么新value不会覆盖旧value，无论新value是不是null；
        如果key对应的value值不为null,新value为null，删除该键值对；
        如果没有这个key，不会添加
         */
    public static Map mapcomputeIfPresent(Map map){

        Object o3 = map.computeIfPresent("疯狂java讲义",(key,value)->null);
        System.out.println(o3);
        return map;
    }



    public static void main(String[] args){

        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("疯狂java讲义",11);
        map.put("疯狂iso讲义",120);
        map.put("javaEE实战",200);
        map.put("Python数据分析",40);


        /*
        如果对应的key的value值为null，就使用新value（第二个参数）替换旧value；
        如果key对应的旧value不为null，使用旧value和新value计算出一个新value覆盖；
         */
        map.merge("疯狂java讲义",1,(oldvalue,newvalue)->null);
        map.forEach((key,value)->System.out.println(key+"--"+value));


       map.replaceAll((key,value)->key.length());
       map.forEach((key,value)->System.out.println(key+"--"+value));

    }
}
