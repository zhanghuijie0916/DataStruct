package org.sunny.graph;

import com.sun.deploy.panel.ITreeNode;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class UndirectGraphTest {
    private UndirectGraph graph1;
    private Vetex g1v1;
    private Vetex g1v2;

    @Before
    public void setUp() throws Exception {
        graph1 = new UndirectGraph(0);
        //顶点集合
        g1v1 = new Vetex(graph1,"v1");
        g1v2 = new Vetex(graph1,"v2");
        Vetex g1v3 = new Vetex(graph1,"v3");
        Vetex g1v4 = new Vetex(graph1,"v4");
        Vetex g1v5 = new Vetex(graph1,"v5");
        Vetex g1v6 = new Vetex(graph1,"v6");
        Vetex g1v7 = new Vetex(graph1,"v7");
        //边集合
        Edge g1e12 = new Edge(graph1,g1v1,g1v2,2,"e12");
        Edge g1e13 = new Edge(graph1,g1v1,g1v3,4,"e13");
        Edge g1e14 = new Edge(graph1,g1v1,g1v4,1,"e14");
        Edge g1e24 = new Edge(graph1,g1v2,g1v4,3,"e24");
        Edge g1e25 = new Edge(graph1,g1v2,g1v5,10,"e25");
        Edge g1e34 = new Edge(graph1,g1v3,g1v4,2,"e34");
        Edge g1e36 = new Edge(graph1,g1v3,g1v6,5,"e36");
        Edge g1e45 = new Edge(graph1,g1v4,g1v5,7,"e45");
        Edge g1e46 = new Edge(graph1,g1v4,g1v6,8,"e46");
        Edge g1e47 = new Edge(graph1,g1v4,g1v7,4,"e47");
        Edge g1e57 = new Edge(graph1,g1v5,g1v7,6,"e57");
        Edge g1e67 = new Edge(graph1,g1v6,g1v7,1,"e67");
    }

    /**
     * 测试删除边
     * @throws Exception
     */
    @Ignore
    public void remove() throws Exception {
        Iterator<Edge> edgeIterator = graph1.getEdges();
        while (edgeIterator.hasNext()){
            System.out.println(edgeIterator.next().toString());
        }

        System.out.println("\n\n");
        graph1.remove(graph1.edges.getFirst().getData());

        Iterator<Edge> edgeIterator1 = graph1.getEdges();
        while (edgeIterator1.hasNext()){
            System.out.println(edgeIterator1.next().toString());
        }
    }

    /**
     * 测试删除顶点
     * @throws Exception
     */
    @Ignore
    public void remove1() throws Exception {
        Iterator<Vetex> vetexIterator = graph1.getVetexs();
        while (vetexIterator.hasNext()){
            System.out.println("-----"+vetexIterator.next().getInformatin()+"-----");
        }

        System.out.println("\n\n");
        graph1.remove(graph1.vetexs.getFirst().getData());

        Iterator<Vetex> vetexIterator1 = graph1.getVetexs();
        while (vetexIterator1.hasNext()){
            System.out.println("-----"+vetexIterator1.next().getInformatin()+"-----");
        }
    }

    @Ignore
    public void edgeFromTo() throws Exception {
        Edge edge = graph1.edgeFromTo(g1v1,g1v2);
        System.out.println(edge);
    }

    @Ignore
    public void adjacentVetexs() throws Exception {
        Iterator<Vetex> adjVetexIterator = graph1.adjacentVetexs(g1v1);
        while (adjVetexIterator.hasNext()){
            System.out.println("===="+adjVetexIterator.next().getInformatin()+"====");
        }
    }

    /**
     * 最小生成树
     * @throws Exception
     */
    @Test
    public void generateMST() throws Exception {
        graph1.generateMST();
        Iterator<Edge> edgeIterator = graph1.getEdges();
        while (edgeIterator.hasNext()){
            System.out.println(edgeIterator.next().toString());
        }
    }

    /**
     * 判断是否为连通图
     * @throws Exception
     */
    @Ignore
    public void isConnectedGraph() throws Exception {
        graph1.isConnectedGraph();
        /*
        Iterator<Vetex> vetexIterator = graph1.getVetexs();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            System.out.println("-----"+v.getInformatin()+"-----"+v.isVisited());
        }*/
    }


}