import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Gvozd on 06.12.2015.
 */
public class ObjectCollection implements Collection {
    private MyNode firstNode = null;
    private int listsize=0;
    private int idCounter=0;

    public ObjectCollection() {
    }

    public ObjectCollection(Object newObject) {
        firstNode = new MyNode(0,newObject,null);
        listsize=1;
        idCounter=1;
    }

    @Override
    public int size() {
        return this.listsize;
    }

    @Override
    public boolean isEmpty() {
        if (this.firstNode!=null) return false;
        return true;
    }

    @Override
    public boolean add(Object newObject) {
        if (listsize==0) {
            firstNode = new MyNode(idCounter,newObject,null);
            listsize=1;
            idCounter++;
            return true;
        }
        if (listsize==1) {
            MyNode newNode = new MyNode(idCounter, newObject,null);
            firstNode.setNextNode(newNode);
            listsize++;
            idCounter++;
            return true;
        }
        MyNode lastNode = getLast(firstNode);
        MyNode newNode = new MyNode(idCounter, newObject,null);
        lastNode.setNextNode(newNode);
        listsize++;
        idCounter++;
        return true;
    }

    private MyNode getLast(MyNode searchNode){
        if (searchNode.getNextNode()==null) {
            return searchNode;
        }
        return getLast(searchNode.getNextNode());
    }

    private MyNode searchDataInNode(Object searchData, MyNode searchNode){
        if (searchNode.getData().equals(searchData)) {
            return searchNode;
        }
        if (searchNode.getNextNode()==null){
            return new MyNode(-1, null,null);
        }
        return searchDataInNode(searchData, searchNode.getNextNode());
    }

    private MyNode searchPreviousInNode(MyNode searcheablePreviousNode, MyNode searchNode){
        if (searchNode.getNextNode()==searcheablePreviousNode) {
            return searchNode;
        }
        return searchPreviousInNode(searcheablePreviousNode, searchNode.getNextNode());
    }

    private MyNode searchIdInNode(int searchId, MyNode searchNode){
        if (searchNode.getId()==searchId) {
            return searchNode;
        }
        if (searchNode.getNextNode()==null){
            return new MyNode(-1, null,null);
        }
        return searchIdInNode(searchId, searchNode.getNextNode());
    }

    public int searchIdByDataInNode(Object searchData){
        return searchDataInNode(searchData, firstNode).getId();
    }

    @Override
    public boolean remove(Object removeableObject) {
        MyNode removeableNode = searchDataInNode(removeableObject, firstNode);
        if (removeableNode.getId()==-1) {
            return false;
        }
        MyNode previousNodeFromRemoveable = searchPreviousInNode(removeableNode, firstNode);
        previousNodeFromRemoveable.setNextNode(removeableNode.getNextNode());
        removeableNode=null;
        listsize--;
        return true;
    }

    public boolean removeById(int removeableId) {
        MyNode removeableNode = searchIdInNode(removeableId, firstNode);
        if (removeableNode.getId()==-1) {
            return false;
        }
        MyNode previousNodeFromRemoveable = searchPreviousInNode(removeableNode, firstNode);
        previousNodeFromRemoveable.setNextNode(removeableNode.getNextNode());
        removeableNode=null;
        listsize--;
        return true;
    }

    @Override
    public boolean addAll(Collection newItems) {
        for (Object newItem: newItems){
            add(newItem);
        }
        return true;
    }

    @Override
    public void clear() {
        this.firstNode=null;
        this.listsize=0;
    }

    @Override
    public boolean retainAll(Collection retainableCollection) {
        boolean operationFlag = false;
        ObjectCollection newCollection = new ObjectCollection();

//        for (Object searchableItem: this.firstNode){
//            boolean addingFlag = true;
//            for (int i=0; i<listsize; i++){
//                removingFlag=this.remove(searchableItem);
//                if (!removingFlag) { break;
//                } else operationFlag=true;
//            }
//        }
        
        return operationFlag;
    }

    @Override
    public boolean removeAll(Collection deletingItems) {
        boolean operationFlag = false;
        for (Object searchableItem: deletingItems){
            boolean removingFlag = true;
            for (int i=0; i<listsize; i++){
               removingFlag=this.remove(searchableItem);
                if (!removingFlag) { break;
                } else operationFlag=true;
            }
        }
        return operationFlag;
    }


    @Override
    public boolean containsAll(Collection c) {
        return false;
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public T[] toArray(Object[] a) {
        return new T[0];
    }
}
