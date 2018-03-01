package org.sunny.graph;

public class SLNode<T> extends INode<T>{
   private SLNode nextNode;

    public SLNode(T t, SLNode nextNode) {
        super(t);
        this.nextNode = nextNode;
    }

    public SLNode(){
        this(null,null);
    }

    public SLNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(SLNode nextNode) {
        this.nextNode = nextNode;
    }
}
