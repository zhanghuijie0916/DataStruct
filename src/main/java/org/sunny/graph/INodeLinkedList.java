
package org.sunny.graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.sunny.exception.*;

/**
 * 存放结点的链表
 * @param <T>
 */
public interface INodeLinkedList<T>{

    int size();

    boolean isEmpty();

    T getFirst() throws OutOfBoundaryException;

    T getLast() throws OutOfBoundaryException;

    T getNext(T t) throws OutOfBoundaryException,InvalidNodeExeption;

    T getPrevious(T t) throws OutOfBoundaryException,InvalidNodeExeption;

    T insertFirst(Object t) throws InvalidNodeExeption;

    T insertLast(Object t) throws InvalidNodeExeption;

    T insertAfter(T t,Object data) throws InvalidNodeExeption;

    T insertBefore(T t,Object data) throws InvalidNodeExeption;

    T removeNode(T t) throws InvalidNodeExeption;

    T removeFirst() throws OutOfBoundaryException;

    T removeLast() throws OutOfBoundaryException;

    T replace(T oldone,T newone) throws InvalidNodeExeption;

    Iterator<T> iterator();
}