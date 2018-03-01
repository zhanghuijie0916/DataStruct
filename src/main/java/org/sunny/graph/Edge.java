package org.sunny.graph;

public class Edge {
    public static final int NORMAL = 0;
    public static final int MST = 1;
    public static final int CRITICAL = 2;

    private int weight;  //边权重
    private Object edgeINfo;
    private INode firstVexPosition;  //起始结点
    private INode secondVexPosition;  //终止结点
    private INode edgePosition; //边在边表中的位置
    private INode edgeFirstPosition;  //边在第一个顶点临街表中的位置
    private INode edgeSecondPosition;  //边在第二个顶点临街边表中的位置
    private int type; //边的类型
    private int graphType; //所在图的类型


    public Edge(IGraph g,Vetex v1,Vetex v2,int weight,Object edgeINfo){
        this.weight = weight;
        this.edgeINfo = edgeINfo;
        firstVexPosition = v1.getVexPosition();
        secondVexPosition = v2.getVexPosition();
        edgePosition = g.insert(this);
        this.type = Edge.NORMAL;
        this.graphType = g.getType();
        if (this.graphType == IGraph.UndirectedGraph){
            edgeFirstPosition = v1.getAdjacentEdges().insertLast(this);
            edgeSecondPosition = v2.getAdjacentEdges().insertLast(this);
        }
        else{
            edgeFirstPosition = v1.getAdjacentEdges().insertLast(this);
            edgeSecondPosition = v2.getReAdjacentEdges().insertLast(this);
        }
    }

    //与边有关的方法
    public void setToMST(){this.type = Edge.MST;}

    public void setToCritical(){this.type = Edge.CRITICAL;}

    public void resetType(){this.type=Edge.NORMAL;}

    public boolean isMSTEdge(){return this.type==Edge.MST;}

    public boolean isCriticalEdge(){return  this.type == Edge.CRITICAL;}

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Object getEdgeINfo() {
        return edgeINfo;
    }

    public void setEdgeINfo(Object edgeINfo) {
        this.edgeINfo = edgeINfo;
    }

    /**
     * 得到顶点信息Vetex
     * @return
     */
    public Vetex getFirstVetex() {
        return (Vetex)this.firstVexPosition.getData();
    }

    public Vetex getSecondVetex() {
        return (Vetex)this.secondVexPosition.getData();
    }

    /**
     * 得到包含顶点信息的INode
     * @return
     */
    public INode getFirstVexPosition() {
        return firstVexPosition;
    }

    public void setFirstVexPosition(INode firstVexPosition) {
        this.firstVexPosition = firstVexPosition;
    }

    public INode getSecondVexPosition() {
        return secondVexPosition;
    }

    public void setSecondVexPosition(INode secondVexPosition) {
        this.secondVexPosition = secondVexPosition;
    }

    public INode getEdgePosition() {
        return edgePosition;
    }

    public void setEdgePosition(INode edgePosition) {
        this.edgePosition = edgePosition;
    }

    public INode getEdgeFirstPosition() {
        return edgeFirstPosition;
    }

    public void setEdgeFirstPosition(INode edgeFirstPosition) {
        this.edgeFirstPosition = edgeFirstPosition;
    }

    public INode getEdgeSecondPosition() {
        return edgeSecondPosition;
    }

    public void setEdgeSecondPosition(INode edgeSecondPosition) {
        this.edgeSecondPosition = edgeSecondPosition;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGraphType() {
        return graphType;
    }

    public void setGraphType(int graphType) {
        this.graphType = graphType;
    }

    @Override
    public String toString() {
        String typeString = "NORMAL";
        if (type==1) typeString = "MST";
        else if (type == 2) typeString="CRITICAL";
        return "Edge{" +
                "weight=" + weight +
                ", edgeINfo=" + edgeINfo +
                ", firstVexPosition=" + ((Vetex)firstVexPosition.getData()).getInformatin() +
                ", secondVexPosition=" + ((Vetex)secondVexPosition.getData()).getInformatin() +
                ", type="+typeString+
                '}';
    }
}
