
package org.sunny.graph;
import java.util.*;

import jdk.nashorn.internal.ir.WhileNode;
import org.sunny.exception.*;
import sun.security.x509.EDIPartyName;

public class DirectGraph extends AbstractGraph {
    public DirectGraph(int graphType){
        super(graphType);
    }


    /**
     * 删除一个顶点（删除所有入度的边；删除所有出度的边；从图中删除该顶点）
     * @param vetex
     */
    public void remove(Vetex vetex) {
        while (vetex.getOutDegree()>0){
            INode node = (INode)(vetex.getAdjacentEdges().getFirst());
            Edge e = (Edge) node.getData();
            remove(e);
        }
        while (vetex.getInDegree()>0){
            INode node = (INode)(vetex.getReAdjacentEdges().getFirst());
            Edge e = (Edge) node.getData();
            remove(e);
        }
        vetexs.removeNode(vetex.getVexPosition());
    }

    /**
     * 删除有向边
     * @param edge
     */
    public void remove(Edge edge) {
        edges.removeNode(edge.getEdgePosition());  //从边表中删除该边
        Vetex first = edge.getFirstVetex();
        first.getAdjacentEdges().removeNode(edge.getEdgePosition());
        Vetex second = edge.getSecondVetex();
        second.getReAdjacentEdges().removeNode(edge.getEdgePosition());
    }

    /**
     * 返回从v1至v2的边
     * @param v1
     * @param v2
     * @return
     */
    protected Edge edgeFromTo(Vetex v1, Vetex v2) {
        INodeLinkedList<INode<Edge>> list = v1.getAdjacentEdges();
        Iterator<INode<Edge>> iterator = list.iterator();
        while (iterator.hasNext()){
            Edge e = iterator.next().getData();
            if (v2 == e.getSecondVetex()) {
                return e;
            }
        }
        return null;
    }

    /**
     * 返回从v出发可以直接到达的临接顶点
     * 使用的是LinkedList
     * @param v
     * @return
     */
    public Iterator<Vetex> adjacentVetexs(Vetex v) {
        LinkedList<Vetex> result = new LinkedList<Vetex>();
        INodeLinkedList<INode<Edge>> list = v.getAdjacentEdges();
        Iterator<INode<Edge>> iterator = list.iterator();
        while (iterator.hasNext()){
            Edge edge = iterator.next().getData();
            System.out.println("连接边的信息："+edge.toString());
            result.add(edge.getSecondVetex());
        }
        return result.iterator();
    }

    /**
     * 求无项图的最小生成树，有向图不可以
     * @throws UnsupportOperation
     */
    public void generateMST() throws UnsupportOperation {
        throw new UnsupportOperation("有向图不支持此操作");
    }

    /**
     * 有向无圈图的拓扑排序
     * @return LinkedList<Vetex>
     * @throws UnsupportOperation
     */
    @Override
    public Iterator<Vetex> topLogicalSort() throws UnsupportOperation,CircularGraphException {
        LinkedList<Vetex> result = new LinkedList();
        Stack<Vetex> vetexStack = new Stack<Vetex>(); //临时存放入度为0的顶点
        //将入度存储在applicationInfo成员变量中
        Iterator<Vetex> vetexIterator = getVetexs();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            v.setApplicationInfo(Integer.valueOf(v.getInDegree()));
            System.out.println("顶点"+v.getInformatin()+"入度为："+v.getInDegree());
            if (v.getInDegree() == 0){
                vetexStack.push(v);
                System.out.println("入度为0的顶点："+v.getInformatin());
            }
        }

