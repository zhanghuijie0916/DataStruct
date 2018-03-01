
package org.sunny.graph;
public class DLNode<T> extends INode<T> {
    private DLNode preNode; //node队列中上一个node
    private DLNode nextNode; //node队列中下一个node

    //用于头和尾的node
    public DLNode(){
        this(null,null,null);
    }

    public DLNode(T t, DLNode preNode, DLNode nextNode) {
        super(t);
        this.preNode = preNode;
        this.nextNode = nextNode;
    }

    public DLNode getPreNode() {
        return preNode;
    }

    public void setPreNode(DLNode preNode) {
        this.preNode = preNode;
    }

    public DLNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(DLNode nextNode) {
        this.nextNode = nextNode;
    }




}
