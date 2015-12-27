package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Gvozd on 06.12.2015.
 */
public class ObjectCollection implements Collection, Iterable {
    private MyNode firstNode = null;
    private MyNode lastNode = null;
    private int listsize = 0;
    private int idCounter = 0;

    public ObjectCollection() {
    }

    public ObjectCollection(Object newObject) {
        firstNode = new MyNode(0, newObject, null, null);
        lastNode=firstNode;
        listsize = 1;
        idCounter = 1;
    }

    @Override
    public int size() {
        return this.listsize;
    }

    @Override
    public boolean isEmpty() {
        if (this.firstNode != null) return false;
        return true;
    }

    @Override
    public boolean add(Object newObject) {
        if (listsize == 0) {
            firstNode = new MyNode(idCounter, newObject, null, null);
            lastNode=firstNode;
            listsize = 1;
            idCounter++;
            return true;
        }
        if (listsize == 1) {
            MyNode newNode = new MyNode(idCounter, newObject, null, firstNode);
            firstNode.setNextNode(newNode);
            lastNode=newNode;
            listsize++;
            idCounter++;
            return true;
        }
        MyNode lastNode = getLast(firstNode);
        MyNode newNode = new MyNode(idCounter, newObject, null, getLast(firstNode));
        lastNode.setNextNode(newNode);
        lastNode=newNode;
        listsize++;
        idCounter++;
        return true;
    }

    private MyNode getLast(MyNode searchNode) {
        return lastNode;
    }

    private MyNode searchDataInNode(Object searchData, MyNode searchNode) {
        if (searchNode.getData().equals(searchData)) {
            return searchNode;
        }
        if (searchNode.getNextNode() == null) {
            return new MyNode(-1, null, null, null);
        }
        return searchDataInNode(searchData, searchNode.getNextNode());
    }

    public int getIdByData(Object searchableData) {
        return (searchDataInNode(searchableData, firstNode)).getId();
    }

    public void setDataById(int searcheableId, Object setObject) {
        MyNode working = searchIdInNode(searcheableId, firstNode);
        if (working != null) working.setData(setObject);
    }

    private MyNode searchPreviousInNode(MyNode searcheablePreviousNode, MyNode searchNode) {
        if (searchNode.getNextNode() == searcheablePreviousNode) {
            return searchNode;
        }
        return searchPreviousInNode(searcheablePreviousNode, searchNode.getNextNode());
    }

    private MyNode searchIdInNode(int searchId, MyNode searchNode) {
        if (searchNode.getId() == searchId) {
            return searchNode;
        }
        if (searchNode.getNextNode() == null) {
            return new MyNode(-1, null, null, null);
        }
        return searchIdInNode(searchId, searchNode.getNextNode());
    }

    public int searchIdByData(Object searchData) {
        return searchDataInNode(searchData, firstNode).getId();
    }

    @Override
    public boolean remove(Object removeableObject) {
        MyNode removeableNode = searchDataInNode(removeableObject, firstNode);
        if (removeableNode.getId() == -1) {
            return false;
        }
        if (removeableNode == firstNode) {
            firstNode = firstNode.getNextNode();
            listsize--;
        } else {
            MyNode previousNodeFromRemoveable = searchPreviousInNode(removeableNode, firstNode);
            previousNodeFromRemoveable.setNextNode(removeableNode.getNextNode());
            removeableNode = null;
            listsize--;
        }
        return true;
    }

    public boolean removeById(int removeableId) {
        MyNode removeableNode = searchIdInNode(removeableId, firstNode);
        if (removeableNode.getId() == -1) {
            return false;
        }
        if (removeableNode == firstNode) {
            firstNode = firstNode.getNextNode();
            listsize--;
        } else {
            MyNode previousNodeFromRemoveable = searchPreviousInNode(removeableNode, firstNode);
            previousNodeFromRemoveable.setNextNode(removeableNode.getNextNode());
            removeableNode = null;
            listsize--;
        }
        return true;
    }

    public void printById(int printableId) {
        MyNode printableNode = searchIdInNode(printableId, firstNode);
        if (printableNode.getId() != -1) {
            System.out.println(printableNode.toString());
        } else System.out.println("No data with such ID!");
    }

    public void printAll() {
        if (listsize > 0) {
            printNext(firstNode);
        } else System.out.println("Collection has 0 items.");
    }


    private MyNode printNext(MyNode printable) {
        if (printable.getData() != null) {
            System.out.println("Cell " + printable.getId() + " - " + printable.toString());
            if (printable.getNextNode() == null) return new MyNode(-1, null, null, null);
            printNext(printable.getNextNode());
        }
        return new MyNode(-1, null, null, null);
    }

    public String getObjectClass(int id) {
        MyNode searcheableNode = searchIdInNode(id, firstNode);
        if (searcheableNode.getId() != -1) return searcheableNode.getThisClass();
        return "No such object in collection!";
    }

    public boolean trimId() {
        if (listsize == idCounter) return false;
        this.idCounter = 0;
        firstNode.setId(idCounter);
        this.idCounter++;
        trimNext(idCounter, firstNode.getNextNode());
        return true;
    }

    private MyNode trimNext(int newidCounter, MyNode trimNode) {
        trimNode.setId(newidCounter);
        this.idCounter++;
        if (trimNode.getNextNode() == null) return new MyNode(-1, null, null, null);
        return trimNext(this.idCounter, trimNode.getNextNode());
    }

    @Override
    public boolean addAll(Collection newItems) {
        for (Object newItem : newItems) {
            add(newItem);
        }
        return true;
    }

    @Override
    public void clear() {
        this.firstNode = null;
        this.listsize = 0;
        this.idCounter = 0;
    }

    @Override
    public boolean retainAll(Collection retainableCollection) {
        boolean operationFlag = false;
        ArrayList<Object> newCollection = new ArrayList<>();
        for (Object searchableItem : retainableCollection) {
            MyNode coincidenceNode = searchDataInNode(searchableItem, firstNode);
            if (coincidenceNode.getId() != -1) {
                operationFlag = true;
                newCollection.add(coincidenceNode.getData());
                removeById(coincidenceNode.getId());
            }
        }
        if (operationFlag) {
            clear();
            addAll(newCollection);
        }
        return operationFlag;
    }

    @Override
    public boolean removeAll(Collection deletingItems) {
        boolean operationFlag = false;
        for (Object searchableItem : deletingItems) {
            operationFlag = remove(searchableItem);
        }
        return operationFlag;
    }


    @Override
    public boolean containsAll(Collection containableCollection) {
        if (firstNode == null) return false;
        for (Object comparsionItem : containableCollection) {
            boolean answer = contains(comparsionItem);
            if (!answer) return false;
        }
        return true;
    }


    @Override
    public boolean contains(Object searcheableObject) {
        if (firstNode == null) return false;
        MyNode answer = searchDataInNode(searcheableObject, firstNode);
        if (answer.getId() == -1) return false;
        return true;
    }

    @Override
    public Iterator iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        Object[] collectionToArray = new Object[listsize];
        int i = 0;
        for (MyNode x = firstNode; x != null; x = x.getNextNode())
            collectionToArray[i++] = x.getData();
        return collectionToArray;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if (a.length < listsize)
            a = (Object[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), listsize);
        Object[] collectionToArray = a;
        int i = 0;
        for (MyNode x = firstNode; x != null; x = x.getNextNode())
            collectionToArray[i++] = x.getData();

        if (a.length > listsize)
            a[listsize] = null;

        return collectionToArray;
    }
}
