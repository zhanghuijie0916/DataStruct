package org.sunny.graph.application;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * 单词接龙
 * figure 9-38
 */
public class WordLadder {

    /**
     *
     * @param adjacentWords 某一个单词改变一个单词可以得到的单词列表
     * @param startWord 单词接龙的头单词
     * @param desWord 单词接龙的尾单词
     * @return 中间最短的单词列表
     */
    public static List<String> findChain(Map<String,List<String>> adjacentWords,String startWord,String desWord){
        //按照所需要转换的startWord，将单词变为what(key)能得到startWord(value)
        Map<String,String> chainMap = new HashMap<String, String>();
        Queue<String> queue = new LinkedList<String>();

        queue.add(startWord);
        while (!queue.isEmpty()){
            String key = queue.poll();
            List<String> adjList = adjacentWords.get(key);
            if (adjList != null){
                for (String str : adjList){
                    /*
                     * 解释：比如2➡️1，3➡️1，3➡️2➡️1，可以看出3-2-1不是3-1的最短距离，
                     * 可以看成一层一层的，先遍历的层一定是两个单词之间最短的。
                     */
                    if (chainMap.get(str) == null){
                        chainMap.put(str,key);
                        queue.add(str);
                    }
                }
            }
        }
        chainMap.put(startWord,null);

        return getChainFromChainMap(chainMap,startWord,desWord);

    }

    /**
     * 从尾单词向首单词推进
     * @param chainMap
     * @param startWord
     * @param desWord
     * @return
     */
    private static LinkedList<String> getChainFromChainMap(Map<String,String> chainMap,String startWord,String desWord){
        LinkedList<String> result = null;
        if (chainMap != null){
            result = new LinkedList<String>();
            for (String str=desWord; str!=null ; str=chainMap.get(str)){
                result.addFirst(str);
            }
        }
        return result;
    }

    public static void main(String[] args){
        Map<String,List<String>> adjacentWordMap = new HashMap<String, List<String>>();
        String[] zeroArr = new String[]{"aero","hero","zero"};
        String[] heroArr = new String[]{"zero","here","hera"};
        String[] hereArr = new String[]{"hero","hire","fare","fire"};
        String[] fireArr = new String[]{"fare","hire","here","five"};
        String[] fiveArr = new String[]{"hive","fire","nive"};
        List<String> zeroList = Arrays.asList(zeroArr);
        List<String> heroList = Arrays.asList(heroArr);
        List<String> hereList = Arrays.asList(hereArr);
        List<String> fireList = Arrays.asList(fireArr);
        List<String> fiveList = Arrays.asList(fiveArr);
        adjacentWordMap.put("zero",zeroList);
        adjacentWordMap.put("hero",heroList);
        adjacentWordMap.put("here",hereList);
        adjacentWordMap.put("fire",fireList);
        adjacentWordMap.put("five",fiveList);

        List<String> chainList = findChain(adjacentWordMap,"zero","five");

        for (String str : chainList){
            System.out.println(str);
        }
    }
}
