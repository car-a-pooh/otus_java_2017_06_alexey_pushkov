import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by carapooh on 18.06.2017.
 */
public class MyArrayList<T> implements List<T> {


    private T[] array = (T[])(new Object[10]);

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) { return false; }

    public Iterator<T> iterator() {
        return listIterator();
    }

    public Object[] toArray() {
        T[] arr = (T[])(new Object[size]);
        for (int i = 0; i != size; i++) {
            arr[i] = array[i];
        }
        return arr;
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        add(size, t);
        return true;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        size = 0;
    }

    public T get(int index) {
        return array[index];
    }

    public T set(int index, T element) {
        T was = array[index];
        array[index] = element;
        return was;
    }

    private void insert(int index, T element){
        for (int i = size; i > index; i--){
            array[i] = array[i - 1];
        }
        array[index] = element;
    }

    private void propagate(){
        T[] newArray =  (T[])(new Object[2 * array.length]);
        for (int i = 0; i != size; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size){
            Exception e = new Exception("Inapropiate index " + index);
            e.printStackTrace();
            return;
        }
        else if(size == array.length){
            propagate();
        }
        insert(index, element);
        size++;
    }

    public T remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {

        ListIterator <T> iterator = new ListIterator<T>(){

            int index;
            int takenIndex;

            public boolean hasNext(){
                return index < size;
            }

            public int nextIndex(){
                if (index == size){
                    return size;
                }
                else {
                    return index + 1;
                }
            }

            public void set(T t){
                array[takenIndex] = t;
            }

            public T next(){
                takenIndex = index;
                index++;
                return array[index - 1];
            }

            public int previousIndex(){
                return index - 1;
            }

            public void add(T t){
                MyArrayList.this.add(index, t);
            }

            public boolean hasPrevious(){
                return index > 0;
            }

            public void remove(){
                MyArrayList.this.remove(index);
            }

            public T previous(){
                index--;
                takenIndex = index;
                return array[index];
            }

        };

        return iterator;
    }

    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
