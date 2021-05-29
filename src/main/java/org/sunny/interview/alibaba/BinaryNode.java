package org.sunny.interview.alibaba;

import java.io.Serializable;

public class BinaryNode<T> implements Serializable,Cloneable{
    private T data;
    private BinaryNode<T> rightChild;
    private BinaryNode<T> leftChild;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    protected BinaryNode<T> clone() throws CloneNotSupportedException {
        BinaryNode<T> newNode = (BinaryNode<T>) super.clone();
        newNode.leftChild = newNode.leftChild.clone();
        newNode.rightChild = newNode.rightChild.clone();
        return newNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryNode<?> that = (BinaryNode<?>) o;

        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (rightChild != null ? !rightChild.equals(that.rightChild) : that.rightChild != null) return false;
        return leftChild != null ? leftChild.equals(that.leftChild) : that.leftChild == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        result = 31 * result + (leftChild != null ? leftChild.hashCode() : 0);
        return result;
    }
}
