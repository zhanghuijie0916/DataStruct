
package org.sunny.graph;

import org.sunny.exception.*;
import java.util.*;



public abstract class AbstractGraph implements IGraph {
    protected INodeLinkedList<INode<Vetex>> vetexs;
    protected INodeLinkedList<INode<Edge>> edges;
    protected int graphType;

    public AbstractGraph(int graphType){
        vetexs = new NodeLinkedList();
        edges = new NodeLinkedList();
        this.graphType = graphType;
    }

    public int getType() {
        return this.graphType;
    }

    public int getVetexNum() {
        return vetexs.size();
    }

    public int getEdgeNum() {
        return vetexs.size();
    }

    public Iterator<Vetex> getVetexs() {
        LinkedList<Vetex> vetexList = new LinkedList<Vetex>();
        Iterator<INode<Vetex>> iterator = vetexs.iterator();
        while (iterator.hasNext()){
            vetexList.add(iterator.next().getData());
        }
        return vetexList.iterator();
    }

    public Iterator<Edge> getEdges() {
        LinkedList<Edge> edgeList = new LinkedList<Edge>();
        Iterator<INode<Edge>> iterator = edges.iterator();
        while (iterator.hasNext()){
            edgeList.add(iterator.next().getData());
        }
        return edgeList.iterator();
    }


    /**
     * 返回一个INode的原因是需要在Vetex和Edge类中标注出她的INode引用
     * @param vetex
     * @return
     */
    public INode insert(Vetex vetex) {
        return vetexs.insertLast(vetex);
    }

    public INode insert(Edge edge) {
        return edges.insertLast(edge);
    }

    /**
     * 判断顶点u和顶点v是否临接，即是否存早从u到v的边
     * @param u
     * @param v
     * @return
     */
    public boolean isAdjacent(Vetex u, Vetex v) {
        return edgeFromTo(u,v)!=null;
    }

    /**
     * 返回顶点u和顶点v之间的边
     * @param u
     * @param v
     * @return
     */
    public Edge adjacentEdge(Vetex u, Vetex v) {
        return edgeFromTo(u,v);
    }

    /**
     * 对图进行深度优先遍历
     * @param v
     * @return
     */
    public Iterator dfsTraverse(Vetex v) {
        return null;
    }

    /**
     * 对图进行广度优先遍历
     * @param v
     * @return
     */
    public Iterator bfsTraverse(Vetex v) {
        return null;
    }

    /**
     * 求无向图的最小生成树，有项图不支持此操作
     * @throws UnsupportOperation
     */
    public abstract void generateMST() throws UnsupportOperation;

    /**
     * 求有向无圈图的拓扑序列，无向图不支持此操作
     * @return
     * @throws UnsupportOperation
     * @throws CircularGraphException
     */
    public abstract Iterator<Vetex> topLogicalSort() throws UnsupportOperation,CircularGraphException;

    /**
     * 求有向无圈图的关键路径，无向图不支持此操作
     * @return
     * @throws UnsupportOperation
     * @throws CircularGraphException
     */
    public abstract LinkedList<Edge> criticalPath() throws UnsupportOperation,CircularGraphException;

    public abstract void remove(Vetex vetex);

    public abstract void remove(Edge edge);

    /**
     * 求取有向图最大流问题
     * @return
     * @throws UnsupportOperation
     */
    public abstract int maximumFlow() throws UnsupportOperation;

    /**
     * 返回从v1指向v2的边，不存在返回null
     * @param v1
     * @param v2
     * @return
     */
    protected abstract Edge edgeFromTo(Vetex v1,Vetex v2);

    /**
     * 返回从顶点v直接能够到达的相邻顶点
     * @param vetex
     * @return
     */
    public abstract Iterator<Vetex> adjacentVetexs(Vetex vetex);

    /**
     * 重置图中各边信息
     */
    protected void resetEdgeStates(){
        Iterator<Edge> iterator  = getEdges();
        while (iterator.hasNext()){
            Edge edge = iterator.next();
            edge.resetType();
        }
    }

    /**
     * 重置图中各顶点的状态
     */
    protected void resetVetexStates(){
        Iterator<Vetex> vetexIterarot = getVetexs();
        while (vetexIterarot.hasNext()){
            Vetex vetex = vetexIterarot.next();
            vetex.resetStates();
        }
    }


