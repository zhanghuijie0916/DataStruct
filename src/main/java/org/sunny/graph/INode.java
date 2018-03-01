package org.sunny.graph;

public abstract class INode<T> {
    private T data;     //结点的内容

    public INode(T t){
        this();
        this.data = t;
    }

    public INode(){
        super();
    }

    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

}
