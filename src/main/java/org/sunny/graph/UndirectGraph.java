package org.sunny.graph;

import org.sunny.exception.CircularGraphException;
import org.sunny.exception.UnsupportOperation;
import sun.security.x509.EDIPartyName;

import java.util.Iterator;
import java.util.LinkedList;

public class UndirectGraph extends AbstractGraph {
    public UndirectGraph(int graphType){
        super(graphType);
    }

    /**
     * 删除某一个结点
     * @param vetex
     */
    public void remove(Vetex vetex) {
       while (vetex.getDegree() > 0){
           Edge edge = vetex.getAdjacentEdges().getFirst().getData();
           remove(edge);
       }
       vetexs.removeNode(vetex.getVexPosition());

    }

    /**
     * 删除一条边(包括两端的结点的临接边中删除边，包括从图中的边队列中删除边)
     * @param edge
     */
    public void remove(Edge edge) {
        Vetex v1 = edge.getFirstVetex();
        Vetex v2 = edge.getSecondVetex();
        v1.getAdjacentEdges().removeNode(edge.getEdgePosition());
        v2.getAdjacentEdges().removeNode(edge.getEdgePosition());
        this.edges.removeNode(edge.getEdgePosition());

    }

    /**
     * 有向图求最大流
     * @return
     * @throws UnsupportOperation
     */
    public int maximumFlow() throws UnsupportOperation {
        throw new UnsupportOperation("无项图不支持此操作");
    }

    /**
     * 返回两个顶点之间的边
     * @param v1
     * @param v2
     * @return v1、v2之间不连接返回null
     */
    protected Edge edgeFromTo(Vetex v1, Vetex v2) {
        INodeLinkedList<INode<Edge>> edgeList = v1.getAdjacentEdges();
        Iterator<INode<Edge>> edgeIterator = edgeList.iterator();
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next().getData();
            if ((v2==edge.getSecondVetex()) | (v2==edge.getFirstVetex())){
                return edge;
            }
        }
        return null;
    }

    /**
     * 返回某一个顶点的直接联通顶点
     * @param vetex
     * @return
     */
    public Iterator<Vetex> adjacentVetexs(Vetex vetex) {
        LinkedList<Vetex> result = new LinkedList<Vetex>();
        INodeLinkedList<INode<Edge>> edgeList = vetex.getAdjacentEdges();
        Iterator<INode<Edge>> edgeIterator = edgeList.iterator();
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next().getData();
            Vetex firstVetex = edge.getFirstVetex();
            Vetex secondVetex = edge.getSecondVetex();
            if (firstVetex != vetex) result.add(firstVetex);
            else result.add(secondVetex);
        }
        return result.iterator();
    }

    public Iterator<Vetex> topLogicalSort() throws UnsupportOperation, CircularGraphException {
        throw new UnsupportOperation("无向图不支持此操作");
    }

    public LinkedList<Edge> criticalPath() throws UnsupportOperation, CircularGraphException {
        throw new UnsupportOperation("无向图不支持此操作");
    }

    /**
     * 求无向图的最小生成树（prim算法），前提是无项图是连通的，算法不判断图的连通性
     * 连通：如果再无向图中，每一个顶点到其他顶点都存在一条路径，则称其是连通的。
     * @throws UnsupportOperation
     */
    public void generateMST() throws UnsupportOperation {
        Iterator<Vetex> vetexIterator = getVetexs();
        //如果图中没有顶点，也会返回false
        if (!isConnectedGraph())
            throw new UnsupportOperation("不是连通图");

        resetVetexStates();
        resetEdgeStates();

        Vetex startVetex = vetexIterator.next();
        startVetex.setVisited(true);
        //初始化
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            Edge edge = edgeFromTo(startVetex,v);
            setCrossEdge(v,edge);
        }

        //已经有第一个点了startVetex
        for (int i=1;i<getVetexNum();i++){
            Vetex minVetex = selectMinVetex(getVetexs());
            minVetex.setVisited(true);
            Edge adjEdge = getCrossEdge(minVetex);
            if (adjEdge!=null) adjEdge.setToMST();

            Iterator<Vetex> adjVetexs = adjacentVetexs(minVetex);
            while (adjVetexs.hasNext()){
                Vetex adjVetex = adjVetexs.next();
                Edge edge = edgeFromTo(minVetex,adjVetex);
                if (edge.getWeight() < getCrossWeight(adjVetex)){
                    setCrossEdge(adjVetex,edge);
                }
            }
        }
    }

    /**
     * applicationInfo中是边的信息，选择边权重小的那个顶点返回
     * @param vetexIterator
     * @return
     */
    protected Vetex selectMinVetex(Iterator<Vetex> vetexIterator){
        Vetex minVetex = null;
        while (vetexIterator.hasNext()){
            Vetex vetex = vetexIterator.next();
            if (!vetex.isVisited()){
                minVetex = vetex;
                break;
            }
        }

        while (vetexIterator.hasNext()){
            Vetex u = vetexIterator.next();
            if (!u.isVisited() & getCrossWeight(minVetex)>getCrossWeight(u)){
                minVetex = u;
            }
        }
        return minVetex;
    }

    protected void setCrossEdge(Vetex vetex,Edge edge){
        vetex.setApplicationInfo(edge);
    }

    protected Edge getCrossEdge(Vetex vetex){
        return (Edge)vetex.getApplicationInfo();
    }

    protected int getCrossWeight(Vetex vetex){
        Edge edge = getCrossEdge(vetex);
        if (edge!=null) return edge.getWeight();
        else return Integer.MAX_VALUE;
    }


    /**
     * 判断图是否是连通图
     * @return
     * @throws UnsupportOperation
     */
    protected boolean isConnectedGraph() throws UnsupportOperation{
        Iterator<Vetex> vetexIterator = getVetexs();
        while (vetexIterator.hasNext()){
            //遍历一个新的顶点之前，重置访问信息，都设为没有访问过
            resetVetexStates();
            Vetex currentVetex = vetexIterator.next();
            System.out.println("======="+currentVetex.getInformatin()+"=======");
            int count = searchFromVetex(currentVetex);  //查看当前的顶点是否可以到达其他顶点

            if(count!=7) return false;
            else return true;

            /*Iterator<Vetex> iterator = getVetexs();
            while (iterator.hasNext()){
                Vetex u = iterator.next();
                System.out.println("-----"+u.getInformatin()+"-----"+u.isVisited());
                if (!u.isVisited()){
                    return false;
                    //throw new UnsupportOperation("不连通");
                }
            }*/
            //System.out.println("==========================================");
        }
        //图为空
        return false;
    }


    /**
     * 从某一顶点遍历
     * @param vetex
     * @return
     */
    protected int searchFromVetex(Vetex vetex){
        int count = 1;
        vetex.setVisited(true);
        Iterator<Vetex> adjVetexs = adjacentVetexs(vetex);

        //标记下一个最小边权重的顶点
        while (adjVetexs.hasNext()){
            Vetex adjVetex = adjVetexs.next();
            if (!adjVetex.isVisited()){
                count += searchFromVetex(adjVetex);
            }
        }
        System.out.println("计数结果="+count);
        return count;
    }







}
