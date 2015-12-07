/**
 * Created by Gvozd on 06.12.2015.
 */
public class MyNode {
    private int id;
    private Object data;
    private MyNode nextNode;

    public MyNode(int id, Object data, MyNode nextNode) {
        this.id = id;
        this.data = data;
        this.nextNode = nextNode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
