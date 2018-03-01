
package org.sunny.graph;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import org.sunny.exception.*;

public class NodeLinkedList<T extends INode> implements INodeLinkedList<INode> {
    private int volumn;

    private DLNode headNode;  //头节点

    private DLNode tailNode; //尾节点

    public NodeLinkedList(){
        this.volumn = 0;
        headNode = new DLNode();
        tailNode = new DLNode();
        headNode.setNextNode(tailNode);
        tailNode.setPreNode(headNode);
    }

    /**
     * 检查不为空，而且node的类型匹配，不是头元素也不是尾元素
     * @param node
     * @return
     * @throws InvalidNodeExeption
     */
    protected DLNode checkPosition(INode node) throws InvalidNodeExeption {
        if (node == null || !(node instanceof DLNode)){
            throw  new InvalidNodeExeption("结点为空或结点类型错误");
        }
        DLNode dlNode = (DLNode)node;
        if (dlNode == headNode || dlNode == tailNode){
            throw new InvalidNodeExeption("指向头节点或尾结点，非法");
        }
        return dlNode;
    }

    /**
     * 将数据根据类转换成结点
     * @param data
     * @return
     */
    private DLNode transformData(Object data){
        DLNode dlNode = null;
        if(data != null){
            if (data instanceof  DLNode){
                dlNode = (DLNode)data;
            }
            else
                dlNode = new DLNode(data,null,null);
        }
        return dlNode;
    }

    /**
     * 返回第一个有内容的结点
     * @return
     * @throws OutOfBoundaryException
     */
    public DLNode getFirst() throws OutOfBoundaryException{
        if (this.isEmpty()){
            throw new OutOfBoundaryException("错误：链表为空");
        }
        return headNode.getNextNode();
    }

    /**
     * 返回最后一个有内容的结点
     * @return
     * @throws OutOfBoundaryException
     */
    public DLNode getLast() throws OutOfBoundaryException{
        if (this.isEmpty()){
            throw new OutOfBoundaryException("错误：链表为空");
        }
        return tailNode.getPreNode();
    }


    /**
     * 得到node p 的下一个元素
     * @param p
     * @return
     * @throws OutOfBoundaryException
     * @throws InvalidNodeExeption
     */
    public DLNode getNext(INode p) throws OutOfBoundaryException,InvalidNodeExeption{
        DLNode dlNode = checkPosition(p);
        DLNode nextNode = dlNode.getNextNode();
        if (nextNode == tailNode)
//            throw new OutOfBoundaryException("下一个元素是链表的结尾元素");
            nextNode = null;
        return nextNode;
    }

    /**
     * 得到node p的上一个元素
     * @param p
     * @return
     * @throws OutOfBoundaryException
     * @throws InvalidNodeExeption
     */
    public DLNode getPrevious(INode p) throws OutOfBoundaryException,InvalidNodeExeption{
        DLNode dlNode = checkPosition(p);
        DLNode preNode = dlNode.getPreNode();
        if (preNode == headNode){
//            throw new OutOfBoundaryException("上一个元素是链表的开始元素");
            preNode = null;
        }
        return preNode;
    }

    /**
     * 将p1、p2、p3结点位置重新排序
     * @param p1
     * @param p2
     * @param p3
     */
    private void changePosition(DLNode p1,DLNode p2,DLNode p3){
        p1.setNextNode(p2);
        p2.setPreNode(p1);
        p2.setNextNode(p3);
        p3.setPreNode(p2);
    }


    /**
     * 头部插入node
     * @param p
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode insertFirst(Object p) throws InvalidNodeExeption{
        DLNode node = transformData(p);
        DLNode dlNode = checkPosition(node);
        changePosition(headNode,dlNode,headNode.getNextNode());
        this.volumn ++;
        return dlNode;
    }


    /**
     * 尾部插入node
     * @param p
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode insertLast(Object p) throws InvalidNodeExeption{
        DLNode node = transformData(p);  //转换成DLNode
        DLNode dlNode = checkPosition(node);
        changePosition(tailNode.getPreNode(),dlNode,tailNode);
        this.volumn++;
        return dlNode;
    }



    /**
     * 在p结点之前插入新结点
     * @param p
     * @param data
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode insertAfter(INode p, Object data) throws InvalidNodeExeption {
        DLNode dlNode = checkPosition(p);
        DLNode insertNode = transformData(data); //将数据转换成node的格式
        checkPosition(insertNode); //检查这个插入的node是否合法
        changePosition(dlNode,insertNode,dlNode.getNextNode()); //转换前后结点的位置关系
        this.volumn++;
        return insertNode;
    }

    /**
     * 在结点p之后插入新结点
     * @param p
     * @param data
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode insertBefore(INode p, Object data) throws InvalidNodeExeption {
        DLNode dlNode = checkPosition(p);
        DLNode insertNode = transformData(data);
        checkPosition(insertNode);
        changePosition(dlNode.getPreNode(),insertNode,dlNode);
        this.volumn++;
        return insertNode;
    }

    /**
     * 移除第一个有内容的结点
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode removeFirst() throws InvalidNodeExeption{
        return removeNode(headNode.getNextNode());
    }

    /**
     * 移除最后一个有内容的结点
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode removeLast() throws InvalidNodeExeption {
        return removeNode(tailNode.getPreNode());
    }

    /**
     * 移除某一个结点p
     * @param p
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode removeNode(INode p) throws InvalidNodeExeption {
        DLNode dlNode = checkPosition(p);
        DLNode preNode = dlNode.getPreNode();
        DLNode nextNode = dlNode.getNextNode();
        preNode.setNextNode(nextNode);
        nextNode.setPreNode(preNode);
        this.volumn = this.volumn-1;
        return dlNode;
    }

    /**
     * 讲旧结点替换成新结点，并返回新结点
     * @param d1
     * @param d2
     * @return
     * @throws InvalidNodeExeption
     */
    public DLNode replace(INode d1, INode d2) throws InvalidNodeExeption {
        DLNode oldone = checkPosition(d1);
        DLNode newone = checkPosition(d2);
        changePosition(oldone.getPreNode(),newone,oldone.getNextNode());
        return oldone;
    }

    /**
     * 大小
     * @return
     */
    public int size() {
        return this.volumn;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        if(this.volumn == 0){
            return  true;
        }
        else {
            return false;
        }
    }

    /**
     * 遍历
     * @return
     */
    public Iterator<INode> iterator() {
        return new NodeListIterator(this);
    }


}