        while (!vetexStack.empty()){
            Vetex v = vetexStack.pop();
            result.add(v);
            Iterator<Vetex> vlist = adjacentVetexs(v);
            while (vlist.hasNext()){
                Vetex u = vlist.next();
                int u_inDeg = getTopInDeg(u)-1;
                //如果某一个点的入度变为0，就把它推进Stack中
                if (u_inDeg == 0)
                    vetexStack.push(u);
                setTopInDeg(u,u_inDeg);
                System.out.println("顶点"+u.getInformatin()+"入度变为："+getTopInDeg(u));
            }
        }
        if (result.size()<getVetexNum())
            throw  new CircularGraphException("有环图，无法进行拓扑排序");
        else
        return result.iterator();
    }

    //=========================topLogicalSort使用的方法=======================================
    /**
     * 在vetex成员变量applicationInfo中临时记录该顶点的入度信息
     * @param v
     * @return
     */
    private int getTopInDeg(Vetex v){
        return ((Integer)v.getApplicationInfo()).intValue();
    }

    /**
     * 拓扑遍历时重新设置该顶点的入度信息
     * @param v
     * @param inDeg
     */
    private void setTopInDeg(Vetex v,int inDeg){
        v.setApplicationInfo(Integer.valueOf(inDeg));
    }
    //========================================================================================


    /**
     * 有向无圈图关键路径，P383
     * @return
     * @throws UnsupportOperation
     * @throws CircularGraphException
     */
    public LinkedList<Edge> criticalPath() throws UnsupportOperation,CircularGraphException {
        resetEdgeStates();  //讲各边的信息设置为normal

        LinkedList<Vetex> reTopList = new LinkedList<Vetex>();
        LinkedList<Vetex> topList = new LinkedList<Vetex>();
        LinkedList<Edge> criticalPathList = new LinkedList<Edge>();

        Iterator<Vetex> iniIterator = topLogicalSort();
        if (iniIterator == null) return null;
        //初始化每一个vetex的applicationInfo
        while (iniIterator.hasNext()){
            Vetex vetex = iniIterator.next();
            VTime vTime = new VTime();
            vetex.setApplicationInfo(vTime);
            reTopList.addFirst(vetex);  //生成逆向拓扑序列
            topList.add(vetex);  //生成正向拓扑排序
            System.out.println(getEarlyTime(vetex)+"====="+getLateTime(vetex));
        }

        //设置vetex的最早完成时间
        Iterator<Vetex> topIterator = topList.iterator();
        while (topIterator.hasNext()){
            Vetex vetex = topIterator.next();
            Iterator<Vetex> adjvetexs = adjacentVetexs(vetex);
            while (adjvetexs.hasNext()){
                Vetex adjVetex = adjvetexs.next();
                Edge adjEdge = edgeFromTo(vetex,adjVetex);
                int temp_earlytime = getEarlyTime(vetex)+adjEdge.getWeight();
                if(temp_earlytime>getEarlyTime(adjVetex)){
                    setEarlyTime(adjVetex,temp_earlytime);
                }
            }
        }

        //设置vetex最晚完成时间
        Iterator<Vetex> reTopIterator = reTopList.iterator();
        Vetex destVetex = reTopIterator.next();
        setLaterTime(destVetex,getEarlyTime(destVetex));  //将终结点的最晚完成时间设置为其最早完成时间
        while (reTopIterator.hasNext()){
            Vetex vetex = reTopIterator.next();
            Iterator<Vetex> adjVetexs = adjacentVetexs(vetex);
            while (adjVetexs.hasNext()){
                Vetex adjVetex = adjVetexs.next();
                Edge adjEdge = edgeFromTo(vetex,adjVetex);
                int temp_latetime = getLateTime(adjVetex)-adjEdge.getWeight();
                if(temp_latetime < getLateTime(vetex)){
                    setLaterTime(vetex,temp_latetime);
                }
            }
        }

        //标记关键事件
        Iterator<Edge> edgeIterator = getEdges();
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next();
            Vetex firstVetex = edge.getFirstVetex();
            Vetex secondVetex = edge.getSecondVetex();
            if (getLateTime(secondVetex)-getEarlyTime(firstVetex)-edge.getWeight() == 0){
                edge.setToCritical();
                criticalPathList.add(edge);
            }
        }

        return criticalPathList;
    }

    //=====================获取设置VTime信息的基础方法（criticalPath中方法）====================
    private int getEarlyTime(Vetex v){
        return ((VTime)v.getApplicationInfo()).getEarlyTime();
    }

    private void setEarlyTime(Vetex v,int eTime){
        ((VTime)v.getApplicationInfo()).setEarlyTime(eTime);
    }

    private int getLateTime(Vetex v){
        return ((VTime)v.getApplicationInfo()).getLateTime();
    }

    private void setLaterTime(Vetex v,int lTime){
        ((VTime)v.getApplicationInfo()).setLateTime(lTime);
    }
    //=====================================================================================


    public int maximumFlow() throws UnsupportOperation {
        LinkedList<Vetex> sourcelist = getSourceVetex();
        LinkedList<Vetex> sinklist = getSinkVetex();
        if (sourcelist.size()!=1 & sinklist.size()!=1){
            throw new UnsupportOperation("没有发点或没有收点，不合法！");
        }
        Vetex sourceVetex = sourcelist.getFirst();  //发点
        Vetex sinkVetex = sinklist.getFirst();  //收点
        System.out.println("发点："+sourceVetex.getInformatin());
        System.out.println("收点："+sinkVetex.getInformatin());


        //得到最大路径（正值）,如果没有路，返回null
        LinkedList<Edge> maxFlowPath = null;
        while ((maxFlowPath = getMaxFlowPath(sourceVetex,sinkVetex)) != null){
            updateEdges(maxFlowPath);  //更新当前边，并增加反向边

            Iterator<Edge> iterator = getEdges();
            //测试一下是否真的删除边权重为0的结点
            while (iterator.hasNext()){
                System.out.println("测试："+iterator.next());
            }
        }
        return 0;
    }


    /**
     * Figure9-46中的方法，当某一条边减少多少权重之后，增加一条反向的边
     * @param maxFlowPath
     */
    protected void updateEdges(LinkedList<Edge> maxFlowPath){
        Integer flowWeight = getMinEdgeWeight(maxFlowPath.iterator());  //得到最小权重
        System.out.println("最小权重："+flowWeight);
        for (Edge edge : maxFlowPath){
            Vetex svetex = edge.getFirstVetex();
            Vetex evetex = edge.getSecondVetex();

            //反向边更新
            Edge reEdge = edgeFromTo(evetex,svetex);
            //判断是否已经存在反边
            if (reEdge == null)
                reEdge = new Edge(this,evetex,svetex,flowWeight,"RE"+edge.getEdgeINfo());
            else
                reEdge.setWeight(reEdge.getWeight()+flowWeight);

            //更改此边的权重
            edge.setWeight(edge.getWeight()-flowWeight);
            if (edge.getWeight()==0){
                remove(edge);
            }
        }
    }


    /**
     * 得到最小权重
     * @param edgeIterator
     * @return
     */
    protected Integer getMinEdgeWeight(Iterator<Edge> edgeIterator){
        Integer minWeight = null;
        if(edgeIterator.hasNext()){
            minWeight = edgeIterator.next().getWeight();
        }
        while (edgeIterator.hasNext()){
            Integer tempWeight = edgeIterator.next().getWeight();
            if (tempWeight < minWeight){
                minWeight = tempWeight;
            }
        }
        return minWeight;
    }

    /**
     * 找到两个顶点之间的最大流路径
     * @param startVetex
     * @param endVetex
     * @return
     */
    private LinkedList<Edge> getMaxFlowPath(Vetex startVetex,Vetex endVetex){
        resetVetexStates();

        //设置路径初始信息（距离startVetex的直达距离）
        Iterator<Vetex> vetexIterator = getVetexs();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            Edge edge = edgeFromTo(startVetex,v);
            int distance = -1;
            if (edge != null) distance = edge.getWeight();
            else if (startVetex == v) distance = 0;
            Path p = new Path(distance,startVetex,v);
            setPath(v,p);
        }

        //采用优先队列，搜索从起始点的最大路径
        Queue<Vetex> queue = new LinkedList<Vetex>();
        Vetex firstMaxVetex = findMaxDistance(getVetexs());
        System.out.println("第一个最大距离点："+firstMaxVetex.getInformatin()+"，距离="+getDistance(firstMaxVetex));
        firstMaxVetex.setVisited(true);
        queue.add(firstMaxVetex);

        /*
        更改目前最大距离的相邻点，初始的时候是距离startVetex的距离，如果第一个的最大距离就是-1，
        说明没有可通路径了，减少迭代次数。
         */
        while (!queue.isEmpty() & getDistance(firstMaxVetex)!=-1){
            Vetex maxVetex = queue.poll();
            Iterator<Vetex> adjVetexs = adjacentVetexs(maxVetex);

            while (adjVetexs.hasNext()){
                Vetex adjVetex = adjVetexs.next();
                Edge edge = edgeFromTo(maxVetex,adjVetex);
                int tempDistance = getDistance(maxVetex)+edge.getWeight();
                if (tempDistance > getDistance(adjVetex)){
                    setDistance(adjVetex,tempDistance);
                    updatePath(adjVetex,maxVetex);

                    /*避免有向有圈图，在圈中死循环。和AbstractGraph中找最小距离相比，不用加判断
                    距离是否等于-1的原因是，这只是找一条距离最大的无圈路径，不涉及遍历每一个点。
                    */
                    if (!adjVetex.isVisited()){
                        adjVetex.setVisited(true);
                        queue.add(adjVetex);
                    }
                }
            }
        }

        LinkedList<Edge> maxPath = new LinkedList<Edge>();  //盛放最大路径
        //如果endVetex没有pathList中是空，说明从startVetex不可达。
        LinkedList<Vetex> pathList = getPath(endVetex).getPathList();

        System.out.println();
        for (Vetex v :pathList){
            System.out.println("-----"+v.getInformatin());
        }

        //如果找不到结点间的最大路径，就返回null
        if ((!isAdjacent(startVetex,endVetex)) & pathList.isEmpty()){
            System.out.println("空");
            return null;
        }
        else {
            //将最大路径中盛装的结点信息转换成Edge
            pathList.addFirst(startVetex);
            pathList.addLast(endVetex);
            System.out.println();
            System.out.println("最大路径信息："+pathList.size());
            for (int i=0;i<pathList.size()-1;i++){
                Vetex u1 = pathList.get(i);
                Vetex u2 = pathList.get(i+1);
                Edge edge = edgeFromTo(u1,u2);
                if (edge!=null){
                    maxPath.add(edge);
                    System.out.println(edge.getEdgeINfo()+":weight="+edge.getWeight());
                }
            }
            return maxPath;
        }
    }

    /**
     * 返回最大距离
     * @param vetexIterator
     * @return
     */
    protected Vetex findMaxDistance(Iterator<Vetex> vetexIterator) {
        Vetex maxVetex = null;
        //判断顶点是否被访问过是因为如果有圈，就会有问题
        while (vetexIterator.hasNext()){
            maxVetex = vetexIterator.next();
            if (!maxVetex.isVisited()){
                maxVetex = vetexIterator.next();
                break;
            }
        }

        while (vetexIterator.hasNext()){
            Vetex tempVetex = vetexIterator.next();
            if (!tempVetex.isVisited() & getDistance(tempVetex) > getDistance(maxVetex)){
                maxVetex = tempVetex;
            }
        }
        return maxVetex;
    }


    /**
     * 获取发点
     * @return
     */
    private LinkedList<Vetex> getSourceVetex(){
        Iterator<Vetex> vetexIterator = getVetexs();
        LinkedList<Vetex> sourceList = new LinkedList<Vetex>();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            int inDeg = v.getInDegree();
            if (inDeg == 0) {
                sourceList.add(v);
            }
        }
        return sourceList;
    }

    /**
     * 获取收点
     * @return
     */
    private LinkedList<Vetex> getSinkVetex(){
        Iterator<Vetex> vetexIterator = getVetexs();
        LinkedList<Vetex> sinkList = new LinkedList<Vetex>();
        while (vetexIterator.hasNext()){
            Vetex v = vetexIterator.next();
            int outDeg = v.getOutDegree();
            if (outDeg == 0){
                sinkList.add(v);
            }
        }
        return sinkList;
    }
}
