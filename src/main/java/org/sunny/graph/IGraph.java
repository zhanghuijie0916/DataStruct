package org.sunny.graph;

import java.util.Iterator;
import java.util.LinkedList;

import org.sunny.exception.*;

public interface IGraph {
    public static final int UndirectedGraph = 0; //无向图
    public static final int DirectedGraph = 1;  //有项图

    //返回图的类型
    public abstract int getType();

    //graph中顶点总数
    public abstract int getVetexNum();

    //边的总数
    public abstract int getEdgeNum();

    //返回图中所有顶点
    public abstract Iterator getVetexs();

    //返回途中所有边
    public abstract Iterator getEdges();

    //删除一个顶点V
    public abstract void remove(Vetex vetex);

    //删除一条边
    public abstract void remove(Edge edge);

    //添加一个顶点
    public abstract INode insert(Vetex vetex);

    //添加一条边
    public abstract INode insert(Edge edge);

    //判断两个顶点u和v是否连接，即是否存在u->v
    public abstract boolean isAdjacent(Vetex u,Vetex v);

    //返回是否有从顶点u到顶点v的边，返回连接的边
    public abstract Edge adjacentEdge(Vetex u,Vetex v);

    //返回从某一个顶点出发，可以直接到达的临界点
    public abstract Iterator adjacentVetexs(Vetex vetex);

    //对图进行深度优先遍历
    public abstract Iterator dfsTraverse(Vetex v);

    //对图进行广度优先遍历
    public abstract Iterator bfsTraverse(Vetex v);

    //从顶点v到其他顶点的最短路径
    public abstract Iterator<Path> shortPaths(Vetex v);

    //使用贪婪算法求去从某一个顶点出发到其余顶点的最短路径
    public abstract Iterator<Path> dijkstra(Vetex v);

    //无项图的最小生成树，有向图不支持
    public abstract void generateMST() throws UnsupportOperation;

    //有项无圈图的拓扑排序，无向图不支持此操作
    public abstract Iterator topLogicalSort() throws UnsupportOperation,CircularGraphException;

    //有项无圈图的关键路径，无向图不支持
    public abstract LinkedList<Edge> criticalPath() throws UnsupportOperation,CircularGraphException;

    public abstract int maximumFlow() throws UnsupportOperation;
}
