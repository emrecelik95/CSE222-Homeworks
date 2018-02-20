import java.util.LinkedList;
import java.util.Queue;

/**
 * StackInterface implement eder.Queue objesi barındırıp onunla islem yapar.
 * @author emre
 * @param <E> generic
 */
public class StackD<E> implements StackInterface<E>{
    /**
     * Queue LiinkedList objesi
     */
    Queue<E> queue = new LinkedList<E>();

    public void push(E item){
        ((LinkedList)(queue)).addFirst(item);
        
    }
    public E pop(){
        return queue.remove();
    }
    public int size(){
        return queue.size();
    }
    public boolean isEmpty(){
        return (size() == 0);
    }
    
}
