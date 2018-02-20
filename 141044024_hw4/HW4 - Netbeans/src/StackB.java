import java.util.ArrayList;

/**
 * StackInterface impelement eder ve ArrayList objesi tutup onun üzerinden
 * işlem yapar.
 * @author emre
 * @param <E> generic
 */
public class StackB<E> implements StackInterface<E>{
    /**
     * ArrayList objesi
     */
    ArrayList<E> list = new ArrayList<E>();

    public void push(E item){
        list.add(item);
    }

    public E pop(){
        return list.remove(size() - 1);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return (size() == 0);
    }
    

}
