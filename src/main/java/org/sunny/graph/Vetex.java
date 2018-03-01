

package org.sunny.graph;

import java.util.LinkedList;

public class Vetex{
    private Object informatin;
    private INodeLinkedList<INode<Edge>> adjacentEdges;  //该顶点的临接边表
    private INodeLinkedList<INode<Edge>> reAdjacentEdges; //顶点的逆临接边表
    private INode vexPosition; //顶点在顶点表中的位置
    private boolean visited;
    private int graphType;
    private Object applicationInfo;

    public Vetex(IGraph g,Object informatin) {
        this.informatin = informatin;
        this.adjacentEdges = new NodeLinkedList();
        this.reAdjacentEdges = new NodeLinkedList();
        this.vexPosition = g.insert(this);
        this.visited = false;
        this.graphType = g.getType();
        this.applicationInfo = null;
    }

    /**
     * 判断是否为无项图
     * @return
     */
    private boolean isUndirectedGraph(){
        return this.graphType == IGraph.UndirectedGraph;
    }

    /**
     * 出度
     * @return
     */
    public int getOutDegree(){
        return adjacentEdges.size();
    }

    /**
     * 入度
     * @return
     */
    public int getInDegree(){
        if(isUndirectedGraph()){
            return adjacentEdges.size();
        }
        else
            return reAdjacentEdges.size();
    }


    /**
     * 返回与该顶点相连的边数
     * @return
     */
    public int getDegree(){
        if (isUndirectedGraph()){
            return getInDegree();
        }
        else
            return getInDegree()+getOutDegree();
    }

    /**
     * 重置顶点状态信息
     */
    public void resetStates(){
        this.visited = false;
        this.applicationInfo = null;
    }

    public Object getInformatin() {
        return informatin;
    }

    public void setInformatin(Object informatin) {
        this.informatin = informatin;
    }

    /**
     * 获取与顶点相关联的边
     * @return
     */
    public INodeLinkedList<INode<Edge>> getAdjacentEdges() {
        return adjacentEdges;
    }

    public void setAdjacentEdges(INodeLinkedList adjacentEdges) {
        this.adjacentEdges = adjacentEdges;
    }

    public INodeLinkedList<INode<Edge>> getReAdjacentEdges() {
        if (isUndirectedGraph()){
            return this.adjacentEdges;
        }
        else
            return reAdjacentEdges;
    }

    public void setReAdjacentEdges(INodeLinkedList reAdjacentEdges) {
        this.reAdjacentEdges = reAdjacentEdges;
    }

    public INode getVexPosition() {
        return vexPosition;
    }

    public void setVexPosition(INode vexPosition) {
        this.vexPosition = vexPosition;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getGraphType() {
        return graphType;
    }

    public void setGraphType(int graphType) {
        this.graphType = graphType;
    }

    public Object getApplicationInfo() {
        return applicationInfo;
    }

    public void setApplicationInfo(Object applicationInfo) {
        this.applicationInfo = applicationInfo;
    }
}
