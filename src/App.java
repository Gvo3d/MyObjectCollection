/**
 * Created by Gvozd on 06.12.2015.
 */
public class App {
    public static void main(String[] args) {
        ObjectCollection collection = new ObjectCollection();
        ObjectCollection collection2 = new ObjectCollection();

        collection.add("String1");
        collection.add("String2");
        collection.add("String3");
        collection.add("String4");
        collection.add("String5");

        collection2.add("String1");
        collection2.add("Line2");
        collection2.add("String4");

        System.out.println("My object collection has "+collection.size()+ " objects.");
        collection.printAll();
        System.out.println("It this collection empty? - "+collection.isEmpty());
        System.out.println("Data in cell 1 has a class: "+(collection.getObjectClass(1)));
        System.out.println("Is there a value \"String3\" in this collection? - "+collection.contains("String3"));
        System.out.println();

        System.out.println("We are adding new item \"String6\" and deleting \"String2\" and deleting cell 4");
        collection.add("String6");
        collection.remove("String2");
        collection.removeById(4);
        collection.printAll();
        System.out.println();

        System.out.println("And we are removing all elements that are equal to other collection and trimming ID counter");
        collection.removeAll(collection2);
        collection.trimId();
        collection.printAll();
        System.out.println();
    }
}
