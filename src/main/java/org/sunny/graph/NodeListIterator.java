package org.sunny.graph;

import java.util.Iterator;


public class NodeListIterator<T> implements Iterator<T>{
    private INodeLinkedList<T> nodeLinkedList;
    private T currentNode;

    public NodeListIterator(INodeLinkedList<T> nodeLinkedList){
        this.nodeLinkedList = nodeLinkedList;
        if(nodeLinkedList.isEmpty()){
            currentNode = null;
        }
        else
            currentNode = nodeLinkedList.getFirst();
    }

    public boolean hasNext() {
        if (currentNode != null){
            return true;
        }
        else
            return false;
    }

    public T next() {
        T node = currentNode;
        currentNode = nodeLinkedList.getNext(currentNode);
        return node;
    }

    public void remove() {

    }


}
