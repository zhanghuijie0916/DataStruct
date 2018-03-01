package org.sunny.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Path {
    private int distance;
    private Vetex startVetex;
    private Vetex endVetex;
    private LinkedList<Vetex> pathList;


    public Path(int distance, Vetex startVetex, Vetex endVetex) {
        this.distance = distance;
        this.startVetex = startVetex;
        this.endVetex = endVetex;
        pathList = new LinkedList<Vetex>();
    }

    public Path(){
        this(Integer.MAX_VALUE,null,null);
    }

    /**
     * 判断起点和终点之间是否存在路径
     * @return
     */
    public boolean hasPath(){
        return (distance!=Integer.MAX_VALUE & startVetex!=null & endVetex!=null);
    }

    /**
     * 路径长度
     * @return
     */
    public int pathLength(){
        if(!hasPath()) return -1;
        else if(startVetex==endVetex) return 0;
        else return pathList.size()+1;
    }

    public LinkedList<Vetex> getPathList() {
        return pathList;
    }

    public void cleanPathList() {
        this.pathList = new LinkedList<Vetex>();
    }

    //添加路径信息
    public void addPath(Vetex path){
        pathList.add(path);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vetex getStartVetex() {
        return startVetex;
    }

    public void setStartVetex(Vetex startVetex) {
        this.startVetex = startVetex;
    }

    public Vetex getEndVetex() {
        return endVetex;
    }

    public void setEndVetex(Vetex endVetex) {
        this.endVetex = endVetex;
    }

    @Override
    public String toString() {
        String listString = "";
        Iterator<Vetex> iterator = pathList.iterator();
        while (iterator.hasNext()){
            listString += iterator.next().getInformatin()+",";
        }
        return "Path{" +
                "distance=" + distance +
                ", startVetex=" + startVetex.getInformatin() +
                ", endVetex=" + endVetex.getInformatin() +
                ", pathList=[" + listString +"]"+
                '}';
    }
}
