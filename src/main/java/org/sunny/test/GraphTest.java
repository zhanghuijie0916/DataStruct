
package org.sunny.test;
import org.sunny.exception.*;
import org.sunny.graph.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphTest {

    public static void main(String[] args){
        IGraph graph = new DirectGraph(1);
        //顶点集合
        Vetex v1 = new Vetex(graph,"v1");
        Vetex v2 = new Vetex(graph,"v2");
        Vetex v3 = new Vetex(graph,"v3");
        Vetex v4 = new Vetex(graph,"v4");
        Vetex v5 = new Vetex(graph,"v5");
        Vetex v6 = new Vetex(graph,"v6");
        Vetex v7 = new Vetex(graph,"v7");
        //边集合

        Edge e12 = new Edge(graph,v1,v2,2,"e12");
        Edge e14 = new Edge(graph,v1,v4,-1,"e14");
        Edge e24 = new Edge(graph,v2,v4,3,"e24");
        Edge e25 = new Edge(graph,v2,v5,10,"e25");
        Edge e31 = new Edge(graph,v3,v1,4,"e31");
        Edge e36 = new Edge(graph,v3,v6,5,"e36");
        Edge e43 = new Edge(graph,v4,v3,2,"e43");
        Edge e45 = new Edge(graph,v4,v5,2,"e45");
        Edge e46 = new Edge(graph,v4,v6,6,"e46");
        Edge e47 = new Edge(graph,v4,v7,4,"e47");
        Edge e57 = new Edge(graph,v5,v7,6,"e57");
        Edge e76 = new Edge(graph,v7,v6,1,"e76");

//        try {
//              //有向无环图拓扑排序
//            Iterator<Vetex> top_iterator = graph.topLogicalSort();
//            while (top_iterator.hasNext()){
//                System.out.println(top_iterator.next().getInformatin());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //最短路径
        Iterator<Path> iterator = graph.shortPaths(v1);
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

        Iterator<Path> iterator1 = graph.dijkstra(v1);
        while (iterator1.hasNext()){
            System.out.println(iterator1.next().toString());
        }

    }


}
