package main;

/**
 * Created by Gvozd on 06.12.2015.
 */
public class MyNode {
    private int id;
    private Object data;
    private MyNode nextNode;
    private MyNode prevNode;

    public MyNode(int id, Object data, MyNode nextNode, MyNode prevNode) {
        this.id = id;
        this.data = data;
        this.nextNode = nextNode;
        this.prevNode = prevNode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public MyNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(MyNode prevNode) {
        this.prevNode = prevNode;
    }

    public MyNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(MyNode nextNode) {
        this.nextNode = nextNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return data.toString();
    }

    public String getThisClass() {
        return String.valueOf(data.getClass());
    }
}
