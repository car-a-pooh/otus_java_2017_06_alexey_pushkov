import static java.util.Collections.addAll;
import static java.util.Collections.copy;
import static java.util.Collections.sort;

/**
 * Created by carapooh on 18.06.2017.
 */
public class ArrayListTest {
    public static void main(String[] args){
        MyArrayList list = new MyArrayList();
        list.add("string2");
        list.add("string1");
        System.out.println("---------------");
        for (Object obj : list){
            System.out.println(obj.toString());
        }
        addAll(list, "string4", "string3");
        System.out.println("---------------");
        for (Object obj : list){
            System.out.println(obj.toString());
        }
        MyArrayList objList = new MyArrayList();
        for (int i = 0; i != list.size(); i++){
            objList.add(new Integer(i));
        }
        copy(objList, list);
        System.out.println("---------------");
        for (Object obj : objList){
            System.out.println(obj.toString());
        }
        for (int i = 5; i != 41; i++){
            list.add("string" + i);
        }
        sort(list);
        System.out.println("---------------");
        for (Object obj : list){
            System.out.println(obj.toString());
        }
    }
}
