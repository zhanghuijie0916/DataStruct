package org.sunny.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sunny.exception.UnsupportOperation;
import org.sunny.graph.*;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class DirectGraphTest {
//    private IGraph graph1;
    private IGraph graph2;
    private IGraph graph3;
    @Before
    public void setUp() throws Exception {
        /*graph1 = new DirectGraph(1);
        //顶点集合
        Vetex g1v1 = new Vetex(graph1,"v1");
        Vetex g1v2 = new Vetex(graph1,"v2");
        Vetex g1v3 = new Vetex(graph1,"v3");
        Vetex g1v4 = new Vetex(graph1,"v4");
        Vetex g1v5 = new Vetex(graph1,"v5");
        Vetex g1v6 = new Vetex(graph1,"v6");
        Vetex g1v7 = new Vetex(graph1,"v7");
        //边集合
        Edge g1e12 = new Edge(graph1,g1v1,g1v2,1,"e12");
        Edge g1e13 = new Edge(graph1,g1v1,g1v3,1,"e13");
        Edge g1e14 = new Edge(graph1,g1v1,g1v4,1,"e14");
        Edge g1e24 = new Edge(graph1,g1v2,g1v4,1,"e24");
        Edge g1e25 = new Edge(graph1,g1v2,g1v5,1,"e25");
        Edge g1e36 = new Edge(graph1,g1v3,g1v6,1,"e36");
        Edge g1e43 = new Edge(graph1,g1v4,g1v3,1,"e43");
        Edge g1e46 = new Edge(graph1,g1v4,g1v6,1,"e46");
        Edge g1e47 = new Edge(graph1,g1v4,g1v7,1,"e47");
        Edge g1e54 = new Edge(graph1,g1v5,g1v4,1,"e54");
        Edge g1e57 = new Edge(graph1,g1v5,g1v7,1,"e57");
        Edge g1e76 = new Edge(graph1,g1v7,g1v6,1,"e76");

*/
        graph2 = new DirectGraph(1);
        //顶点集合
        Vetex g2v1 = new Vetex(graph2,"v1");
        Vetex g2v2 = new Vetex(graph2,"v2");
        Vetex g2v3 = new Vetex(graph2,"v3");
        Vetex g2v6i = new Vetex(graph2,"v6'");
        Vetex g2v6 = new Vetex(graph2,"v6");
        Vetex g2v4 = new Vetex(graph2,"v4");
        Vetex g2v5 = new Vetex(graph2,"v5");
        Vetex g2v7i = new Vetex(graph2,"v7'");
        Vetex g2v7 = new Vetex(graph2,"v7");
        Vetex g2v8i = new Vetex(graph2,"v8'");
        Vetex g2v8 = new Vetex(graph2,"v8");
        Vetex g2v9 = new Vetex(graph2,"v9");
        Vetex g2v10i = new Vetex(graph2,"v10'");
        Vetex g2v10 = new Vetex(graph2,"v10");
        //边集合
        Edge g2e12 = new Edge(graph2,g2v1,g2v2,3,"A");
        Edge g2e13 = new Edge(graph2,g2v1,g2v3,2,"B");
        Edge g2e26i = new Edge(graph2,g2v2,g2v6i,0,"e26'");
        Edge g2e36i = new Edge(graph2,g2v3,g2v6i,0,"e36'");
        Edge g2e24 = new Edge(graph2,g2v2,g2v4,3,"C");
        Edge g2e6i6 = new Edge(graph2,g2v6i,g2v6,2,"D");
        Edge g2e35 = new Edge(graph2,g2v3,g2v5,1,"E");
        Edge g2e47i = new Edge(graph2,g2v4,g2v7i,0,"e47'");
        Edge g2e67i = new Edge(graph2,g2v6,g2v7i,0,"e67'");
        Edge g2e68i = new Edge(graph2,g2v6,g2v8i,0,"e68'");
        Edge g2e58i = new Edge(graph2,g2v5,g2v8i,0,"e58'");
        Edge g2e59 = new Edge(graph2,g2v5,g2v9,4,"K");
        Edge g2e7i7 = new Edge(graph2,g2v7i,g2v7,3,"e7'7");
        Edge g2e8i8 = new Edge(graph2,g2v8i,g2v8,2,"G");
        Edge g2e710i = new Edge(graph2,g2v7,g2v10i,0,"e710'");
        Edge g2e810i = new Edge(graph2,g2v8,g2v10i,0,"e810'");
        Edge g2e910i = new Edge(graph2,g2v9,g2v10i,0,"e910'");
        Edge g2e10i10 = new Edge(graph2,g2v10i,g2v10,1,"H");


        graph3 = new DirectGraph(1);
        //顶点集合
        Vetex g3vs = new Vetex(graph3,"s");
        Vetex g3va = new Vetex(graph3,"a");
        Vetex g3vb = new Vetex(graph3,"b");
        Vetex g3vc = new Vetex(graph3,"c");
        Vetex g3vd = new Vetex(graph3,"d");
        Vetex g3vt = new Vetex(graph3,"t");

        //边集合
        Edge g3esa = new Edge(graph3,g3vs,g3va,3,"Esa");
        Edge g3esb = new Edge(graph3,g3vs,g3vb,2,"Esb");
        Edge g3eab = new Edge(graph3,g3va,g3vb,1,"Eab");
        Edge g3eac = new Edge(graph3,g3va,g3vc,3,"Eac");
        Edge g3ead = new Edge(graph3,g3va,g3vd,4,"Ead");
        Edge g3ebd = new Edge(graph3,g3vb,g3vd,2,"Ebd");
        Edge g3ect = new Edge(graph3,g3vc,g3vt,2,"Ect");
        Edge g3edt = new Edge(graph3,g3vd,g3vt,3,"Edt");


    }

    @Ignore
    public void topLogicalSort() throws Exception {
        //有向无环图拓扑排序
        Iterator<Vetex> top_iterator = graph2.topLogicalSort();
        System.out.println("\n");
        while (top_iterator.hasNext()) {
            System.out.println(top_iterator.next().getInformatin());
        }

    }

    @Ignore
    public void criticalPath() throws Exception {
        LinkedList<Edge> criticalPathList = graph2.criticalPath();  //求取有向无圈图的最短路径
        Iterator<Edge> criticalEdgeIterator = criticalPathList.iterator();

        System.out.println("\n"+"关键路径为：");
        while (criticalEdgeIterator.hasNext()){
            Edge edge = criticalEdgeIterator.next();
            System.out.print(edge.getEdgeINfo()+"-----");
        }
        System.out.println("\n");

        //查看每一个结点的最早、最晚的完成时间
        Iterator<Vetex> vetexIterator = graph2.getVetexs();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            Object o = v.getApplicationInfo();
            if (o instanceof VTime){
                VTime time = (VTime)o;
                System.out.println(v.getInformatin()+" : "+time.toString());
            }
        }
    }

    @Test
    public void maximumFlow() throws Exception{
        graph3.maximumFlow();


        Iterator<Edge> edgeIterator = graph3.getEdges();
        while (edgeIterator.hasNext()){
            System.out.println(edgeIterator.next().toString());
        }

    }



}