    /**
     * 求顶点v到其他顶点的最短路径
     * Page377 figure9-31
     * @param v
     * @return
     */
    public Iterator<Path> shortPaths(Vetex v) {
        LinkedList<Path> pathList = new LinkedList<Path>();
        resetVetexStates();     //重置顶点的信息，visited以及applicationInfo

        //为每个顶点的applicationInfo设置初始Path信息
        Iterator<Vetex> vetexIterator = getVetexs();
        while (vetexIterator.hasNext()){
            Vetex u = vetexIterator.next();
            //每一个顶点的初始path就是有没有边可以直接到
            int distance = Integer.MAX_VALUE;
            Edge edge = edgeFromTo(v,u);
            if (edge!=null) {distance = edge.getWeight();}
            if(u == v) {distance = 0;}
            Path p = new Path(distance,v,u);
            setPath(u,p); //现将每一个顶点的applcationINfo设置好
            System.out.println("初始path："+p.toString());
            System.out.println("=====================================");
        }

        //保证每一个点都可以被放入到最终的path集合中（无论可不可达）
        for (int i=0;i<getVetexNum();i++){
            //找到路径最短而且没有被更新过的顶点
            Vetex minVetex = findMinDistance(getVetexs());
            int minDis = getDistance(minVetex);
            System.out.println("在iterator中取出的最短距离的顶点："+minVetex.getInformatin());
            minVetex.setVisited(true);
            pathList.add(getPath(minVetex));    //加入到最短路径集合中

            //对临接的顶点进行更新
            Iterator<Vetex> adjVetexs = adjacentVetexs(minVetex);
            while (adjVetexs.hasNext()){
                Vetex adjVet = adjVetexs.next();
                /*
                如果这点顶点已经被访问过了，说明经历过找最小的操作了，那就不会有比之前更小的了，
                为了减少遍历次数，所以增加判断，看是否被访问过了。
                 */
                if (!adjVet.isVisited()){
                    Edge adje = edgeFromTo(minVetex,adjVet);
                    System.out.println(minVetex.getInformatin()+"--"+adjVet.getInformatin()+"距离："+adje.getWeight());

                    /*
                    因为所有顶点都要遍历一遍，所以为了避免给最大值加一个正数溢出的问题，导致不可达的顶点距离出现负数。
                     */
                    if (minDis!=Integer.MAX_VALUE & getDistance(adjVet) > minDis+adje.getWeight()){
                        setDistance(adjVet,getDistance(minVetex)+adje.getWeight());
                        updatePath(adjVet,minVetex);
                    }
                }
            }
        }
        return pathList.iterator();
    }

    /**
     *
     * @param v
     * @return
     */
    public Iterator<Path> dijkstra(Vetex v){
        Queue<Vetex> pathDeque = new LinkedList<Vetex>();
        LinkedList<Path> pathLinkedList = new LinkedList<Path>();
        resetVetexStates();     //重置顶点的信息，visited以及applicationInfo

        //为每个顶点的applicationInfo设置Path信息
        Iterator<Vetex> vetexIterator = getVetexs();
        while (vetexIterator.hasNext()){
            Vetex u = vetexIterator.next();
            Path p = new Path(Integer.MAX_VALUE,v,u);
            setPath(u,p);
            pathLinkedList.add(getPath(u));
        }

        //将起始结点的状态改变
        setDistance(v,0);
        pathDeque.add(v);
        v.setVisited(true);
        pathLinkedList.add(getPath(v));

        while (!pathDeque.isEmpty()){
            Vetex tempVetex = pathDeque.poll();
            tempVetex.setVisited(false);
            Iterator<Vetex> adjVetexs = adjacentVetexs(tempVetex);
            while (adjVetexs.hasNext()){
                Vetex adjvetex = adjVetexs.next();
                Edge e = edgeFromTo(tempVetex,adjvetex);  //一定有这条边
                int newDis = getDistance(tempVetex)+e.getWeight();
                //发现更短路径，更新
                if (getDistance(adjvetex) > newDis){
                    setDistance(adjvetex,newDis); //更新adjvetex结点的最短距离以及路径
                    updatePath(adjvetex,tempVetex);

                    //为什么让结点只能进一次Queue，首先说明该结点可以走通，另外不能循环走。
                    if(!adjvetex.isVisited()){
                        pathDeque.offer(adjvetex);
                        adjvetex.setVisited(true);
                    /*
                     * 之所以这块可以得到正确的答案是因为装到pathLinkedList是引用，所以上一个
                     * if (getDistance(adjvetex) > newDis)会改变已经进入到pathLinkedList
                     * 中的vetex中的path。
                     */

//                        System.out.println("临时："+getPath(adjvetex).toString());
                    }
                }

            }
        }
        return pathLinkedList.iterator();
    }

    /**
     * 使用desVex中最短距离的路径对iniVex结点进行更新
     * @param iniVex
     * @param desVex
     */
    protected void updatePath(Vetex iniVex,Vetex desVex){
        /**
         * 使用desVex更新iniVex的最短路径信息
         */
        Iterator<Vetex> desVexList = getPath(desVex).getPathList().iterator();
        getPath(iniVex).cleanPathList();
        while (desVexList.hasNext()){
            getPath(iniVex).addPath(desVexList.next());
        }

        getPath(iniVex).addPath(desVex);


    }

    /**
     * 从所有的vextex中找出没有被遍历过的距离最小的顶点
     * @param iterator
     * @return
     */
    protected Vetex findMinDistance(Iterator<Vetex> iterator){
        Vetex minVetex = null;
        while (iterator.hasNext()){
            Vetex v = iterator.next();
            if (!v.isVisited()){
                minVetex = v;
                break;
            }
        }
        while (iterator.hasNext()){
            Vetex v = iterator.next();
            if(!v.isVisited() && getDistance(v)<getDistance(minVetex)){
                minVetex = v;
            }
        }
        return minVetex;
    }


    /**
     * Vextex中的成员变量applicationInfo可以用于承装一些信息，
     * 当求去某一个结点的最短路径的时候，将该成员变量设置为Path。
     * @param v
     * @param path
     */
    protected void setPath(Vetex v,Path path){
        v.setApplicationInfo(path);
    }

    protected Path getPath(Vetex v){
        return (Path) v.getApplicationInfo();
    }

    /**
     * Path中包含当前结点到初始结点的距离
     * @param v
     * @param distance
     */
    protected void setDistance(Vetex v,int distance){
        getPath(v).setDistance(distance);
    }

    protected int getDistance(Vetex v){
        return getPath(v).getDistance();
    }

}
